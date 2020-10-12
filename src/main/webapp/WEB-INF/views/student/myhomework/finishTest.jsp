<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生端功能--完成编程测试作业</title>
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
                    <br>学生姓名：<span class="layui-badge layui-bg-green"
                                   style="margin-right: 20px">${myHomeWork.student.user.realname}</span> 来自课程：<span
                        class="layui-badge layui-bg-blue" style="margin-right: 20px">${homework.course.name}</span>发布/更新时间：<span
                        class="layui-badge-rim" style="margin-right: 20px">${myHomeWork.updateTime}</span>
                    <br>
                </div>
            </div>
        </div>

        <div>
            <table class="layui-table">
                <colgroup>
                    <col width="50">
                    <col width="200">
                    <col width="50">
                    <col width="50">
                    <col width="250">
                </colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>试题标题</th>
                    <th>是否通过</th>
                    <th>提交次数</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${problems}" var="myproblem" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${myproblem.problem.title}</td>
                        <td>
                            <c:choose>
                                <c:when test="${myproblem.pass}">
                                    通过
                                </c:when>
                                <c:otherwise>没通过</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${myproblem.submissionTimes}</td>
                        <td><a class="layui-btn layui-btn-primary layui-btn-xs"
                               onclick="view('${myproblem.problem.title}',${myproblem.problem.id})">查看试题</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

        </div>
    </div>
</div>

</div>
</div>
<script>

        //查看作业试题
        function view(title,id) {
            layui.use(['index'], function () {
                var index = layui.index;
                index.openTab({
                    title: '试题：'+title,
                    url: '${ctx}/myHomeWorkForProgramming/${myHomeWork.id}/'+id,
                    end: function() {
                    }
                });
            });
        }

</script>
</body>
</html>