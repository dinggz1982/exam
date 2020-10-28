<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查看试题</title>
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
                        <label class="layui-form-label w-auto">标题：</label>
                        <div class="layui-input-inline mr0">
                            <input name="title" class="layui-input" type="text" placeholder="输入标题"/>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <%--题目类型(1.填空题,2.判断题3.单选题,4.多选题5.编程题 6.程序填空题 7.选择填空--%>
                        <label class="layui-form-label w-auto">试题类型：</label>
                        <div class="layui-input-inline mr0">
                            <select name="type">
                                <option value="">请选择</option>
                                <option value="1">填空题</option>
                                <option value="2">判断题</option>
                                <option value="3">单选题</option>
                                <option value="4">多选题</option>
                                <option value="5">编程题</option>
                                <option value="6">程序填空题</option>
                                <option value="7">选择填空</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label w-auto">测评类型：</label>
                        <div class="layui-input-inline mr0">
                            <select name="judgeType">
                                <option value="">请选择</option>
                                <option value="1">normal judge</option>
                                <option value="3">lemon judge</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline" style="padding-right: 110px;">
                        <button class="layui-btn icon-btn" lay-filter="formSubSearchUser" lay-submit>
                            <i class="layui-icon">&#xe615;</i>搜索
                        </button>
                        <button id="btnAddUser" class="layui-btn icon-btn"><i class="layui-icon">&#xe654;</i>添加</button>
                    </div>
                </div>
            </div>
            <table id="problemTable" lay-filter="tableUser"></table>
        </div>
    </div>
</div>

<!-- 表格操作列 -->
<script type="text/html" id="tableBarUser">
    <%--<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
    <a class="layui-btn layui-btn-xs" lay-event="view">查看</a>
</script>
<!-- 表格状态列 -->
<script type="text/html" id="tableStateUser">
    <input type="checkbox" lay-filter="ckStateUser" value="{{d.userId}}" lay-skin="switch"
           lay-text="正常|锁定" {{d.state==0?'checked':''}}/>
</script>
<!-- 表单弹窗 -->
<script type="text/html" id="modelUser">
    <form id="modelUserForm" lay-filter="modelUserForm" class="layui-form model-form">
        <input name="id" type="hidden"/>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">用户名</label>
            <div class="layui-input-block">
                <input name="username" placeholder="请输入用户名" type="text" class="layui-input" maxlength="20"
                       lay-verType="tips" lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">真实姓名</label>
            <div class="layui-input-block">
                <input name="realname" placeholder="请输入真实姓名" type="text" class="layui-input" maxlength="20"
                       lay-verType="tips" lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">测评类型</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男" checked/>
                <input type="radio" name="sex" value="女" title="女"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">角色</label>
            <div class="layui-input-block">
					<c:forEach items="${roles }" var="role">
						<input type="checkbox" name="roleIds" value="${role.id}" title="${role.name}"/>&nbsp;
					</c:forEach>
            </div>
        </div>
        <div class="layui-form-item text-right">
            <button class="layui-btn layui-btn-primary" type="button" ew-event="closePageDialog">取消</button>
            <button class="layui-btn" lay-filter="modelSubmitUser" lay-submit>保存</button>
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

        // 渲染表格
        var insTb = table.render({
            elem: '#problemTable',
            url: '${ctx}/viewAllProblem/list.json',
            page: true,
            toolbar: true,
            cellMinWidth: 100,
            cols: [[
                {type: 'numbers'},
                {field: 'id', sort: true, title: '题号'},
                {field: 'title', sort: true, title: 'title'},
                {field: 'judgeType', templet: function (d) {
                        var judgeType = "";//normal_judge、2：goc_judge、3：lemon_judge
                        switch (d.judgeType) {
                            case (1):
                                judgeType = "normal_judge";
                                break;
                            case (2):
                                judgeType = "goc_judge";
                                break;
                            case (3):
                                judgeType = "lemon_judge";
                                break;
                        }
                        return judgeType;
                    }, title: '测评类型'},
                {
                    field: 'type', templet: function (d) {
                        var type = "";
                        //题目类型(1.填空题,2.判断题3.单选题,4.多选题5.编程题 6.程序填空题 7.选择填空
                        switch (d.type) {
                            case (1):
                                type = "填空题";
                                break;
                            case (2):
                                type = "判断题";
                                break;
                            case (3):
                                type = "单选题";
                                break;
                            case (4):
                                type = "多选题";
                                break;
                            case (5):
                                type = "编程题";
                                break;
                            case (6):
                                type = "程序填空题";
                                break;
                            case (7):
                                type = "选择填空";
                                break;
                        }
                        return type;
                    }, title: '试题类型'
                },
                {align: 'center', toolbar: '#tableBarUser', title: '操作', minWidth: 200}
            ]]
        });

        // 添加
        $('#btnAddUser').click(function () {
            showEditModel();
        });

        // 搜索
        form.on('submit(formSubSearchUser)', function (data) {
            insTb.reload({where: data.field}, 'data');
        });

        // 工具条点击事件
        table.on('tool(tableUser)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            /*if (layEvent === 'edit') { // 修改
                console.log(data);
                showEditModel(data);
            } else if (layEvent === 'del') { // 删除
                doDel(data.id, data.username);
            } else if (layEvent === 'reset') { // 重置密码
                resetPsw(data.userId, data.nickName);
            }*/
            if(layEvent==="view"){
                //alert();
                //查看试题
                layui.use(['index'], function () {
                    var index = layui.index;
                    index.openTab({
                        title: '查看试题:'+data.title+'设置',
                        url: '/viewProblem/'+data.type+'/'+data.id,
                        end: function() {
                            //insTb.reload();
                        }
                    });
                });
            }
        });

        // 显示表单弹窗
        function showEditModel(mUser) {
            admin.open({
                type: 1,
                title: (mUser ? '修改' : '添加') + '用户',
                content: $('#modelUser').html(),
                success: function (layero, dIndex) {
                    $(layero).children('.layui-layer-content').css('overflow', 'visible');
                    var url = '${ctx}/system/user/edit';
                    // 回显数据
                    form.val('modelUserForm', mUser);
                    // 表单提交事件
                    form.on('submit(modelSubmitUser)', function (data) {
                        layer.load(2);
                        $.get(url, data.field, function (res) {
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
        function doDel(id, username) {
            layer.confirm('确定要删除“' + username + '”吗？', {
                skin: 'layui-layer-admin',
                shade: .1
            }, function (i) {
                layer.close(i);
                layer.load(2);
                $.post('${ctx}/system/user/delete', {
                    id: id
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

        // 修改用户状态
        form.on('switch(ckStateUser)', function (obj) {
            layer.load(2);
            $.get('../../json/ok.json', {
                userId: obj.elem.value,
                state: obj.elem.checked ? 0 : 1
            }, function (res) {
                layer.closeAll('loading');
                if (res.code == 200) {
                    layer.msg(res.msg, {icon: 1});
                } else {
                    layer.msg(res.msg, {icon: 2});
                    $(obj.elem).prop('checked', !obj.elem.checked);
                    form.render('checkbox');
                }
            }, 'json');
        });

        // 重置密码
        function resetPsw(userId, nickName) {
            layer.confirm('确定要重置“' + nickName + '”的登录密码吗？', {
                skin: 'layui-layer-admin',
                shade: .1
            }, function (i) {
                layer.close(i);
                layer.load(2);
                $.get('../../json/ok.json', {
                    userId: userId
                }, function (res) {
                    layer.closeAll('loading');
                    if (res.code == 200) {
                        layer.msg(res.msg, {icon: 1});
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