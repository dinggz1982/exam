<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生端功能--完成作业</title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
    <script type="text/javascript" src="${ctx }/assets/vis/vis-network.min.js"></script>
    <script type="text/javascript" src="${ctx }/assets/libs/jquery/jquery-3.2.1.min.js"></script>
    <style>
        .detail-about {
            position: relative;
            line-height: 20px;
            padding: 15px 15px 15px 75px;
            font-size: 13px;
            background-color: #f8f8f8;
            color: #999;
        }

        body {
            background-color: #fff;
        !important;
        }
    </style>
</head>
<body>
<!-- 正文开始 -->
<div class="layui-fluid">
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-header"><h1>${homework.title}</h1></div>
            <div class="layui-card-body">
                <div class="layui-inline">
                    <br>发布人：<span class="layui-badge layui-bg-green"
                                  style="margin-right: 20px">${homework.teacher.realname}</span> 来自课程：<span
                        class="layui-badge layui-bg-blue" style="margin-right: 20px">${homework.course.name}</span>发布时间：<span
                        class="layui-badge-rim" style="margin-right: 20px">${homework.createTime}</span>
                    <br>
                </div>
                <div class="layui-inline">
                    <button id="btnAddKnowledge" class="layui-btn icon-btn">
                        <i class="layui-icon">&#xe654;</i>添加知识网络
                    </button>
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div id="mynetwork" style="width: 100%;height: 800px;"></div>
        </div>
    </div>
</div>
<!-- 表单弹窗 -->
<script type="text/html" id="modelKnowledge">
    <form id="modelKnowledgeForm" lay-filter="modelKnowledgeForm" method="post" class="layui-form model-form">
        <input name="id" type="hidden"/>
        <input type="hidden" name="myHomework_id" value="${myHomeWork.id}">
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">知识节点(From)</label>
            <div class="layui-input-block">
                <input name="from" id="from" placeholder="请输入知识节点(From)" type="text" class="layui-input" maxlength="20"
                       lay-verType="tips" lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">关系(Relation)</label>
            <div class="layui-input-block">
                <input name="relation" placeholder="请输入关系(Relation)" type="text" class="layui-input" maxlength="20"
                       lay-verType="tips" lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">知识节点(To)</label>
            <div class="layui-input-block">
                <input name="to" placeholder="请输入知识节点(From)" type="text" class="layui-input" maxlength="20"
                       lay-verType="tips" lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">描述</label>
            <div class="layui-input-block">
                <textarea name="description" placeholder="请输入相关描述信息" class="layui-textarea"></textarea>
            </div>
        </div>
        <div class="layui-form-item text-right">
            <button class="layui-btn layui-btn-primary" type="button" ew-event="closePageDialog">取消</button>
            <button class="layui-btn" lay-filter="modelSubmitKnowledge" lay-submit>保存</button>
        </div>
    </form>
</script>
</div>
</div>
<script>
    layui.use(['layer', 'form', 'util', 'admin'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var admin = layui.admin;
        // 添加
        $('#btnAddKnowledge').click(function () {
            showEditModel();
        });

        function showEditModel(mCourse) {
            admin.open({
                type: 1,
                title: (mCourse ? '修改' : '添加') + '知识关联',
                content: $('#modelKnowledge').html(),
                success: function (layero, dIndex) {
                    $(layero).children('.layui-layer-content').css('overflow', 'visible');
                    var url = '${ctx}/myKnowledgeGraph/edit';
                    // 表单提交事件
                    form.on('submit(modelSubmitKnowledge)', function (data) {
                        layer.load(2);
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
                },end:function () {
                    location.reload();
                }
            });
        }
        //可视化
        var nodes = new vis.DataSet([${nodes}]);
        var edges = new vis.DataSet([${edges}]);
        var container = document.getElementById('mynetwork');
        var data = {
            nodes: nodes,
            edges: edges
        };
        var options = {};
        var network = new vis.Network(container, data, options);

        //单击节点事件
        network.on("click", function (params) {
            params.event = "[original event]";
            var id =params.nodes;
            if(id!=null&&id>0){
                $.get("${ctx}/myKnowledgeGraph/"+id,function(data,status){
                    showEditModel();
                    $("#from").val(data);
                });
            }
        });
    });



</script>
</body>
</html>