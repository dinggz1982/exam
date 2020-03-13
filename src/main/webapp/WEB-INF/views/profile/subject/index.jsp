<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>课元管理</title>
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
                <div class="layui-form-item">
                <div class="layui-inline">
                        <label class="layui-form-label w-auto">选择专业：</label>
                        <div class="layui-input-inline mr0">
                            <select name="major_id">
                            	<option value="0">请选择专业</option>
                            	<c:forEach items="${majors }" var="major">
                            		<option value="${major.id }">${major.name }</option>
                            	</c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label w-auto">专业名称：</label>
                        <div class="layui-input-inline mr0">
                            <input name="name" class="layui-input" type="text" placeholder="输入学院名称"/>
                        </div>
                    </div>
                    <div class="layui-inline" style="padding-right: 110px;">
                        <button class="layui-btn icon-btn" lay-filter="formSubSearchSubject" lay-submit>
                            <i class="layui-icon">&#xe615;</i>搜索
                        </button>
                        <button id="btnAddSubject" class="layui-btn icon-btn"><i class="layui-icon">&#xe654;</i>添加</button>
                    </div>
                </div>
            </div>
            <table id="tableSubject" lay-filter="tableSubject"></table>
        </div>
    </div>
</div>

<!-- 表格操作列 -->
<script type="text/html" id="tableBarSubject">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看课元详细情况</a>
</script>

<!-- 表单弹窗 -->
<script type="text/html" id="modelSubject">
    <form id="modelSubjectForm" lay-filter="modelSubjectForm" class="layui-form model-form">
        <input name="id" type="hidden"/>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">课元名名称</label>
            <div class="layui-input-block">
                <input name="name" placeholder="请输入课元名称" type="text" class="layui-input" maxlength="20"
                       lay-verType="tips" lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">专业</label>
            <div class="layui-input-block">
               	<select name="major_id" lay-filter="getSubject">
						<option>请选择专业</option>
					<c:forEach items="${majors }" var="major">
						<option value="${major.id}">${major.college.school.name }-${major.college.name }-${major.name }</option>
					</c:forEach>
				</select>
            </div>
        </div>
		<div class="layui-form-item">
            <label class="layui-form-label layui-form-required">课程简介</label>
            <div class="layui-input-block">
				<textarea class="layui-textarea" name="description" id="description"></textarea>
            </div>
        </div>
        <div class="layui-form-item text-right">
            <button class="layui-btn layui-btn-primary" type="button" ew-event="closePageDialog">取消</button>
            <button class="layui-btn" lay-filter="modelSubmitSubject" lay-submit>保存</button>
        </div>
    </form>
</script>

<!-- js部分 -->
<script>
    layui.use(['layer', 'form', 'table', 'util', 'admin'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var util = layui.util;
        var admin = layui.admin;
        var layedit = layui.layedit;

        // 渲染表格
        var insTb = table.render({
            elem: '#tableSubject',//要渲染的表格id
            url: '${ctx}/profile/subject/list.json',
            page: true,
            toolbar: true,
            cellMinWidth: 100,
            cols: [[
                {type: 'numbers'},
                {field: 'name', sort: true, title: '课元名称'},
                {field: 'schoolName', sort: true, title: '学校-学院-专业',templet: function (d) {
                        return d.major.college.school.name + "-" +  d.major.college.name+"-"+d.major.name;
                    }},
                {
                    field: 'createTime', sort: true, templet: function (d) {
                        return util.toDateString(d.createTime);
                    }, title: '创建时间'
                },
                {align: 'center', toolbar: '#tableBarSubject', title: '操作', minWidth: 200}
            ]]
        });

        // 添加
        $('#btnAddSubject').click(function () {
            showEditModel();
        });

        // 搜索
        form.on('submit(formSubSearchSubject)', function (data) {
            insTb.reload({where: data.field}, 'data');
        });

        // 工具条点击事件
        table.on('tool(tableSubject)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'edit') { // 修改
                showEditModel(data);
            } else if (layEvent === 'del') { // 删除
                doDel(data.id, data.name);
            } else if (layEvent === 'detail') { // 查看学校详细情况
                //resetPsw(data.id, data.nickName);新开一个页面查看学校详细情况
            }
        });

        // 显示表单弹窗
        function showEditModel(mSubject) {
            admin.open({
                type: 1,
                title: (mSubject ? '修改' : '添加') + '课元',
                content: $('#modelSubject').html(),
                success: function (layero, dIndex) {
                    $(layero).children('.layui-layer-content').css('overflow', 'visible');
                    var url = '${ctx}/profile/subject/edit';
                    // 回显数据
                    form.val('modelSubjectForm', mSubject);
                    // 表单提交事件
                    form.on('submit(modelSubmitSubject)', function (data) {
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
        function doDel(subjectId, subjectName) {
            layer.confirm('确定要删除“' + subjectName + '”吗？', {
                skin: 'layui-layer-admin',
                shade: .1
            }, function (i) {
                layer.close(i);
                layer.load(2);
                $.post('${ctx}/profile/subject/delete', {
                    subjectId: subjectId
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