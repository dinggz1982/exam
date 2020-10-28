<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${homeWork.title}作业情况</title>
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
            <div class="layui-card-header" style="text-align: center"><h1>${homeWork.title}</h1></div>
        </div>
        <div>
            <c:forEach items="${classHomeWorkForProgrammingInfos}" var="cps">
                <div style="text-align: center"><h2>${cps.classInfo.name}</h2></div>
                <table class="layui-table">
                    <tr>
                        <th>序号</th>
                        <th>姓名</th>
                        <c:forEach items="${problems}" var="problem">
                            <th title="${problem.title}">${problem.id}</th>
                        </c:forEach>
                    </tr>
                    <c:forEach items="${cps.studentHomeWorkProblems}" var="shp" varStatus="status">
                        <tr>
                            <td>${status.index+1}</td>
                            <td>${shp.student.user.realname}</td>
                            <c:forEach items="${shp.myHomeWorkProblems}" var="mpro">
                                <c:choose>
                                    <c:when test="${mpro.pass}"><td bgcolor="#4ad26a">通过(${mpro.passTimes}/${mpro.submissionTimes})</td></c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${mpro.submissionTimes>0}"><td bgcolor="#a9a9a9">FAILED(0/${mpro.submissionTimes})</td></c:when>
                                            <c:otherwise><td bgcolor="#CC0033">没做！(0/${mpro.submissionTimes})</td></c:otherwise>
                                        </c:choose>
                                        </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>

                    </c:forEach>
                </table>
            </c:forEach>
        </div>
    </div>
</div>

</div>
</div>
<script>
    //查看作业试题
    function view(title, id) {
        layui.use(['index'], function () {
            var index = layui.index;
            index.openTab({
                title: '试题：' + title,
                url: '${ctx}/myHomeWorkForProgramming/${myHomeWork.id}/' + id,
                end: function () {
                }
            });
        });
    }

    //查看我的代码
    function viewMyCode(title, id) {
        layui.use(['index'], function () {
            var index = layui.index;
            index.openTab({
                title: '我的代码：' + title,
                url: '${ctx}/viewMyCode/${myHomeWork.id}/' + id,
                end: function () {
                }
            });
        });
    }
</script>
</body>
</html>