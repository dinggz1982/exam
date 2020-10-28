<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>我的编程作业--${problem.title }</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
</head>
<body>
<div class="container">
    <br>
    <div class="layui-card" style="border:1px solid #d2d2d2">
        <div class="layui-card-body">
            <div class="layui-row">
                <div class="layui-col-md6">
                    <br>
                    <strong style='font-size:30px;'>${problem.title }</strong>&nbsp;
                    <br> <br>
                    <span class='layui-badge layui-bg-black'><i class='fa fa-clock-o'></i>&nbsp;时间限制: 1秒</span>&nbsp;&nbsp;<span
                        class='layui-badge layui-bg-black'><i class='fa fa-database'></i>&nbsp; 内存限制: 64 MB</span>
                    <span class="layui-badge layui-bg-black">提交&nbsp;&nbsp;${problem.submitcounter}</span> <span
                        class="layui-badge layui-bg-black">解决&nbsp;&nbsp;${problem.acceptcounter}</span></div>
                <div class="layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header"><span class="layui-badge layui-bg-orange">标签</span></div>
                        <div class="layui-card-body">
                                <c:forEach items="${problem.problemTags}" var="tag">
                                    ${tag.tag.name},
                                </c:forEach>
                        </div>
                    </div>
                    <div class='layui-btn-group'>
                        <button id='submitCode' class='layui-btn layui-btn-danger ' ><i
                                class='fa fa-code'></i>&nbsp;提交
                        </button>
                        <%--<a class='layui-btn ' href='problemstatus.php?id=1000'><i class='fa fa-legal'></i>&nbsp;状态</a><a
                            class='layui-btn layui-btn-primary  ' href='bbs.php?pid=1000'><i
                            class='fa fa-commenting'></i>&nbsp;讨论版</a>--%></div>
                </div>
            </div>
        </div>
    </div>
    <div class="grid-demo grid-demo-bg1" style="padding:0px 0px;">
        <div class="layui-card " style="border:1px solid #d2d2d2">
            <div class="layui-card-header tits">题目描述</div>
            <div class="layui-card-body">${programming.description }</div>
        </div>
        <div class="layui-card" style="border:1px solid #d2d2d2">
            <div class="layui-card-header tits">输入格式</div>
            <div class="layui-card-body">${programming.inputstyle }</div>
        </div>
        <div class="layui-card" style="border:1px solid #d2d2d2">
            <div class="layui-card-header tits">输出格式</div>
            <div class="layui-card-body">${programming.outputstyle }</div>
        </div>
        <c:forEach items="${programming.samples}" var="sample" varStatus="status">
            <div class='layui-card' style='position:relative;border:1px solid #d2d2d2'>
                <div class='layui-card-header tits'> 输入/输出样例${status.index+1}</div>
                <div class='layui-card-body'>输入：
                    <pre class='layui-elem-quote'>${sample.inputsample}
                    </pre>
                    输出：
                    <pre class='layui-elem-quote'>${sample.outputsample}
                    </pre>
                </div>
            </div>
            <p></p>
        </c:forEach>

        <div class='layui-card' style='border:1px solid #d2d2d2'>
            <div class='layui-card-header ' style='background-color:#F0E68C'> 提示</div>
            <div class='layui-card-body'></div>
        </div>
       <%-- <center>
            <button id='submitCode' class='layui-btn layui-btn-danger '>提交</button>
        </center>--%>
    </div>

    <br>
</div>
</body>
<script>
    layui.use(['layer', 'util'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        //监听按钮
        $('#submitCode').click(function() {
            var index = layer.open({
                type: 2
                ,title: '提交代码'
                ,area: ['60%', '100%']
                ,shade: false
                ,maxmin: true
                ,offset: 'rt'
                ,content: '${ctx}/problem/submissionCodeForHomeWork/${myHomeWorkId}/${problem.id}'
                ,anim: 2
                ,zIndex: layer.zIndex //重点1
                ,success: function(layero){
                    layer.setTop(layero); //重点2
                }
            });
        });
    });
</script>

</html>