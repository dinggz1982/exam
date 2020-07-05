<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>班级详细情况</title>
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
                    <div class="layui-inline" style="padding-right: 110px;">
                        <button class="layui-btn icon-btn" lay-filter="formSubSearchClassInfo" lay-submit>
                            <i class="layui-icon">&#xe615;</i>搜索
                        </button>
                        <%--<button id="btnAddStudent" class="layui-btn icon-btn" lay-filter="importStudent"><i class="layui-icon">&#xe67c;</i>导入学生
                        </button>--%>
                            <button type="button" class="layui-btn layui-btn-normal" id="studentFile">选择文件</button>
                            <button class="layui-btn" id="uploadFileBtn">开始上传</button>
                    </div>
                </div>
            </div>
            <table id="tableClassInfo" lay-filter="tableClassInfo"></table>
        </div>
    </div>
</div>

<!-- 表格操作列 -->
<script type="text/html" id="tableBarClassInfo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看班级详细情况</a>
</script>
<!-- 批量导入学生 -->
<!-- 表单弹窗 -->
<script type="text/html" id="modelUploadStudent">
    <form id="modelUploadStudentForm" lay-filter="modelUploadStudentForm" class="layui-form model-form">
        <input name="id" type="hidden"/>
        <div class="layui-form-item">
            <div class="layui-input-block">
                提交批量导入学生信息，模板下载
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block layui-upload">
                <button type="button" class="layui-btn" id="importStudent"><i class="layui-icon"></i>批量导入学生</button>
            </div>
        </div>
    </form>
</script>



<!-- js部分 -->
<script>
    layui.use(['layer', 'form', 'upload','table', 'util', 'admin'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var table = layui.table;
        var util = layui.util;
        var admin = layui.admin;
        var upload = layui.upload;

        //更新学院信息
        form.on('select(getCollege)', function (data) {
            //data.value 得到被选中的值
            var url = '${ctx}/profile/college/getCollegeBySchoolId/' + data.value;
            $.post(url, function (data) {
                $("#college_").empty();
                $("#college_").append(new Option("请选择学院", ""));
                $.each(data, function (index, item) {
                    $("#college_").append(new Option(item.name, item.id));
                    //console.log(index,item);
                });
                layui.form.render("select");
            });
        });
        //更新学院信息--用于新增表单
        form.on('select(getCollege2)', function (data) {
            //alert("新增表单-更新学院");
            //data.value 得到被选中的值
            var url = '${ctx}/profile/college/getCollegeBySchoolId/' + data.value;
            $.post(url, function (data) {
                $("#college").empty();
                $("#college").append(new Option("请选择学院", ""));
                $.each(data, function (index, item) {
                    $("#college").append(new Option(item.name, item.id));
                    //console.log(index,item);
                });
                layui.form.render("select");
            });
        });

        //根据学院更新major信息
        form.on('select(getMajor)', function (data) {
            //data.value 得到被选中的值
           // alert("根据学院更新专业")
            var url = '${ctx}/profile/major/getMajorByCollegeId/' + data.value;
            $.post(url, function (data) {
                $("#major_").empty();
                $("#major_").append(new Option("请选择专业", ""));
                $.each(data, function (index, item) {
                    $("#major_").append(new Option(item.name, item.id));
                });
                layui.form.render("select");
            });

        });

        //根据学院更新major信息--用于修改或新增表单
        form.on('select(getMajor2)', function (data) {
            //data.value 得到被选中的值
            // alert("根据学院更新专业")
            var url = '${ctx}/profile/major/getMajorByCollegeId/' + data.value;
            $.post(url, function (data) {
                $("#major").empty();
                $("#major").append(new Option("请选择专业", ""));
                $.each(data, function (index, item) {
                    $("#major").append(new Option(item.name, item.id));
                });
                layui.form.render("select");
            });

        });

        // 渲染表格
        var insTb = table.render({
            elem: '#tableClassInfo',//要渲染的表格id
            url: '${ctx}/profile/classInfo/student.json/${classInfo.id}',
            page: true,
            toolbar: true,
            cellMinWidth: 100,
            cols: [[
                {type: 'numbers'},
                {field: 'username', sort: true, title: '用户名',templet: function (d) {
                    return d.user.username;
                    }
                },
                {field: 'reaname', sort: true, title: '真实姓名',templet: function (d) {
                        return d.user.realname;
                    }
                },
                {field: 'sex', sort: true, title: '性别',templet: function (d) {
                        return d.user.sex;
                    }
                },
                {align: 'center', toolbar: '#tableBarClassInfo', title: '操作', minWidth: 200}
            ]]
        });

        // 添加
        $('#btnAddClassInfo').click(function () {
            showEditModel();
        });

        // 添加
        $('#btnAddStudent').click(function () {
            showUploadStudentModel();
        });
        // 搜索
        form.on('submit(formSubSearchClassInfo)', function (data) {
            insTb.reload({where: data.field}, 'data');
        });

        // 工具条点击事件
        table.on('tool(tableClassInfo)', function (obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'edit') { // 修改
                showEditModel(data);
            } else if (layEvent === 'del') { // 删除
                doDel(data.id, data.name);
            } else if (layEvent === 'detail') { // 查看学校详细情况

            }
        });

        // 显示表单弹窗
        function showEditModel(mClassInfo) {
            admin.open({
                type: 1,
                title: (mClassInfo ? '修改' : '添加') + '学生',
                content: $('#modelClassInfo').html(),
                success: function (layero, dIndex) {
                    $(layero).children('.layui-layer-content').css('overflow', 'visible');
                    var url = '${ctx}/profile/classInfo/edit';
                    // 回显数据
                    form.val('modelClassInfoForm', mClassInfo);
                        // 表单提交事件
                        form.on('submit(modelSubmitClassInfo)', function (data) {
                            //判断专业是否增加
                            var major = $("#major").val();
                            if(major>0){
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
                            }else{
                                layer.msg("您尚未选择班级对应的专业！", {icon: 2});
                                return false;
                            }
                        });

                }
            });
        }

        //批量导入学生
        upload.render({ //允许上传的文件后缀
            elem: '#studentFile'
            ,url: '${ctx}/student/saveUserFromFile/${classInfo.id}' //改成您自己的上传接口
            ,accept: 'file' //普通文件
            ,exts: 'xlsx|xls' //只允许上传压缩文件
            ,done: function(res){
                layer.msg('上传成功');
                console.log(res)
            },error:function (e) {
                layer.msg(e);
            }
        });

        //显示上传学生
        function showUploadStudentModel(mClassInfo) {
        admin.open({
            type: 1,
            title: (mClassInfo ? '修改' : '添加') + '学生',
            content: $('#modelUploadStudent').html(),
            success: function (layero, dIndex) {
                $(layero).children('.layui-layer-content').css('overflow', 'visible');
                var url = '${ctx}/profile/classInfo/edit';
                // 回显数据
                form.val('modelClassInfoForm', mClassInfo);
                // 表单提交事件
                form.on('submit(modelSubmitClassInfo)', function (data) {
                    //判断专业是否增加
                    var major = $("#major").val();
                    if(major>0){
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
                    }else{
                        layer.msg("您尚未选择班级对应的专业！", {icon: 2});
                        return false;
                    }
                });

            }
        });
    }

        // 删除
        function doDel(classInfoId, classInfoname) {
            layer.confirm('确定要删除“' + classInfoname + '”吗？', {
                skin: 'layui-layer-admin',
                shade: .1
            }, function (i) {
                layer.close(i);
                layer.load(2);
                $.post('${ctx}/profile/classInfo/delete', {
                    classInfoId: classInfoId
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