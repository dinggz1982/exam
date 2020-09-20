<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${homework.title}详细情况分析</title>
    <style>
        /** 数据表格中的select尺寸调整 */
        .layui-table-view .layui-table-cell .layui-select-title .layui-input {
            height: 28px;
            line-height: 28px;
        }

        .layui-table-view [lay-size="lg"] .layui-table-cell .layui-select-title .layui-input {
            height: 40px;
            line-height: 40px;
        }

        .layui-table-view [lay-size="lg"] .layui-table-cell .layui-select-title .layui-input {
            height: 40px;
            line-height: 40px;
        }

        .layui-table-view [lay-size="sm"] .layui-table-cell .layui-select-title .layui-input {
            height: 20px;
            line-height: 20px;
        }

        .layui-table-view [lay-size="sm"] .layui-table-cell .layui-btn-xs {
            height: 18px;
            line-height: 18px;
        }
    </style>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
<!-- 页面加载loading -->
<div class="page-loading">
    <div class="ball-loader">
        <span></span><span></span><span></span><span></span>
    </div>
</div>
<!-- 正文开始 -->
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body table-tool-mini full-table">
            <div class="layui-form toolbar">
               <h1 style="margin-top: 10px;margin-bottom: 10px;"> ${homework.title}</h1>
            </div>
            <table id="tableHomework" lay-filter="tableHomework"></table>
        </div>
    </div>
</div>

<!-- 表格操作列 -->
<script type="text/html" id="tableBarHomework">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<!-- 表单弹窗 -->
<script type="text/html" id="modelHomework">
    <form class="layui-form" lay-filter="homework" style="max-width: 960px;">
        <input type="hidden" name="questionId" value=""/>
        <div class="layui-form-item" style="margin-top: 0;">
            <div class="layui-inline">
                <label class="layui-form-label">作业标题：</label>
                <div class="layui-input-block">
                    <input name="title" placeholder="请输入作业标题" class="layui-input" lay-vertype="tips"
                           lay-verify="required" required="">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">作业内容：</label>
            <div class="layui-input-block">
                <textarea name="homeworkContent" id="homeworkContent" lay-verType="tips"></textarea>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">相关附件：</label>
            <div class="layui-input-block">
                <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-normal" id="files">选择文件</button>
                    <div class="layui-upload-list">
                        <table class="layui-table">
                            <thead>
                            <tr>
                                <th>文件名</th>
                                <th>大小</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody id="uploadList"></tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-form-item" style="text-align: center;margin: 0 auto">
            <button type="button" class="layui-btn" lay-filter="submit" lay-submit>提交</button>
        </div>
    </form>
</script>
<!-- js部分 -->
<!-- js部分 -->
<script>
    layui.use(['layer', 'form', 'table', 'util', 'admin', 'zTree', 'upload', 'layedit'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var util = layui.util;
        var admin = layui.admin;
        var upload = layui.upload;
        var layedit = layui.layedit;
        // 渲染表格
        var insTb = table.render({
            elem: '#tableHomework', //要渲染的表格id
            url: '${ctx}/teacher/homework/detailInfo/${homework.id}/list.json',
            page: true,
            toolbar: true,
            cellMinWidth: 100,
            cols: [[
                {
                    type: 'numbers'
                },
                {
                    field: 'username',
                    sort: false,
                    title: '创建人',
                    templet : function(d) {
                        return d.student.user.realname;
                    }
                },
                {
                    field: 'fromKnowledge',
                    sort: false,
                    title: '知识点',
                    templet : function(d) {
                        return d.fromKnowledge.knowledge;
                    }
                },
                {
                    field: 'relation',
                    sort: false,
                    title: '知识关系',
                    templet : function(d) {
                        return d.relation;
                    }
                }, {
                    field: 'toKnowledge',
                    sort: false,
                    title: '知识点',
                    templet : function(d) {
                        return d.toKnowledge.knowledge;
                    }
                },
                {
                    field: 'createTime',
                    sort: true,
                    title: '创建时间',
                    templet: function (d) {
                        return util.toDateString(d.createTime);
                    }
                },
                {
                    align: 'center',
                    toolbar: '#tableBarHomework',
                    title: '操作',
                    minWidth: 200
                }
            ]]
        });

        // 添加
        $('#btnAddHomework').click(function () {
            showEditModel();
        });

        // 搜索
        form.on('submit(formSubSearchHomework)', function (data) {
            insTb.reload({
                where: data.field
            }, 'data');
        });

        // 工具条点击事件
        table.on('tool(tableHomework)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'edit') { // 修改
                showEditModel(data);
            } else if (layEvent === 'del') { // 删除
                doDel(data.id, data.name);
            }
        });

        // 显示表单弹窗
        function showEditModel(mHomework) {
            admin.open({
                type: 1,
                title: (mHomework ? '修改' : '添加') + '作业',
                content: $('#modelHomework').html(),
                success: function (layero, dIndex) {
                    $(layero).children('.layui-layer-content').css('overflow', 'visible');
                    var url = '${ctx}/teacher/homework/edit';
                    // 回显数据
                    form.val('modelHomeworkForm', mHomework);
                    // 表单提交事件
                    form.on('submit(modelSubmitHomework)', function (data) {
                        layer.load(2);
                        //用post提交数据
                        $.post(url, data.field, function (res) {
                            layer.closeAll('loading');
                            if (res.code == 200) {
                                layer.close(dIndex);
                                layer.msg(res.msg, {icon: 1});
                                insTb.reload({}, 'data');
                            } else {
                                layer.msg(res.msg, {icon: 2});
                            }
                        }, 'json');
                        return false;
                    });
                }
            });
        }

        // 删除
        function doDel(homeworkId, title) {
            layer.confirm('确定要删除“' + title + '”吗？', {
                skin: 'layui-layer-admin',
                shade: .1
            }, function (i) {
                layer.close(i);
                layer.load(2);
                $.post('${ctx}/teacher/homework/delete', {
                    homeworkId: homeworkId
                }, function (res) {
                    layer.closeAll('loading');
                    if (res.code == 200) {
                        layer.msg(res.msg, {icon: 1});
                        insTb.reload({}, 'data');
                    } else {
                        layer.msg(res.msg, {icon: 2});
                    }
                }, 'json');
            });
        }
    });
</script>

</body>
</html>