<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>错误页面</title>
     <%@include file="/WEB-INF/views/include/head.jsp" %>
    <link rel="stylesheet" href="${ctx }/assets/css/error-page.css"/>
  </head>
  
  <!-- 加载动画 -->
<div class="page-loading">
    <div class="ball-loader">
        <span></span><span></span><span></span><span></span>
    </div>
</div>

<!-- 正文开始 -->
<div class="error-page">
    <img class="error-page-img" src="../../../assets/images/ic_500.png">
    <div class="error-page-info">
        <h1>服务器内部错误</h1>
        <div class="error-page-info-desc">${exception}</div>
        <div>
            <button ew-href="/" class="layui-btn">返回首页</button>
        </div>
    </div>
</div>

<script>
    layui.use(['admin'], function () {
        var $ = layui.jquery;
        var admin = layui.admin;

    });
</script>
</html>
