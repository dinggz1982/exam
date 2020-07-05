<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${homework.title}</title>
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
	<div class="layui-fluid">
		<div class="layui-card">
			<div class="layui-card-body table-tool-mini full-table">
                <div class="layui-card-header" style="margin-bottom: 5px;"><h2>${homework.title}</h2></div>
				<div class="layui-form toolbar">
					<div class="layui-form-item">
						<div class="layui-inline" style="padding-right: 110px;">
							<%--<button class="layui-btn icon-btn"
								lay-filter="formSubSearchCourse" lay-submit>
								<i class="layui-icon">&#xe615;</i>搜索
							</button>
							<button id="btnAddHomeWork" class="layui-btn icon-btn">
								<i class="layui-icon">&#xe654;</i>布置作业
							</button>--%>
								<button id="showAll" class="layui-btn icon-btn">
									<i class="layui-icon">&#xe654;</i>查看全班
								</button>
								<button id="showDetails" class="layui-btn icon-btn">
									<i class="layui-icon">&#xe654;</i>建构分析
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
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看学生作业详情</a>
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
				url : '${ctx}/teacher/homework/detailInfo/${homework.id}/list.json',
				page : true,
				toolbar : true,
				cellMinWidth : 100,
				cols : [ [
					{
						type : 'numbers'
					},
					{
						field : 'studentName',
						sort : true,
						title : '学生',
						templet : function(d) {
							return d.student.user.realname;
						}
					},
					{
						field : 'status',
						sort : true,
						title : '状态',
						templet : function(d) {
							var status="";
							if(d.status==0){
								status = "未开始";
							}else if(d.status==1){
								status="已提交";
							}else {
								status="已批阅";
							}
							return status;
						}
					},
					{
						field : 'score',
						sort : true,
						title : '得分',
						templet : function(d) {
							return d.score;
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

			// 查看全班情况
			$('#showAll').click(function() {
				layui.use(['index'], function () {
					var index = layui.index;
					index.openTab({
						title: '查看${course.name}全班汇总',
						url: '${ctx}/teacher/homework/showAll/${homework.id}',
						end: function() {
						}
					});
				});
			});
			//建构分析

			$('#showDetails').click(function() {
				layui.use(['index'], function () {
					var index = layui.index;
					index.openTab({
						title: '知识建构过程分析',
						url: '${ctx}/teacher/homework/showDetails/${homework.id}',
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
				console.log(data);
				var layEvent = obj.event;
				if (layEvent === 'detail') { // 查看作业详细情况
					layui.use(['index'], function () {
						var index = layui.index;
						index.openTab({
							title: data.student.user.realname+'的作业',
							url: '/student/homework/showHomework/'+data.id,
							end: function() {
								//insTb.reload();
							}
						});
					});
				}
			});
    });
</script>

</body>
</html>