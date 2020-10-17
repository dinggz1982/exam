<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的代码</title>
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
                                   style="margin-right: 20px">${currentUser.realname}</span> 试题：<span
                        class="layui-badge layui-bg-blue" style="margin-right: 20px">${problem.title}</span>
                    <br>
                </div>
            </div>
        </div>

        <div>
            <table class="layui-table">
                <colgroup>
                    <col width="30">
                    <col width="250">
                    <col width="100">
                    <col width="100">
                    <col width="100">
                </colgroup>
                <thead>
                <tr>
                    <th>序号</th>
                    <th>提交时间</th>
                    <th>是否通过</th>
                    <th>测评状态</th>
                    <th>编译信息</th>
                    <th>测评结果</th>

                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${submissions}" var="submission" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${submission.submitTime}</td>
                        <td>
                            <c:choose>
                                <c:when test="${submission.acceptNumber eq submission.testNumber}">
                                    通过
                                </c:when>
                                <c:otherwise>没通过</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${submission.status ==1 }">
                                    等待测评
                                </c:when>
                                <c:when test="${submission.status ==2 }">
                                    正在测评
                                </c:when>
                                <c:when test="${submission.status ==3 }">
                                    完成测评
                                </c:when>
                                <c:otherwise>其他状态</c:otherwise>
                            </c:choose>
                        </td>
                        <td>${submission.compileInfo}</td>
                        <td>${submission.judgeResult}</td>
                        <td><a class="layui-btn layui-btn-primary layui-btn-xs"
                               onclick="viewCode(${submission.id})">查看代码</a>

                        </td>
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
    function viewCode(id) {
        var index = layer.open({
            type: 2
            ,title: '查看'
            ,area: ['60%', '100%']
            ,shade: false
            ,maxmin: true
            ,offset: 'rt'
            ,content: '${ctx}/showCode/'+id
            ,anim: 2
            ,zIndex: layer.zIndex //重点1
            ,success: function(layero){
                layer.setTop(layero); //重点2
            }
        });
    }


</script>
</body>
</html>