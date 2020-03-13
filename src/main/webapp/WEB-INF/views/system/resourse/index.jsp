<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>菜单管理</title>
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
        <div class="layui-card-body">
            <div class="layui-form toolbar">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label w-auto">搜索：</label>
                        <div class="layui-input-inline mr0">
                            <input id="edtSearchAuth" name="name" class="layui-input" placeholder="输入关键字"/>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button id="btnSearchAuth" class="layui-btn icon-btn"><i class="layui-icon">&#xe615;</i>搜索
                        </button>
                        <button id="btnAddAuth" class="layui-btn icon-btn"><i class="layui-icon">&#xe654;</i>添加</button>
                    </div>
                </div>
            </div>
            <table id="tableAuth"></table>
        </div>
    </div>
</div>

<!-- 表格操作列 -->
<script type="text/html" id="tableBarAuth">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<!-- 表单弹窗 -->
<script type="text/html" id="modelAuth">
    <form id="modelAuthForm" lay-filter="modelAuthForm" class="layui-form model-form">
        <input name="id" type="hidden"/>
        <div class="layui-form-item">
            <label class="layui-form-label">上级菜单</label>
            <div class="layui-input-block">
                <select name="parentId" lay-search>
                    <option value="">请选择上级菜单</option>
					<c:forEach var="resource" items="${resources }">
						<option value="${resource.id}">${resource.name }</option>
					</c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">权限名称</label>
            <div class="layui-input-block">
                <input name="name" placeholder="请输入权限名称" type="text" class="layui-input" maxlength="50"
                       lay-verType="tips" lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">权限类型</label>
            <div class="layui-input-block">
                <input name="isMenu" type="radio" value="1" title="菜单" checked/>
                <input name="isMenu" type="radio" value="0" title="按钮"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单url</label>
            <div class="layui-input-block">
                <input name="url" placeholder="请输入菜单url" type="text" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">菜单图标</label>
            <div class="layui-input-block">
                <input name="menuIcon" placeholder="请输入菜单图标" type="text" class="layui-input"/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">排序号</label>
            <div class="layui-input-block">
                <input name="orderNumber" placeholder="请输入排序号" type="number" class="layui-input" min="0" max="1000"
                       lay-verType="tips" lay-verify="required|number" required/>
            </div>
        </div>
        <div class="layui-form-item text-right">
            <button class="layui-btn layui-btn-primary" type="button" ew-event="closePageDialog">取消</button>
            <button class="layui-btn" lay-filter="modelSubmitAuth" lay-submit>保存</button>
        </div>
    </form>
</script>
<script>
    layui.use(['layer', 'form', 'admin', 'treeTable', 'util'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var admin = layui.admin;
        var treeTable = layui.treeTable;
        var util = layui.util;

        // 渲染表格
        var insTb = treeTable.render({
            elem: '#tableAuth',
            tree: {
                iconIndex: 1,  // 折叠图标显示在第几列
                idName: 'id',  // 自定义id字段的名称
                pidName: 'parentId',  // 自定义标识是否还有子节点的字段名称
                isPidData: true  // 是否是pid形式数据
            },
            cellMinWidth: 100,
            cols: [
                {type: 'numbers'},
                {field: 'name', title: '权限名称', width: 260},
                {field: 'url', title: '菜单url'},
                {field: 'orderNumber', title: '排序号', align: 'center', width: 100},
                {
                    title: '类型', templet: function (d) {
                        var strs = [
                            '<span class="layui-badge layui-badge-green">菜单</span>',
                            '<span class="layui-badge layui-badge-blue">按钮</span>'
                        ];
                       // return strs[d.isMenu];
                       return d.isMenu;
                    }, align: 'center', width: 100
                },
                {
                    templet: function (d) {
                        return util.toDateString(d.createTime);
                    }, title: '创建时间', align: 'center'
                },
                {templet: '#tableBarAuth', title: '操作', align: 'center', width: 160}
            ],
            reqData: function (data, callback) {
                $.get('/system/resource.json', function (res) {
                    callback(res.data);
                });
            }
        });

        // 搜索
        $('#btnSearchAuth').click(function () {
            var keyword = $('#edtSearchAuth').val();
            if (keyword) {
                insTb.filterData(keyword);
            } else {
                insTb.clearFilter();
            }
        });

        // 添加按钮点击事件
        $('#btnAddAuth').click(function () {
            showEditModel();
        });

        // 工具条点击事件
        treeTable.on('tool(tableAuth)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'edit') { // 修改
                showEditModel(data);
            } else if (layEvent === 'del') { // 删除
                doDel(data.id, data.name);
            }
        });

        // 显示表单弹窗
        function showEditModel(mAuth) {
            admin.open({
                type: 1,
                title: (mAuth ? '修改' : '添加') + '权限',
                content: $('#modelAuth').html(),
                success: function (layero, dIndex) {
                    $(layero).children('.layui-layer-content').css('overflow', 'visible');
                    var url = '${ctx}/system/resource/edit';
                    if (mAuth && mAuth.isMenu == '1') {
                        $('#modelAuthForm input[name="isMenu"][value="1"]').prop("checked", true);
                    }
                    form.val('modelAuthForm', mAuth);  // 回显数据
                    // 表单提交事件
                    form.on('submit(modelSubmitAuth)', function (data) {
                        /* if (data.field.parentId == '') {
                            data.field.parentId = '-1';
                        } */
                        layer.load(2);
                        $.post(url, data.field, function (res) {
                        console.log(res);
                            layer.closeAll('loading');
                            if (res.code == 200) {
                                layer.close(dIndex);
                                layer.msg(res.msg, {icon: 1});
                                insTb.refresh();
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
        function doDel(id, name) {
            layer.confirm('确定要删除“' + name + '”吗？', {
                skin: 'layui-layer-admin',
                shade: .1
            }, function (index) {
                layer.close(index);
                layer.load(2);
                $.get('${ctx}/system/resource/delete', {
                    id: id
                }, function (res) {
                    layer.closeAll('loading');
                    if (res.code == 200) {
                        layer.msg(res.msg, {icon: 1});
                        insTb.refresh();
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
