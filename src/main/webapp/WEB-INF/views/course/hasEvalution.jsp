<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>已提交作业</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
	<link rel="stylesheet" href="${ctx }/assets/css/login.css?v=316">
  </head>
<body>
<div class="error-page">
    <div class="error-page-info">
        <h1 style="text-align: center;margin: 0 auto;padding-top: 100px;">已提交过课堂练习，不能重复测试</h1>
        <div>
        </div>
    </div>
</div>

<!-- js部分 -->
<script>
    layui.use(['layer', 'form'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
    });
</script>
</body>
</html>
