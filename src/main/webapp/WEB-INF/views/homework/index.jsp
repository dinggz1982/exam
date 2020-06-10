<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>教师端功能--我的课程</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
</head>
<body>
	<!-- 页面加载loading -->
	<div class="page-loading">
		<div class="ball-loader">
			<span></span><span></span><span></span><span></span>
		</div>
	</div>
	<!-- 正文开始 -->
	<!-- 正文开始 -->
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-card-header">添加作业</div>
			<div class="layui-card-body">
				<form class="layui-form" lay-filter="homework" style="max-width: 960px;">
					<input type="hidden" name="questionId" value=""/>
					<div class="layui-form-item" style="margin-top: 0;">
						<div class="layui-inline">
							<label class="layui-form-label">作业标题：</label>
							<div class="layui-input-block">
								<input name="title" placeholder="请输入作业标题" class="layui-input" lay-vertype="tips" lay-verify="required" required="">
							</div>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">作业内容：</label>
						<div class="layui-input-block">
                        <textarea name="homeworkContent" id="homeworkContent" lay-verType="tips"></textarea>
						</div>
					</div>
					<div class="layui-form-item">
						<label class="layui-form-label">相关附件：</label>
						<div class="layui-input-block">
					<div class="layui-upload">
						<button type="button" class="layui-btn layui-btn-normal" id="files">选择文件</button>
						<div class="layui-upload-list">
							<table class="layui-table">
								<thead>
								<tr><th>文件名</th>
									<th>大小</th>
									<th>状态</th>
									<th>操作</th>
								</tr></thead>
								<tbody id="uploadList"></tbody>
							</table>
						</div>
					</div>
						</div>
					</div>
					<div class="layui-form-item" style="text-align: center;margin: 0 auto">
					<button type="button" class="layui-btn" lay-filter="submit" lay-submit>提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		layui.use(['layer', 'form', 'upload', 'layedit'], function () {
			var $ = layui.jquery;
			var layer = layui.layer;
			var form = layui.form;
			var	upload = layui.upload;
			var layedit = layui.layedit;

			//var editIndex = layedit.build('content'); // 建立编辑器

			// 提交事件
			form.on('submit(submit)', function (data) {
				layer.load(2);
				$.post('${ctx}/its/homework/addHomework', data.field, function (res) {
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
			//多文件列表

			var fileListView = upload.render({
				elem: '#files'
				,url: '${ctx}/its/homework/upload' //上传接口
				,accept: 'file' //允许不同的格式
				,multiple: true //允许上传多个文件
				,choose: function(obj){
					var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
					//读取本地文件
					obj.preview(function(index, file, result){
						var tr = $(['<tr id="upload-'+ index +'">'
							,'<td>'+ file.name +'</td>'
							,'<td>'+ (file.size/1024).toFixed(1) +'kb</td>'
							,'<td>等待上传</td>'
							,'<td>'
							,'<button class="layui-btn layui-btn-xs file-reload layui-hide">重传</button>'
							,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
							,'</td>'
							,'</tr>'].join(''));

						//单个重传
						tr.find('.file-reload').on('click', function(){
							obj.upload(index, file);
						});

						//删除
						tr.find('.file-delete').on('click', function(){
							delete files[index]; //删除对应的文件
							tr.remove();
							uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
						});

						$("#uploadList").append(tr);
					});
				}
				,done: function(res, index, upload){
					if(res.result=="success"){ //上传成功
						var tr = $("#uploadList").find('tr#upload-'+ index)
								,tds = tr.children();
						tds.eq(2).html('<span style="color: #5FB878;">上传成功</span><input type="hidden" name="files" values="'+res.filePath+'"/> ');
						tds.eq(3).html('<button type="button" class="layui-btn layui-btn-xs layui-btn-danger file-delete">删除</button><a target="_blank" href="'+res.filePath+'" class="layui-btn layui-btn-xs layui-btn-normal file-reload">下载</a>'); //清空操作
						//重写删除
						tr.find('.file-delete').on('click', function(){
							delete files[index]; //删除对应的文件
							tr.remove();
							uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
						});
						//单个重传--有问题，暂且不用
						tr.find('.file-reload').on('click', function(){
							this.upload(index, file);
						});

						return delete this.files[index]; //删除文件队列已经上传成功的文件
					}else{
						layer.msg("上传失败！请联系管理员！" + res.result);
					}
					this.error(index, upload);
				}
				,error: function(index, upload){
					var tr =  $("#uploadList").find('tr#upload-'+ index)
							,tds = tr.children();
					tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
					tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
				}
			});

		});
		
	</script>

</body>
</html>