<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学生端功能--我的课程</title>
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
				<div class="layui-form toolbar">
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label w-auto">选择学期：</label>
							<div class="layui-input-inline mr0">
								<select name="team_id">
									<option value="0">请选择学期</option>
									<c:forEach items="${studyTeams }" var="team">
										<option value="${team.id }">${team.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label w-auto">课程名称：</label>
							<div class="layui-input-inline mr0">
								<input name="name" class="layui-input" type="text"
									placeholder="输入课程名称" />
							</div>
						</div>
						<div class="layui-inline" style="padding-right: 110px;">
							<button class="layui-btn icon-btn"
								lay-filter="formSubSearchCourse" lay-submit>
								<i class="layui-icon">&#xe615;</i>搜索
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
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看课程公告</a>
    <a class="layui-btn layui-btn-xs" lay-event="homework">查看作业</a>
</script>
	<!-- 表单弹窗 -->
	
	
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
				elem : '#tableCourse', //要渲染的表格id
				url : '${ctx}/teacher/course/list.json',
				page : true,
				toolbar : true,
				cellMinWidth : 100,
				cols : [ [
					{
						type : 'numbers'
					},
					{
						field : 'name',
						sort : true,
						title : '课程名称'
					},
					{
						field : 'studyTeam',
						sort : true,
						title : '学期',
						templet : function(d) {
							return d.studyTeam.name;
						}
					},
					{
						field : 'classHour',
						sort : true,
						title : '课时'
					},
					{
						align : 'center',
						toolbar : '#tableBarCourse',
						title : '操作',
						minWidth : 200
					}
				] ]
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
				if (layEvent === 'homework') { // 修改
					showEditModel(data);
				} else if (layEvent === 'detail') { // 查看学校详细情况
					//resetPsw(data.id, data.nickName);新开一个页面查看学校详细情况
				}
			});
			
		// 显示表单弹窗
        function showEditModel(mCourse) {
            admin.open({
                type: 1,
                title: (mCourse ? '修改' : '添加') + '课程',
                content: $('#modelCourse').html(),
                success: function (layero, dIndex) {
                    $(layero).children('.layui-layer-content').css('overflow', 'visible');
                    var url = '${ctx}/teacher/course/edit';
                    // 回显数据
                    form.val('modelCourseForm', mCourse);
                    //加载班级树
                        layer.load(2);
						 var courseId = null;
						if (mCourse) {
							if (mCourse.hasOwnProperty("id")) {
								courseId = mCourse.id;
							}
						}
						var setting = {
							check : {
								enable : true
							}
						};
						$.post('${ctx}/profile/classInfo/classTree', {
							course_id : courseId
						}, function(res) {
							var data1 = JSON.parse(res.data);
							$.fn.zTree.init($('#classTree'), setting, data1);
							layer.closeAll('loading');
						}, 'json');
                    // 表单提交事件
                }
            });
        }
    });
</script>

</body>
</html>
