<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>教师端功能--布置作业</title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
<%--
    <script src="${ctx}/assets/libs/tinymce/tinymce.min.js"></script>
--%>
</head>
<body>
<!-- 页面加载loading -->
<div class="page-loading">
    <div class="ball-loader">
        <span></span><span></span><span></span><span></span>
    </div>
</div>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-card-header" style="margin-bottom: 5px;"><h2>${course.name}--布置作业</h2></div>
            <!-- 表单开始 -->
            <form class="layui-form" id="formBasForm" lay-filter="formBasForm">
                <input type="hidden" name="course_id" value="${course.id}">
                <input type="hidden" name="id" value="${homeWork.id}">
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-required">作业标题:</label>
                    <div class="layui-input-block">
                        <input name="title" placeholder="作业标题" value="${homeWork.title}" class="layui-input"
                               lay-verType="tips" lay-verify="required" required/>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label layui-form-required">作业类型:</label>
                    <div class="layui-input-block">
                        <c:choose>
                            <c:when test="${homeWork.type==1}">
                                <input type="radio" name="type" value="1" title="文件提交" checked="true">
                                <input type="radio" name="type" value="2" title="知识图谱">
                            </c:when>
                            <c:otherwise>
                                <input type="radio" name="type" value="1" title="文件提交">
                                <input type="radio" name="type" value="2" title="知识图谱" checked="true">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-required">起止日期:</label>
                    <div class="layui-input-block">
                        <input id="formBasDateSel" value="" name="date" placeholder="请选择开始和结束日期" class="layui-input icon-date"
                               autocomplete="off" lay-verType="tips" lay-verify="required" required/>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label layui-form-required">作业描述:</label>
                    <div class="layui-input-block">
						<textarea name="content" id="content" lay-verType="tips" lay-verify="required" required>${homeWork.content}</textarea>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label layui-form-required">作业班级:</label>
                    <div class="layui-input-block">
                        <c:forEach var="classInfo" items="${course.classInfos}">
                            <input type="checkbox" value="${classInfo.id}" name="classInfos" title="${classInfo.name}"
                                   checked>
                        </c:forEach>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn" lay-filter="formBasSubmit" lay-submit>&emsp;提交&emsp;</button>
                        <button type="reset" class="layui-btn layui-btn-primary">&emsp;重置&emsp;</button>
                    </div>
                </div>
            </form>
            <!-- //表单结束 -->
        </div>
    </div>
</div>
<!-- js部分 -->
<!-- js部分 -->
<script>
    layui.extend({
        tinymce: 'tinymce/tinymce.min'
    }).use(['tinymce', 'util', 'layer'], function () {
        var tinymce = layui.tinymce
        var edit = tinymce.render({
            elem: "#content"
            , height: 600
            , width:'100%'
        });
    });
    layui.use(['layer', 'form', 'laydate'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var laydate = layui.laydate;
        /* 渲染laydate */
        laydate.render({
            elem: '#formBasDateSel',
            trigger: 'click',
            range: true
        });
        form.render();

        // 提交作业
        form.on('submit(formBasSubmit)', function (data) {
            layer.load(2);
            url = "${ctx}/teacher/homework/saveHomework";
            $.post(url, data.field, function (res) {
                layer.closeAll('loading');
                if (res.code == 200) {
                    layer.close(dIndex);
                    layer.msg(res.msg, {icon: 1});
                    insTb.reload({}, 'data');
                } else {
                    layer.msg(res.msg, {icon: 2});
                }
            }, 'json');
            return false;
        });
    });
    //tinymce
	/*tinymce.init({
		language : "zh_CN",
		menubar: false,
		height:500,
		selector: '#content',
		plugins: [
			"advlist autolink lists link image charmap print preview anchor codesample",
			"searchreplace visualblocks code fullscreen",
			"insertdatetime media table paste imagetools wordcount"
		],
		toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image codesample",
		image_advtab: true,
		table_default_styles: {
			width: '100%',
			borderCollapse: 'collapse'
		},
		image_title: false, // 是否开启图片标题设置的选择，这里设置否
		automatic_uploads: true,
		// 图片异步上传处理函数
		images_upload_handler: (blobInfo, success, failure) => {
			var xhr, formData;
			xhr = new XMLHttpRequest();
			xhr.withCredentials = false;
			xhr.open('POST', '${ctx}/teacher/homework/homeworkUploads');

			xhr.onload = function () {
				var json;
				if (xhr.status !== 200) {
					failure('HTTP Error: ' + xhr.status);
					return;
				}
				json = JSON.parse(xhr.responseText);
				// if (!json || typeof json.location !== 'string') {
				// 	failure('Invalid JSON: ' + xhr.responseText);
				// 	return;
				// }
				success(json.location);
			};

			formData = new FormData();
			formData.append('file', blobInfo.blob(), blobInfo.filename());
			xhr.send(formData);
		}
	});*/
</script>

</body>
</html>