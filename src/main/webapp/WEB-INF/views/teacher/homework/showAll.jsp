<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>教师端功能--查看全班作业</title>
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
                        class="layui-badge layui-bg-blue" style="margin-right: 20px">${homework.course.name}</span>发布/更新时间：<span
                        class="layui-badge-rim" style="margin-right: 20px">${homework.createTime}</span>
                    <br>
                </div>
                <%--<div class="layui-inline">
                    <button id="btnAddKnowledge" class="layui-btn icon-btn">
                        <i class="layui-icon">&#xe654;</i>添加知识网络
                    </button>
                </div>--%>
            </div>
        </div>
        <div class="layui-col-md12">
            <div id="mynetwork" style="width: 100%;height: 800px;"></div>
        </div>
    </div>
</div>
</div>
</div>
<script>
    layui.use(['layer', 'form', 'util', 'admin'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var admin = layui.admin;
    });
</script>
<script>
    var nodes = new vis.DataSet([${nodes}]);
    var edges = new vis.DataSet([${edges}]);
    var container = document.getElementById('mynetwork');
    var data = {
        nodes: nodes,
        edges: edges
    };
    var options = {};
    var network = new vis.Network(container, data, options);
</script>
</body>
</html>