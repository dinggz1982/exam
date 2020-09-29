<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<%@include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="${ctx }/assets/module/admin.css?v=316">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <!-- 头部 -->
    <div class="layui-header">
        <div class="layui-logo">
            <img src="${ctx }/assets/images/exam.png"/>
            <cite>&nbsp;考试系统</cite>
        </div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item" lay-unselect>
                <a ew-event="flexible" title="侧边伸缩"><i class="layui-icon layui-icon-shrink-right"></i></a>
            </li>
            <li class="layui-nav-item" lay-unselect>
                <a ew-event="refresh" title="刷新"><i class="layui-icon layui-icon-refresh-3"></i></a>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item" lay-unselect>
                <a ew-event="message" title="消息">
                    <i class="layui-icon layui-icon-notice"></i>
                    <span class="layui-badge-dot"></span>
                </a>
            </li>
            <li class="layui-nav-item" lay-unselect>
                <a ew-event="note" title="便签"><i class="layui-icon layui-icon-note"></i></a>
            </li>
            <li class="layui-nav-item layui-hide-xs" lay-unselect>
                <a ew-event="fullScreen" title="全屏"><i class="layui-icon layui-icon-screen-full"></i></a>
            </li>
            <li class="layui-nav-item layui-hide-xs" lay-unselect>
                <a ew-event="lockScreen" title="锁屏"><i class="layui-icon layui-icon-password"></i></a>
            </li>
            <li class="layui-nav-item" lay-unselect>
                <a>
                    <img src="${currentUser.img }" class="layui-nav-img">
                    <cite>${currentUser.username}</cite>
                </a>
                <dl class="layui-nav-child">
                    <dd lay-unselect><a ew-href="${ctx }/user/userInfo">个人中心</a></dd>
                    <dd lay-unselect><a ew-event="psw">修改密码</a></dd>
                    <hr>
                    <dd lay-unselect><a ew-event="logout" data-url="/logout">退出</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item" lay-unselect>
                <a ew-event="theme" title="主题"><i class="layui-icon layui-icon-more-vertical"></i></a>
            </li>
        </ul>
    </div>

    <!-- 侧边栏 -->
    <div class="layui-side">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree arrow2" lay-filter="admin-side-nav" lay-shrink="all">
                <li class="layui-nav-item">
                    <a><i class="layui-icon layui-icon-home"></i>&emsp;<cite>Dashboard</cite></a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="${ctx}/main">工作台</a></dd>
                    </dl>
                </li>
                <!-- 遍历当前用户的权限菜单 -->
                <c:forEach items="${resources }" var="resource">
                	 <li class="layui-nav-item">
                    <a><i class="layui-icon layui-icon-set"></i>&emsp;<cite>${resource.name }</cite></a>
                    	<c:choose>
                    		<c:when test="${!empty resource.children }">
                    		 	<dl class="layui-nav-child">
                        			<c:forEach items="${resource.children }" var="child">
                        				<dd><a lay-href="${child.url }">${child.name }</a></dd>
                        			</c:forEach>
                    		 	</dl>
                    		</c:when>
                    	</c:choose>
                </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <!-- 主体部分 -->
    <div class="layui-body"></div>
    <!-- 底部 -->
    <div class="layui-footer layui-text">
        copyright © 2019 <a href="#" target="_blank">教学系统</a> all rights reserved.
        <span class="pull-right">Version 1.0</span>
    </div>
</div>

<!-- 加载动画 -->
<div class="page-loading">
    <div class="ball-loader">
        <span></span><span></span><span></span><span></span>
    </div>
</div>

<script>
    layui.use(['index'], function () {
        var $ = layui.jquery;
        var index = layui.index;

        // 默认加载主页
        index.loadHome({
            menuPath: '${ctx}/main',
            menuName: '<i class="layui-icon layui-icon-home"></i>'
        });
    });
</script>
</body>
</html>