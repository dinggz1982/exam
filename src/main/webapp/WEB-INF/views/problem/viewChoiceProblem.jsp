<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>查看试题</title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
<!-- 页面加载loading -->
<div class="page-loading">
    <div class="ball-loader">
        <span></span><span></span><span></span><span></span>
    </div>
</div>
<div style="padding: 20px; background-color: #F2F2F2;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
            <div class="layui-card">
                <div class="layui-card-header"><div class="layui-form-mid layui-word-aux">${problemChoice.description}</div></div>
                <div class="layui-card-body">
                    <c:choose>
                        <c:when test="${type==3}">
                        <table class="layui-table" id="blankTable" style="width: 100%">
                            <tr>
                                <td width="40%">选项内容</td>
                                <td width="40%">答案选项</td>
                            </tr>
                            <c:forEach items="${problemChoiceItems}" var="item">
                            <tr>
                                <td>${item.item}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.isRightAnswer}">
                                            <input type="radio" lay-skin="primary" name="isRightAnswer" checked  value="${item.id}" title="正确答案">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" lay-skin="primary" name="isRightAnswer"  value="${item.id}" title="正确答案">
                                        </c:otherwise>
                                    </c:choose>
                            </tr>
                            </c:forEach>

                        </table>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['form', 'layedit', 'laydate'], function() {
            var form = layui.form;
            form.render();
    });
</script>
</body>
</html>