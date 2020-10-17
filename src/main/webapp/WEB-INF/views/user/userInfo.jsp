<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息维护</title>
<%@include file="/WEB-INF/views/include/head.jsp" %>
 <style>
        .user-info-head {
            width: 110px;
            height: 110px;
            position: relative;
            display: inline-block;
            border-radius: 50%;
            border: 2px solid #eee;
        }

        .user-info-head:hover:after {
            content: '\e624';
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            color: #eee;
            background: rgba(0, 0, 0, 0.5);
            font-family: layui-icon;
            font-size: 28px;
            font-style: normal;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
            cursor: pointer;
            line-height: 110px;
            border-radius: 50%;
        }

        .user-info-head img {
            width: 110px;
            height: 110px;
            border-radius: 50%;
        }

        .info-list-item {
            position: relative;
            padding-bottom: 8px;
        }

        .info-list-item > .layui-icon {
            position: absolute;
        }

        .info-list-item > p {
            padding-left: 30px;
        }

        .dash {
            border-bottom: 1px dashed #ccc;
            margin: 15px 0;
        }

        .bd-list-item {
            padding: 14px 0;
            border-bottom: 1px solid #e8e8e8;
            position: relative;
        }

        .bd-list-item .bd-list-item-img {
            width: 48px;
            height: 48px;
            line-height: 48px;
            margin-right: 12px;
            display: inline-block;
            vertical-align: middle;
        }

        .bd-list-item .bd-list-item-content {
            display: inline-block;
            vertical-align: middle;
        }

        .bd-list-item .bd-list-item-lable {
            margin-bottom: 4px;
            color: #333;
        }

        .bd-list-item .bd-list-item-oper {
            position: absolute;
            right: 0;
            top: 50%;
            text-decoration: none !important;
            cursor: pointer;
            transform: translateY(-50%);
        }

        .user-info-form .layui-form-item {
            margin-bottom: 25px;
        }
    </style>
</head>
<body>
<!-- 加载动画 -->
<div class="page-loading">
    <div class="ball-loader">
        <span></span><span></span><span></span><span></span>
    </div>
</div>

<!-- 正文开始 -->
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <!-- 左 -->
        <div class="layui-col-sm12 layui-col-md3">
            <div class="layui-card">
                <div class="layui-card-body" style="padding: 25px;">
                    <div class="text-center layui-text">
                        <div class="user-info-head" id="imgHead">
                            <img src="${currentUser.img }"/>
                        </div>
                        <h2 style="padding-top: 20px;">${currentUser.username }</h2>
                       <%-- <p style="padding-top: 8px;">海纳百川，有容乃大</p>--%>
                    </div>
                    <div class="layui-text" style="padding-top: 30px;">
                        <div class="info-list-item">
                            <i class="layui-icon layui-icon-username"></i>
                            <p>${currentUser.realname }</p>
                        </div>
                        <div class="info-list-item">
                            <i class="layui-icon layui-icon-release"></i>
                            <p>广州大学-教育技术-程序入门菜鸟</p>
                        </div>
                        <div class="info-list-item">
                            <i class="layui-icon layui-icon-location"></i>
                            <p>广东省广州市</p>
                        </div>
                    </div>
                    <div class="dash"></div>
                    <h3>标签</h3>
                    <div class="layui-badge-list" style="padding-top: 6px;">
                        <span class="layui-badge layui-bg-gray">很有想法的</span>
                        <span class="layui-badge layui-bg-gray">编程菜鸟</span>
                    </div>
                </div>
            </div>
        </div>
        <!-- 右 -->
        <div class="layui-col-sm12 layui-col-md9">
            <div class="layui-card">
                <div class="layui-card-body">

                    <div class="layui-tab layui-tab-brief" lay-filter="userInfoTab">
                        <ul class="layui-tab-title">
                            <li class="layui-this">知识地图</li>
                            <li>智能推荐</li>
                        </ul>
                        <div class="layui-tab-content">
                            <div class="layui-tab-item layui-show">

                               正在开发中

                            </div>
                            <div class="layui-tab-item" style="padding: 6px 25px 30px 25px;">

                                <div class="bd-list layui-text">
                                    正在开发中
                                </div>

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>

    </div>
</div>
<script>
    layui.use(['layer', 'form', 'element', 'admin'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var element = layui.element;
        var admin = layui.admin;

        // 选择头像
        $('#imgHead').click(function () {
            admin.cropImg({
                imgSrc: $('#imgHead>img').attr('src'),
                onCrop: function (res) {
                    $('#imgHead>img').attr('src', res);
                   	//更新用户头像
                   	layer.load(2);
                        $.post('${ctx}/user/uploadImg', {"res":res}, function (data) {
                            layer.closeAll('loading');
                            if (data.code == 200) {
                                layer.close(1);
                                layer.msg(data.msg, {icon: 1});
                            } else {
                                layer.msg(data.msg, {icon: 2});
                            }
                        }, 'json');
                }
            });
        });

    });
</script>
</body>

</html>