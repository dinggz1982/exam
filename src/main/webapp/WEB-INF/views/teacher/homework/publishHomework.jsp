<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>教师端功能--布置作业</title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
    <script src="${ctx}/assets/module/tinymce/tinymce.min.js"></script>
    <script src="${ctx}/assets/libs/jquery/jquery-3.2.1.min.js"></script>
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
                                <input type="radio" name="type" value="3" title="程序测评">
                            </c:when>
                            <c:when test="${homeWork.type==2}">
                                <input type="radio" name="type" value="1" title="文件提交" >
                                <input type="radio" name="type" value="2" title="知识图谱" checked="true">
                                <input type="radio" name="type" value="3" title="程序测评">
                            </c:when>
                            <c:otherwise>
                                <input type="radio" name="type" value="1" title="文件提交">
                                <input type="radio" name="type" value="2" title="知识图谱" >
                                <input type="radio" name="type" value="3" title="程序测评" checked="true">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label layui-form-required">作业完成的类型:</label>
                    <div class="layui-input-block">
                        <c:choose>
                            <c:when test="${homeWork.evalutionType==1}">
                                <input type="radio" name="evalutionType" value="1" title="课后作业" checked="true">
                                <input type="radio" name="evalutionType" value="2" title="课堂测评">
                            </c:when>
                            <c:otherwise>
                                <input type="radio" name="evalutionType" value="1" title="课后作业">
                                <input type="radio" name="evalutionType" value="2" title="课堂测评" checked="true">
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-required">题号:</label>
                    <div class="layui-input-block">
                        <input name="problemIds" placeholder="题号（用英文状态的;隔开）" value="${homeWork.problemIds}" class="layui-input"
                               lay-verType="tips" lay-verify="required" required/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-required">起止日期:</label>
                    <div class="layui-input-block">
                        <c:choose>
                            <c:when test="${!empty homeWork.startTime && !empty homeWork.endTime}">
                                <input id="formBasDateSel" value="<fmt:formatDate value="${homeWork.startTime}" pattern="yyyy-MM-dd"/> - <fmt:formatDate value="${homeWork.endTime}" pattern="yyyy-MM-dd"/>" name="date" placeholder="请选择开始和结束日期" class="layui-input icon-date"
                                       autocomplete="off" lay-verType="tips" lay-verify="required" required/>
                            </c:when>
                            <c:otherwise>
                                <input id="formBasDateSel" value="" name="date" placeholder="请选择开始和结束日期" class="layui-input icon-date"
                                       autocomplete="off" lay-verType="tips" lay-verify="required" required/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label layui-form-required">作业描述:</label>
                    <div class="layui-input-block">
						<textarea name="content1" id="content1" lay-verType="tips" >${homeWork.content}</textarea>
                    </div>
                </div>
                <input type="hidden" name="content" id="content">
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label layui-form-required">作业班级:</label>
                    <div class="layui-input-block">
                        <c:forEach var="classInfo" items="${course.classInfos}">
                            <input type="checkbox" value="${classInfo.id}" lay-skin="primary"  name="classInfos" title="${classInfo.name}"
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

    /*layui.use(['layer', 'form', 'laydate'], function () {

    });*/
    //tinymce
    tinyMCE = tinymce.init({
		language : "zh_CN",
		menubar: false,
		height:300,
        mode : "specific_textareas",
        editor_selector : "mceEditor",
		selector: '#content1',
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
        setup:function(editor){
            editor.on('blur',function (){
                //console.log(tinymce.get('content1').getContent());
                $("#content").html(tinymce.get('content1').getContent());
            })
        },
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
	});
    layui.use([ 'form', 'laydate'], function () {
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

            var arr = new Array();
            $("input:checkbox[name='classInfos']:checked").each(function(i){
                arr[i] = $(this).val();
            });
            data.field.classInfos = arr.join(",");//将数组合并成字符串
            console.log(data);
            data.field.content = tinymce.get('content1').getContent();
            var url = "${ctx}/teacher/homework/saveHomework";
            $.post(url, data.field, function (res) {
                layer.closeAll('loading');
                if (res.code == 200) {
                    layer.msg(res.msg, {icon: 1});
                } else {
                    layer.msg(res.msg, {icon: 2});
                }
            }, 'json');
            return false;
        });
    });
    function getHtml(editorid) {
        if (editorid == "")
            return "";
        for (var i = 0; i < editors.length; i++) {
            var editor = editors[i];
            if (editor.id == editorid)
                return editor.getBody().innerHTML;
        }
        return "";

    }
</script>

</body>
</html>