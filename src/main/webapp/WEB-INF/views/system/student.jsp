<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理</title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
    <link rel="stylesheet" href="${ctx }/assets/module/admin.css?v=316">
    <style>
        /** 应用快捷块样式 */
        .console-app-group {
            padding: 16px;
            border-radius: 4px;
            text-align: center;
            background-color: #fff;
            cursor: pointer;
            display: block;
        }

        .console-app-group .console-app-icon {
            width: 32px;
            height: 32px;
            line-height: 32px;
            margin-bottom: 6px;
            display: inline-block;
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            font-size: 32px;
            color: #69c0ff;
        }

        .console-app-group:hover {
            box-shadow: 0 0 15px rgba(0, 0, 0, .08);
        }

        /** //应用快捷块样式 */

        /** 小组成员 */
        .console-user-group {
            position: relative;
            padding: 10px 0 10px 60px;
        }

        .console-user-group .console-user-group-head {
            width: 32px;
            height: 32px;
            position: absolute;
            top: 50%;
            left: 12px;
            margin-top: -16px;
            border-radius: 50%;
        }

        .console-user-group .layui-badge {
            position: absolute;
            top: 50%;
            right: 8px;
            margin-top: -10px;
        }

        .console-user-group .console-user-group-name {
            line-height: 1.2;
        }

        .console-user-group .console-user-group-desc {
            color: #8c8c8c;
            line-height: 1;
            font-size: 12px;
            margin-top: 5px;
        }

        /** 卡片轮播图样式 */
        .admin-carousel .layui-carousel-ind {
            position: absolute;
            top: -41px;
            text-align: right;
        }

        .admin-carousel .layui-carousel-ind ul {
            background: 0 0;
        }

        .admin-carousel .layui-carousel-ind li {
            background-color: #e2e2e2;
        }

        .admin-carousel .layui-carousel-ind li.layui-this {
            background-color: #999;
        }

        /** 广告位轮播图 */
        .admin-news .layui-carousel-ind {
            height: 45px;
        }

        .admin-news a {
            display: block;
            line-height: 70px;
            text-align: center;
        }

        /** 最新动态时间线 */
        .layui-timeline-dynamic .layui-timeline-item {
            padding-bottom: 0;
        }

        .layui-timeline-dynamic .layui-timeline-item:before {
            top: 16px;
        }

        .layui-timeline-dynamic .layui-timeline-axis {
            width: 9px;
            height: 9px;
            left: 1px;
            top: 7px;
            background-color: #cbd0db;
        }

        .layui-timeline-dynamic .layui-timeline-axis.active {
            background-color: #0c64eb;
            box-shadow: 0 0 0 2px rgba(12, 100, 235, .3);
        }

        .dynamic-card-body {
            box-sizing: border-box;
            overflow: hidden;
        }

        .dynamic-card-body:hover {
            overflow-y: auto;
            padding-right: 9px;
        }

        /** 优先级徽章 */
        .layui-badge-priority {
            border-radius: 50%;
            width: 20px;
            height: 20px;
            padding: 0;
            line-height: 18px;
            border-width: 2px;
            font-weight: 600;
        }
    </style>
</head>
<body>
<!-- 页面加载loading -->
<div class="page-loading">
    <div class="ball-loader">
        <span></span><span></span><span></span><span></span>
    </div>
</div>
<!-- 正文开始 -->
<div class="layui-fluid ew-console-wrapper">
    <!-- 快捷方式 -->
    <div class="layui-row layui-col-space15">
        <div class="layui-col-sm6" style="padding-bottom: 0;">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-xs6 layui-col-sm3">
                    <div class="console-app-group" ew-href="page/system/user.html" ew-title="用户管理">
                        <i class="console-app-icon layui-icon layui-icon-group"
                           style="font-size: 26px;padding-top: 3px;margin-right: 6px;"></i>
                        <div class="console-app-name">用户</div>
                    </div>
                </div>
                <div class="layui-col-xs6 layui-col-sm3">
                    <div class="console-app-group">
                        <i class="console-app-icon layui-icon layui-icon-chart" style="color: #95de64;"></i>
                        <div class="console-app-name">分析</div>
                    </div>
                </div>
                <div class="layui-col-xs6 layui-col-sm3">
                    <div class="console-app-group">
                        <i class="console-app-icon layui-icon layui-icon-email"
                           style="color: #5cdbd3;font-size: 36px;"></i>
                        <div class="console-app-name">消息</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="layui-row layui-col-space15">
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-header">我的作业</div>
            <table id="myHomework" lay-filter="myHomework"></table>
        </div>
    </div>
</div>
<!-- 表格操作列 -->
<script type="text/html" id="tableBarMyHomework">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">查看并完成作业</a>
    <a class="layui-btn layui-btn-xs" lay-event="showAll">查看全班汇总</a>
</script>
<script>
    layui.use(['index','table','util'], function () {
        var $ = layui.jquery;
        var util = layui.util;
        var table = layui.table;

        // 渲染表格
        var insTb = table.render({
            elem: '#myHomework',//要渲染的表格id
            url: '${ctx}/student/myHomework/list.json',
            page: true,
            toolbar: true,
            cellMinWidth: 100,
            cols: [[
                {type: 'numbers'},
                {field: 'title', sort: true, title: '作业标题',templet: function (d) {
                        return d.homeWork.title;
                    }
                },
                {field: 'courseName', sort: true, title: '课程名',templet: function (d) {
                        return d.homeWork.course.name;
                    }
                },
                {field: 'status', sort: true, title: '进展',templet: function (d) {
                        var status = "";
                        if(d.status==0){
                            status = "未开始";
                        }
                        if(d.status==1){
                            status = "未批阅";
                        }
                        if(d.status==2){
                            status = "已批阅";
                        }
                        return status;
                    }
                },
                {
                    field: 'createTime', sort: true, templet: function (d) {
                        return util.toDateString(d.homeWork.createTime);
                    }, title: '创建时间'
                },
                {align: 'center', toolbar: '#tableBarMyHomework', title: '操作', minWidth: 200}
            ]]
        });

        // 工具条点击事件
        table.on('tool(myHomework)', function(obj) {
            var data = obj.data;
            var layEvent = obj.event;
            if (layEvent === 'edit') { // 查看并完成作业
                var type = data.homeWork.evalutionType;
                if(type==1){
                    layui.use(['index'], function () {
                        var index = layui.index;
                        index.openTab({
                            title: '查看作业',
                            url: '${ctx}/student/finishMyHomework/'+data.homeWork.type+"/"+data.id,
                            end: function() {
                            }
                        });
                    });
                }else{
                    //课堂测评
                    window.open('${ctx}/student/finishMyCourseEvalution/'+data.homeWork.type+'/'+data.id);
                }

            }else if(layEvent === 'showAll'){
                layui.use(['index'], function () {
                    var index = layui.index;
                    index.openTab({
                        title: '查看全班汇总',
                        url: '${ctx}/teacher/homework/showAll/'+data.homeWork.id,
                        end: function() {
                        }
                    });
                });
            }
        });
    });


</script>
</body>
</html>