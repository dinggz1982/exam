<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>考勤列表</title>
   <%@include file="/WEB-INF/views/include/head.jsp" %>
  </head>
  <body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 系统管理 <span class="c-gray en">&gt;</span> 用户管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="pd-20">
  <table class="table table-border table-bordered table-hover table-bg table-sort">
    <thead>
      <tr class="text-c">
        <th width="25"><input type="checkbox" name="" value=""></th>
        <th width="80">序号</th>
        <th width="100">点名信息</th>
        <th width="100">点名时间</th>
        <th width="100">下载点名文件</th>
      </tr>
    </thead>
    <tbody>
    	<c:forEach items="${dataList }" var="rollCall" varStatus="status">
      <tr class="text-c">
        <td><input type="checkbox" value="1" name=""></td>
        <td>${status.index+1 }</td>
        <td><u style="cursor:pointer" class="text-primary">${rollCall.name }</u></td>
        <td><fmt:formatDate value="${rollCall.date}" pattern="yyyy-MM-dd HH:mm"/></td>
     <td><a href="/showRollCall/${rollCall.id }">查看点名详情</a></td>
</tr>
      </c:forEach>
    </tbody>
  </table>
  <div id="page" class="page"></div>
</div>

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>

  </body>
</html>
