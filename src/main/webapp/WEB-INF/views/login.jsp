<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>考试系统登录</title>
	<%@include file="/WEB-INF/views/include/head.jsp" %>
    <script>
        if (window != top) {
            top.location.replace(location.href);
        }
    </script>
  </head>  
<body>
<div class="login-wrapper">
    <div class="login-header">
        <img src="../../assets/images/logo.png"> 考试系统
    </div>
    <div class="login-body">
        <div class="layui-card">
            <div class="layui-card-header">
                <i class="layui-icon layui-icon-engine"></i>&nbsp;&nbsp;用户登录
            </div>
            <form action="/login" method="post"  class="layui-card-body layui-form layui-form-pane">
                <div class="layui-form-item">
                    <label class="layui-form-label"><i class="layui-icon layui-icon-username"></i></label>
                    <div class="layui-input-block">
                        <input name="username" type="text" placeholder="账号" class="layui-input"
                               lay-verType="tips" lay-verify="required" required/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label"><i class="layui-icon layui-icon-password"></i></label>
                    <div class="layui-input-block">
                        <input name="password" type="password" placeholder="密码" class="layui-input"
                               lay-verType="tips" lay-verify="required" required/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <a href="javascript:;" class="layui-link">帐号注册</a>
                    <a href="javascript:;" class="layui-link pull-right">忘记密码？</a>
                </div>
                <div class="layui-form-item">
                	<input type="submit" class="layui-btn layui-btn-fluid" value="登录"/>
                </div>
                <div class="layui-form-item login-other">
                    <label>第三方登录</label>
                    <a href="javascript:;"><i class="layui-icon layui-icon-login-qq"></i></a>
                    <a href="javascript:;"><i class="layui-icon layui-icon-login-wechat"></i></a>
                    <a href="javascript:;"><i class="layui-icon layui-icon-login-weibo"></i></a>
                </div>
            </form>
        </div>
    </div>

    <div class="login-footer">
        <p>© 2020 XXX 版权所有</p>
        <p>
            <span><a href="#" target="_blank">开发文档</a></span>
        </p>
    </div>
</div>

<!-- js部分 -->
<script>
    layui.use(['layer', 'form'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;

        // 表单提交
        /* form.on('submit(login-submit)', function (obj) {
            console.log(obj.field);
            layer.msg('登录成功', {icon: 1, time: 1500}, function () {
                location.replace('../../index.html')
            });
            return false;
        }); */
    });
</script>
</body>
</html>
