<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新增试题</title>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
    <script src="${ctx}/assets/module/tinymce/tinymce.min.js"></script>
    <script src="${ctx}/assets/libs/jquery/jquery-3.2.1.min.js"></script>
</head>
<body>
<!-- 正文开始 -->
<div class="layui-fluid">
    <div class="layui-card">
		<div class="layui-card-header" style="margin-bottom: 5px;"><h2>添加试题</h2></div>
        <div class="layui-card-body">
            <form class="layui-form" lay-filter="eQuestionForm" style="max-width: 960px;">
                <input type="hidden" name="questionId" value=""/>

                <div class="layui-form-item">
                    <label class="layui-form-label">试题类型：</label>
                    <div class="layui-input-block">
                        <%--1.填空题,2.判断题3.单选题,4.多选题5.编程题 6.程序填空题 7.选择填空题--%>
                        <input type="radio" lay-filter="raQT" name="type" value="3" title="单选题" checked>
                        <input type="radio" lay-filter="raQT" name="type" value="4" title="多选题">
                        <input type="radio" lay-filter="raQT" name="type" value="2" title="判断题">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-required">试题标题:</label>
                    <div class="layui-input-block">
                        <input name="title" placeholder="试题标题" value="" class="layui-input"
                               lay-verType="tips" lay-verify="required" required/>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label layui-form-required">分数:</label>
                    <div class="layui-input-block">
                        <input name="score" placeholder="分数" value="10" class="layui-input"
                               lay-verType="tips" lay-verify="required" required/>
                    </div>
                </div>
                <input type="hidden" name="content" id="content">
                <div class="layui-form-item">
                    <label class="layui-form-label">试题内容：</label>
                    <div class="layui-input-block">
						<textarea name="content1" id="content1" lay-verType="tips" ></textarea>
                    </div>
                </div>
                <div class="layui-form-item layui-form-text" id="eQuestionSelGroup">
                </div>
                <div class="layui-form-item layui-form-text">
                    <label class="layui-form-label">正确答案：</label>
                    <div id="qaRIGroup" class="layui-input-block">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block text-center">
                        <button ew-event="closeThisTabs" type="button" class="layui-btn layui-btn-primary">&emsp;关闭&emsp;</button>
                        <button class="layui-btn" lay-filter="eQuestionSubmit" lay-submit>&emsp;提交&emsp;</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- 不同类型题目的正确答案选项 -->
<script type="text/html" id="eQuestionSinTpl">
    <!-- 单选题 -->
    <div style="max-width: 160px;">
        <select name="answer" lay-verType="tips" lay-verify="required" required>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
            <option value="E">E</option>
            <option value="F">F</option>
        </select>
    </div>
</script>
<script type="text/html" id="eQuestionMulTpl">
    <!-- 多选题 -->
    <div style="max-width: 220px;" id="qaRIMore">
    </div>
</script>
<script type="text/html" id="eQuestionTfTpl">
    <!-- 判断题 -->
    <div style="max-width: 160px;">
        <select name="answer" lay-verType="tips" lay-verify="required" required>
            <option value="T">正确</option>
            <option value="F">错误</option>
        </select>
    </div>
</script>
<script type="text/html" id="eQuestionSelTpl">
    <label class="layui-form-label">试题选项：</label>
    <div class="layui-input-block">
        <div class="qa-xx-item">
            <span class="qa-xx-item-title">A：</span>
            <input type="text" name="itemA" placeholder="请输入选项内容" class="layui-input"
                   autocomplete="off" lay-verType="tips" lay-verify="required" required/>
        </div>
        <div class="qa-xx-item">
            <span class="qa-xx-item-title">B：</span>
            <input type="text" name="itemB" placeholder="请输入选项内容" class="layui-input"
                   autocomplete="off" lay-verType="tips" lay-verify="required" required/>
        </div>
        <div class="qa-xx-item">
            <span class="qa-xx-item-title">C：</span>
            <input type="text" name="itemC" placeholder="请输入选项内容" class="layui-input"
                   autocomplete="off" lay-verType="tips" lay-verify="required" required/>
        </div>
        <div class="qa-xx-item">
            <span class="qa-xx-item-title">D：</span>
            <input type="text" name="itemD" placeholder="请输入选项内容" class="layui-input" autocomplete="off"/>
        </div>
        <div class="qa-xx-item">
            <span class="qa-xx-item-title">E：</span>
            <input type="text" name="itemE" placeholder="请输入选项内容" class="layui-input" autocomplete="off"/>
        </div>
        <div class="qa-xx-item">
            <span class="qa-xx-item-title">F：</span>
            <input type="text" name="itemF" placeholder="请输入选项内容" class="layui-input" autocomplete="off"/>
        </div>
    </div>
</script>

<!-- js部分 -->
<script>
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
				$("#content").val(tinymce.get('content1').getContent());
			})
		},
		// 图片异步上传处理函数
		images_upload_handler: (blobInfo, success, failure) => {
			var xhr, formData;
			xhr = new XMLHttpRequest();
			xhr.withCredentials = false;
			xhr.open('POST', '${ctx}/problem/uploadImg');
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
    layui.use(['layer', 'form', 'xmSelect', 'layedit'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        var form = layui.form;
        var xmSelect = layui.xmSelect;
        var layedit = layui.layedit;

        var editIndex = layedit.build('eQuestionContentEdt'); // 建立编辑器

        // 表单提交事件
        form.on('submit(eQuestionSubmit)', function (data) {
            //layer.msg("表单验证通过", {icon: 1});
            console.log(data);
            //提交表单
            layer.load(2);
            $.post('${ctx}/problem/saveChoice', data.field, function (res) {
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
            return false;
        });

        // 试题类型切换事件
        form.on('radio(raQT)', function (data) {
            //alert(data.value);
            changeQT(data.value);
        });

        function changeQT(value, sel) {
            if (value == 3) {
                $('#qaRIGroup').html($('#eQuestionSinTpl').html());
                form.render('select', 'eQuestionForm');
                $('#eQuestionSelGroup').html($('#eQuestionSelTpl').html());
            } else if (value == 4) {
                $('#qaRIGroup').html($('#eQuestionMulTpl').html());
                xmSelect.render({
                    el: '#qaRIMore',
                    data: [
                        {name: 'A', value: 'A'},
                        {name: 'B', value: 'B'},
                        {name: 'C', value: 'C'},
                        {name: 'D', value: 'D'},
                        {name: 'E', value: 'E'},
                        {name: 'F', value: 'F'}
                    ]
                });
                $('#eQuestionSelGroup').html($('#eQuestionSelTpl').html());
            } else if (value == 2) {
                $('#qaRIGroup').html($('#eQuestionTfTpl').html());
                form.render('select', 'eQuestionForm');
                $('#eQuestionSelGroup').html('');
            }
        }

        setTimeout(function () {
            changeQT(3);
        }, 50);

    });
</script>
</body>
</html>