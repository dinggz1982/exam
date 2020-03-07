<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理</title>
<%@include file="/WEB-INF/views/include/head.jsp" %>
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
                    <img src="assets/images/head.png" class="layui-nav-img">
                    <cite>管理员</cite>
                </a>
                <dl class="layui-nav-child">
                    <dd lay-unselect><a ew-href="page/template/user-info.html">个人中心</a></dd>
                    <dd lay-unselect><a ew-event="psw">修改密码</a></dd>
                    <hr>
                    <dd lay-unselect><a ew-event="logout" data-url="page/template/login.html">退出</a></dd>
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
                        <dd><a lay-href="page/console/console.html">控制台</a></dd>
                        <dd><a lay-href="page/console/workplace.html">工作台</a></dd>
                        <dd><a lay-href="page/console/dashboard.html">分析页</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a><i class="layui-icon layui-icon-set"></i>&emsp;<cite>系统管理</cite></a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="${ctx }/system/user/index">用户管理</a></dd>
                        <dd><a lay-href="${ctx }/system/role/index">角色管理</a></dd>
                        <dd><a lay-href="${ctx }/system/resource/index">权限管理</a></dd>
                        <dd><a lay-href="${ctx }/system/log/index">操作日志</a></dd>
                    </dl>
                </li>
                 <li class="layui-nav-item">
                    <a><i class="layui-icon layui-icon-set"></i>&emsp;<cite>学校基础数据</cite></a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="${ctx }/profile/school/index">学校管理</a></dd>
                        <dd><a lay-href="${ctx }/profile/college/index">学院管理</a></dd>
                        <dd><a lay-href="${ctx }/profile/major/index">专业管理</a></dd>
                        <dd><a lay-href="${ctx }/profile/major/index">专业管理</a></dd>
                    </dl>
                </li>
                
                
                <li class="layui-nav-item">
                    <a><i class="layui-icon layui-icon-template"></i>&emsp;<cite>模板页面</cite></a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a>表单页</a>
                            <dl class="layui-nav-child">
                                <dd><a lay-href="page/template/form/form-advance.html">复杂表单</a></dd>
                                <dd><a lay-href="page/template/form/form-step.html">分步表单</a></dd>
                            </dl>
                        </dd>
                        <dd>
                            <a>表格页</a>
                            <dl class="layui-nav-child">
                                <dd><a lay-href="page/template/table/table-basic.html">数据表格</a></dd>
                                <dd><a lay-href="page/template/table/table-advance.html">复杂查询</a></dd>
                                <dd><a lay-href="page/template/table/table-img.html">表格缩略图</a></dd>
                            </dl>
                        </dd>
                        <dd>
                            <a>错误页</a>
                            <dl class="layui-nav-child">
                                <dd><a lay-href="page/template/error/error-500.html">500</a></dd>
                                <dd><a lay-href="page/template/error/error-404.html">404</a></dd>
                                <dd><a lay-href="page/template/error/error-403.html">403</a></dd>
                            </dl>
                        </dd>
                        <dd><a lay-href="page/template/user-info.html">个人中心</a></dd>
                        <dd><a href="page/template/login.html" target="_blank">登录页面</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a><i class="layui-icon layui-icon-component"></i>&emsp;<cite>扩展组件</cite></a>
                    <dl class="layui-nav-child">
                        <dd>
                            <a>常用组件</a>
                            <dl class="layui-nav-child">
                                <dd><a lay-href="page/plugin/basic/dialog.html">弹窗扩展</a></dd>
                                <dd><a lay-href="page/plugin/basic/dropdown.html">下拉菜单</a></dd>
                                <dd><a lay-href="page/plugin/basic/notice.html">消息通知</a></dd>
                                <dd><a lay-href="page/plugin/basic/tagsInput.html">标签输入</a></dd>
                                <dd><a lay-href="page/plugin/basic/cascader.html">级联选择器</a></dd>
                            </dl>
                        </dd>
                        <dd>
                            <a>进阶组件</a>
                            <dl class="layui-nav-child">
                                <dd><a lay-href="page/plugin/advance/printer.html">打印插件</a></dd>
                                <dd><a lay-href="page/plugin/advance/split.html">分割面板</a></dd>
                                <dd><a lay-href="page/plugin/advance/formX.html">表单扩展</a></dd>
                                <dd><a lay-href="page/plugin/advance/tableX.html">表格扩展</a></dd>
                                <dd><a lay-href="page/plugin/advance/dataGrid.html">数据列表</a></dd>
                                <dd><a lay-href="page/plugin/advance/contextMenu.html">鼠标右键菜单</a></dd>
                            </dl>
                        </dd>
                        <dd>
                            <a>开源组件</a>
                            <dl class="layui-nav-child">
                                <dd><a lay-href="page/plugin/other/circleProgress.html">圆形进度条</a></dd>
                                <dd><a lay-href="page/plugin/other/editor.html">富文本编辑器</a></dd>
                                <dd><a lay-href="page/plugin/other/mousewheel.html">鼠标滚轮监听</a></dd>
                                <dd><a lay-href="page/plugin/other/other.html">其他开源组件</a></dd>
                            </dl>
                        </dd>
                        <dd><a lay-href="page/plugin/more.html">更多扩展</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a><i class="layui-icon layui-icon-app"></i>&emsp;<cite>经典实例</cite></a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="page/example/dialog.html">弹窗实例</a></dd>
                        <dd><a lay-href="page/example/syxm.html">课程管理</a></dd>
                        <dd><a lay-href="page/example/fullcalendar1.html">排课管理</a></dd>
                        <dd><a lay-href="page/example/form.html">添加试题</a></dd>
                        <dd><a lay-href="page/example/file.html">文件管理</a></dd>
                        <dd><a lay-href="page/example/table-crud.html">表格CRUD</a></dd>
                        <dd><a href="page/example/side-more.html" target="_blank">多系统模式</a></dd>
                        <dd><a href="page/example/side-ajax.html" target="_blank">Ajax侧边栏</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a><i class="layui-icon layui-icon-release"></i>&emsp;<cite>LayUI组件</cite></a>
                    <dl class="layui-nav-child">
                        <dd><a lay-href="https://www.layui.com/demo/button.html">组件演示</a></dd>
                        <dd><a lay-href="https://www.layui.com/doc/element/button.html#use">layui文档</a></dd>
                        <dd><a lay-href="https://layer.layui.com/">layer弹窗组件</a></dd>
                        <dd><a lay-href="https://www.layui.com/laydate/">laydate日期组件</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a><i class="layui-icon layui-icon-unlink"></i>&emsp;<cite>多级菜单</cite></a>
                    <dl class="layui-nav-child">
                        <dd><a>二级菜单</a></dd>
                        <dd>
                            <a>二级菜单</a>
                            <dl class="layui-nav-child">
                                <dd><a>三级菜单</a></dd>
                                <dd>
                                    <a>三级菜单</a>
                                    <dl class="layui-nav-child">
                                        <dd><a>四级菜单</a></dd>
                                        <dd>
                                            <a>四级菜单</a>
                                            <dl class="layui-nav-child">
                                                <dd><a>五级菜单</a></dd>
                                                <dd>
                                                    <a lay-href="https://baidu.com">百度一下</a>
                                                </dd>
                                            </dl>
                                        </dd>
                                    </dl>
                                </dd>
                            </dl>
                        </dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a lay-href="//baidu.com"><i class="layui-icon layui-icon-unlink"></i>&emsp;<cite>一级菜单</cite></a>
                </li>
            </ul>
        </div>
    </div>

    <!-- 主体部分 -->
    <div class="layui-body"></div>
    <!-- 底部 -->
    <div class="layui-footer layui-text">
        copyright © 2019 <a href="http://easyweb.vip" target="_blank">easyweb.vip</a> all rights reserved.
        <span class="pull-right">Version 3.1.6</span>
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
            menuPath: 'page/console/console.html',
            menuName: '<i class="layui-icon layui-icon-home"></i>'
        });

    });
</script>
</body>
</html>