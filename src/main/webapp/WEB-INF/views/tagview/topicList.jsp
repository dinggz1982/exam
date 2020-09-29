<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="its" uri="/WEB-INF/tlds/remark.tld" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>标签云实验列表</title>
<%@include file="/WEB-INF/views/include/top.jsp"%>
   <link rel="stylesheet" type="text/css" href="/static/lib/layerui/2.2.5/css/layui.css">

</head>
<body>
	<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span>
	标签词汇列表<span class="c-gray en">&gt;</span> 
	<span>欢迎您！${currentUser.username}</span>
		<a
		class="btn btn-success radius r"
		style="line-height:1.6em;margin-top:3px"
		href="/logout" title="退出"><i
		class="Hui-iconfont">&#xe68f;</i></a>
		</nav>
		<div class="pd-20 col-xs-10 col-md-offset-1">
 </div>
		 
	<div class="col-xs-10 col-md-offset-1">
		<table class="table table-border table-bordered table-hover table-bg table-sort" style="width: 100%">
 		<tr>
 			<th>
 				序号
 			</th>
 			<th>
 				主题
 			</th>
 			<th>
 				主题描述
 			</th>
 			<th>
 				主题对应的标签
 			</th>
 			<th>
 				编辑我的标签
 			</th>
 			<th>
 				编辑历史
 			</th>
 			<c:forEach items="${dataList }" var="topic" varStatus="status">
 				<tr>
 					<td>${(pageIndex-1)*pageSize + status.index+1}</td>
 					<td>${topic.topic}</td>
 					<td>${topic.description}</td>
 					<td><a href="/tag/topic/${topic.id}">相关词汇</a></td>
 					<td><a href="/tag/editTag/${topic.id}">编辑我的标签</a></td>
 					<td><a href="/tag/history/${topic.id}">编辑历史</a></td>
 				</tr>
 			</c:forEach>
 		</tr>
		</table>
		<div style="float: right;margin-right: 50px;" id="page" class="page"></div>
 	</div>
 	  
 	<script type="text/javascript" src="${ctx }/static/lib/layerui/2.2.5/layui.js"></script>
<script type="text/javascript" src="${ctx }/static/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
layui.use(['laypage', 'layer'], function(){
  var laypage = layui.laypage
  ,layer = layui.layer;
 	laypage.render({
    elem: 'page'
    ,count: ${total}
    ,curr :${pageIndex}
    ,layout: ['count', 'prev', 'page', 'next',  'refresh', 'skip']
    , jump:function(obj,first){
                    if(!first) {
    　　　　　　　　　　　　//***第一次不执行,一定要记住,这个必须有,要不然就是死循环,哈哈
                        var pageIndex = obj.curr;
                      self.location='/tag-setting?pageIndex='+pageIndex; 
                    }
                }
  });
  });
</script>
 	
	
</body>
</html>
