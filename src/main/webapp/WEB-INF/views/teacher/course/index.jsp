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
							<button id="btnAddCourse" class="layui-btn icon-btn">
								<i class="layui-icon">&#xe654;</i>添加
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
						field : 'classInfos',
						sort : true,
						templet : function(d) {
							var classes = "";
							for (var classInfo of d.classInfos) { // 遍历Set
								classes = classes + classInfo.name + ","; }
							return classes;
						},
						title : '授课班级'
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
	
			// 添加
			$('#btnAddCourse').click(function() {
				showEditModel();
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
				} else if (layEvent === 'del') { // 删除
					doDel(data.id, data.name);
				} else if (layEvent === 'detail') { // 查看作业详细情况
					layui.use(['index'], function () {
						var index = layui.index;
						index.openTab({
							title: data.name+'详细情况',
							url: '${ctx}/homework/showHomework/'+data.id,
							end: function() {
								//insTb.reload();
							}
						});
					});
				}
			});
			// 删除
			function doDel(id, courseName) {
				layer.confirm('确定要删除课程“' + courseName + '”吗？', {
					skin: 'layui-layer-admin',
					shade: .1
				}, function (i) {
					layer.close(i);
					layer.load(2);
					$.post('${ctx}/teacher/course/delete', {
						id: id
					}, function (res) {
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
        function showEditModel(mCourse) {
            admin.open({
                type: 1,
                title: (mCourse ? '修改' : '添加') + '课程',
                content: $('#modelCourse').html(),
                success: function (layero, dIndex) {
                    $(layero).children('.layui-layer-content').css('overflow', 'visible');
                    if(mCourse!=null&&mCourse.id!=null){
						var url = '${ctx}/teacher/course/edit?id='+mCourse.id;
					}else{
						var url = '${ctx}/teacher/course/edit';
					}
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
                    form.on('submit(modelSubmitCourse)', function (data) {
                    	var treeObj = $.fn.zTree.getZTreeObj('classTree');
                   	 		var nodes = treeObj.getCheckedNodes(true);
                    		var ids = new Array();
                    		var names ="";
                    		for (var i = 0; i < nodes.length; i++) {
                        		ids[i] = nodes[i].id;
                        		names =names+nodes[i].id;
                    		}
                    		if(names.indexOf("classinfo_")==-1){
                    			layer.msg("课程没有指定到具体的班级！", {icon: 2});
                    			layer.close(index);
                    			return false;
                    		}else{$.post(url, {
	                        		subject_id: data.field.subject_id,
	                        		classHour: data.field.classHour,
	                        		name: data.field.name,
	                        		study_team_id: data.field.study_team_id,
	                        		classInfoIds: JSON.stringify(ids)
	                    		}, function (res) {
			                       if (200 == res.code) {
			                           layer.msg(res.msg, {icon: 1});
			                           layer.close(index);
			                       } else {
			                           layer.msg(res.msg, {icon: 2});
			                       }
	                    		}, 'json'); 
                       }
                    });
                },
				end:function () {
					insTb.reload();
				}
            });
        }
    });
</script>

</body>
</html>