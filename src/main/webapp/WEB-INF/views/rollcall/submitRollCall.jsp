<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>课堂点名</title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
<!-- 正文开始 -->
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-header" style="margin-bottom: 5px;"><h2>课堂点名+提问</h2></div>
        <div class="layui-card-body">
            <h2>${rollcall.classInfo.name}/${rollcall.course.name}/第${rollcall.week}周</h2>
            <form method="post" class="layui-form" lay-filter="rollcallForm" style="max-width: 960px;">
                <input type="hidden" name="rollcall" value="${rollcall.id}">
                <c:forEach var="student" items="${users }">
                    <div class="layui-form-item">
                        <input id="userid" name="userid" value="${student.id}" type="hidden">
                        <label class="layui-form-label" style="width: 200px;">学生：<b>${student.username}</b></label>
                        <div class="layui-input-block">
                            <input type="radio" name="status" lay-filter="raQT" name="type" value="1" title="上课" checked>
                            <input type="radio" name="status" lay-filter="raQT" name="type" value="2" title="迟到">
                            <input type="radio" name="status" lay-filter="raQT" name="type" value="3" title="早退">
                            <input type="radio" name="status" lay-filter="raQT" name="type" value="4" title="旷课">
                            <input type="radio" name="status" lay-filter="raQT" name="type" value="5" title="回答正确">
                            <input type="radio" name="status" lay-filter="raQT" name="type" value="6" title="回答错误">
                            <input type="radio" name="status" lay-filter="raQT" name="type" value="7" title="没回答">
                        </div>
                    </div>
                </c:forEach>
                <div class="layui-form-item">
                    <div class="layui-input-block text-center">
                        <button class="layui-btn" lay-filter="submitRollCall" lay-submit>&emsp;提交&emsp;</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    layui.use(['layer', 'form'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        form.on('submit(submitRollCall)', function (data) {
            //layer.msg("表单验证通过", {icon: 1});
            //console.log(data);
            //提交表单
            layer.load(2);
            $.post('${ctx}/submitRollCall', data.field, function (res) {
                layer.closeAll('loading');
                if (res.code == 200) {
                    //layer.close(dIndex);
                    layer.msg(res.msg, {icon: 1});
                    //insTb.reload({}, 'data');
                } else {
                    layer.msg(res.msg, {icon: 2});
                }
            }, 'json');
            return false;
        });
    });

</script>
</body>
</html>
