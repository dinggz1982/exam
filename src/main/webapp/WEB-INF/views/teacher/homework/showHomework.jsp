<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${course.name}的作业</title>
<%@include file="/WEB-INF/views/include/head.jsp"%>
	<style>
		a {
			cursor:pointer;
		}
	</style>
</head>
<body>
	<!-- 页面加载loading -->
	<div class="page-loading">
		<div class="ball-loader">
			<span></span><span></span><span></span><span></span>
		</div>
	</div>
	<!-- 正文开始 -->
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-card-body table-tool-mini full-table">
                <div class="layui-card-header" style="margin-bottom: 5px;"><h2>${course.name}</h2></div>
				<div class="layui-form toolbar">
					<div class="layui-form-item">
						<div class="layui-inline" style="padding-right: 110px;">
							<%--<button class="layui-btn icon-btn"
								lay-filter="formSubSearchCourse" lay-submit>
								<i class="layui-icon">&#xe615;</i>搜索
							</button>--%>
							<button id="btnAddHomeWork" class="layui-btn icon-btn">
								<i class="layui-icon">&#xe654;</i>布置作业
							</button>
						</div>
					</div>
				</div>
				<table id="tableCourse" lay-filter="tableCourse"></table>
			</div>
		</div>
	</div>

	<!-- 表格操作列 -->
	<script type="text/html" id="tableBarCourse">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="edit">修改</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看作业详细情况</a>
</script>
	<!-- 表单弹窗 -->
	<script type="text/html" id="modelCourse">
    <form id="modelCourseForm" lay-filter="modelCourseForm" class="layui-form model-form">
        <input name="id" type="hidden"/>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">课程名称</label>
            <div class="layui-input-block">
                <input name="name" placeholder="请输入课程名称" type="text" class="layui-input" maxlength="20"
                       lay-verType="tips" lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label layui-form-required">课元</label>
            <div class="layui-input-block">
               	<select name="subject_id">
					<c:forEach items="${subjects }" var="subject">
						<option value="${subject.id}">${subject.name }</option>
					</c:forEach>
				</select>
            </div>
        </div>
		<div class="layui-form-item">
            <label class="layui-form-label layui-form-required">学期</label>
            <div class="layui-input-block">
               	<select name="study_team_id">
					<c:forEach items="${studyTeams }" var="studyTeam">
						<option value="${studyTeam.id}">${studyTeam.name }</option>
					</c:forEach>
				</select>
            </div>
        </div>
		<div class="layui-form-item">
            <label class="layui-form-label layui-form-required">授课班级</label>
            <div class="layui-input-block">
               	<ul id="classTree" class="ztree"></ul>
            </div>
        </div>
		<div class="layui-form-item">
            <label class="layui-form-label layui-form-required">课时</label>
            <div class="layui-input-block">
                <input name="classHour" placeholder="请输入课时" type="text" class="layui-input" maxlength="20"
                       lay-verType="tips" lay-verify="required" required/>
            </div>
        </div>
        <div class="layui-form-item text-right">
            <button class="layui-btn layui-btn-primary" type="button" ew-event="closePageDialog">取消</button>
            <button class="layui-btn" lay-filter="modelSubmitCourse" lay-submit>保存</button>
        </div>
    </form>
</script>
	<!-- js部分 -->
	<!-- js部分 -->
	<script>
		layui.use([ 'layer', 'form', 'table', 'util', 'admin', 'zTree' ], function() {
			var $ = layui.jquery;
			var layer = layui.layer;
			var form = layui.form;
			var table = layui.table;
			var util = layui.util;
			var admin = layui.admin;
	
			// 渲染表格
			var insTb = table.render({
				elem : '#tableCourse', //根据课程信息显示作业
				url : '${ctx}/homework/${course.id}/list.json',
				page : true,
				toolbar : true,
				cellMinWidth : 100,
				cols : [ [
					{
						type : 'numbers'
					},
					{
						field : 'title',
						sort : true,
						title : '作业标题',
						templet:function(d){
							return '<a lay-href="#" lay-event="show">' + d.title + '</a>'
						}
					},
					{
						field : 'classInfo',
						sort : true,
						title : '班级',
						templet: function (d) {
							var classInfos="";
							for (var classInfo of d.classInfos) { // 遍历Set
								classInfos=classInfo.name + ",";
							}
							return classInfos
						}
					},
					{
						align : 'center',
						toolbar : '#tableBarCourse',
						title : '操作',
						minWidth : 200
					}
				] ]
			});
	
			// 添加
			$('#btnAddCourse').click(function() {
				showEditModel();
			});

			// 布置作业
			$('#btnAddHomeWork').click(function() {
				layui.use(['index'], function () {
					var index = layui.index;
					index.openTab({
						title: '布置${course.name}作业',
						url: '${ctx}/teacher/homework/publishHomeWork/${course.id}',
						end: function() {
						}
					});
				});
			});
	
			// 搜索
			form.on('submit(formSubSearchCourse)', function(data) {
				insTb.reload({
					where : data.field
				}, 'data');
			});
	
			// 工具条点击事件
			table.on('tool(tableCourse)', function(obj) {
				var data = obj.data;
				var layEvent = obj.event;
				if (layEvent === 'edit') { // 修改
					showEditModel(data);
				}
				else if(layEvent=="show"){
					layui.use(['index'], function () {
						var index = layui.index;
						index.openTab({
							title: '作业：' + data.title + '详细情况',
							url: '/teacher/homework/' + data.id,
							end: function () {
								//insTb.reload();
							}
						});
					});
				}
				else if (layEvent === 'del') { // 删除
					doDel(data.id, data.title);
				} else if (layEvent === 'detail') { // 查看作业详细情况
					layui.use(['index'], function () {
						var index = layui.index;
						index.openTab({
							title: data.title+'详细情况',
							url: '/teacher/homework/result/'+data.id,
							end: function() {
								//insTb.reload();
							}
						});
					});
				}
			});
			// 删除作业
			function doDel(id, title) {
				layer.confirm('确定要删除“' + title + '”吗？', {
					skin: 'layui-layer-admin',
					shade: .1
				}, function (i) {
					layer.close(i);
					layer.load(2);
					$.post('${ctx}/teacher/homework/delete/'+id, {}, function (res) {
						layer.closeAll('loading');
						if (res.code == 200) {
							layer.msg(res.msg, {icon: 1});
							insTb.reload({}, 'data');
						} else {
							layer.msg(res.msg, {icon: 2});
						}
					}, 'json');
				});
			}
		// 显示表单弹窗
        function showEditModel(course) {
			layui.use(['index'], function () {
				var index = layui.index;
				index.openTab({
					title: '布置${course.name}作业',
					url: '${ctx}/teacher/homework/publishHomeWork/${course.id}?homework_id='+course.id,
					end: function() {
					}
				});
			});
        }
    });
</script>

</body>
</html>