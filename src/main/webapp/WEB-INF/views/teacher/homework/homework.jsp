<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>${homework.title}</title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <link rel="stylesheet" href="${ctx }/assets/css/global.css">
</head>
<body>
<div class="layui-container">
    <div class="fly-panel detail-box">
        <h1>${homeWork.title}</h1>
        </div>
    <div class="layui-inline" style="padding-right: 110px;">
        <c:if test="${currentUser.id == homeWork.teacher.id}">
            <button id="show" class="layui-btn icon-btn">
                查看作业完成情况
            </button>
        </c:if>
        <button id="fininsh" class="layui-btn icon-btn">
            完成作业
        </button>
    </div>
        <div class="detail-about">
            <a class="fly-avatar" href="#">
                <img src="${homeWork.teacher.img}" alt="${homeWork.teacher.realname}">
            </a>
            <div class="fly-detail-user">
                <a href="#" class="fly-link">
                    <cite>${homeWork.teacher.realname}</cite>
                </a>
                <span><fmt:formatDate value="${homeWork.createTime}" pattern="yyyy-MM-dd"/></span>
        </div>
        <div class="detail-hits" id="LAY_jieAdmin" data-id="123">
            <c:forEach items="${homeWork.classInfos}" var="classInfo">
                <span class="layui-btn layui-btn-xs jie-admin" type="edit">${classInfo.name}</span>
            </c:forEach>

        </div>
        </div>
        <div class="detail-body photos">
            ${homeWork.content}
            <hr>
        </div>

    </div>
</div>
<script>
    layui.use(['layer', 'form', 'table', 'util', 'admin'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        // 添加
        $('#show').click(function() {
            layui.use(['index'], function () {
                var index = layui.index;
                index.openTab({
                    title: '作业${homeWord.title}完成情况',
                    url: '${ctx}/teacher/homework/details/${homeWork.id}',
                    end: function() {
                    }
                });
            });
        });
    });

</script>
</body>
</html>
