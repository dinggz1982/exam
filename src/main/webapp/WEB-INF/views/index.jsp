<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>考试系统</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<link rel="stylesheet" href="${ctx }/assets/css/index.css">
<link rel="stylesheet" href="${ctx }/assets/css/layui2.5.5.css">

<style>
@media screen and (min-width: 1191px) {
	.layuimini-top-content {
		float: right;
	}
	.ew-banner .layui-container {
		min-height: 500px;
	}
}
</style>

</head>
<body>
	<div class="ew-header">
		<a class="layui-logo" href="/">教学系统</a>
		<div class="ew-nav-group">
			<div class="nav-toggle">
				<i class="layui-icon layui-icon-more-vertical"></i>
			</div>
			<ul class="layui-nav" lay-filter="ew-header-nav">
				<li class="layui-nav-item layui-this"><a href="/"
					target="_self">首页</a></li>
				<li class="layui-nav-item"><a href="#" target="_blank">文档</a></li>
				<li class="layui-nav-item"><a href="${ctx}/login">登录</a></li>
			</ul>
		</div>
	</div>

	<div class="ew-banner"
		style="background-image:url(${ctx}/assets/images/index_logo.jpg);">
		<div class="layui-container">
			<div class="layuimini-top-content">
				<h1>XX智能教学系统 后台管理模板</h1>
				<p>纯粹用于教学</p>
			</div>
		</div>
	</div>

	<div class="section" nav-id="description" style="padding-bottom: 15px;">
		<div class="section-title">
			<h2>系统特性</h2>
		</div>
		<div class="layui-container">
			<div class="layui-row">

				<div class="layui-col-md4 layui-col-sm6">
					<div class="feature">
						<i class="layui-icon layui-icon-auz"></i>
						<h3>知识图谱</h3>
						基于知识图谱构建底层知识体系
					</div>
				</div>

				<div class="layui-col-md4 layui-col-sm6">
					<div class="feature">
						<i class="layui-icon layui-icon-chart-screen"></i>
						<h3>自然语言处理</h3>
						基于特定学科的自然语言模型，实现智能化的教学对话
					</div>
				</div>

				<div class="layui-col-md4 layui-col-sm6">
					<div class="feature">
						<i class="layui-icon layui-icon-transfer"></i>
						<h3>师生协助</h3>
						部分开发交给学生自主完成
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="footer-bottom copyright_info hidden-xs">
		Copyright © 2020 广州大学教育学院-教育技术系 All rights reserved. | <a
			href="#" target="_blank"></a>
	</div>

	<script type="text/javascript" src="${ctx }/assets/js/index.js"></script>

</body>
</html>
