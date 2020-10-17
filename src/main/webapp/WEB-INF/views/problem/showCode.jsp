<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>测评</title>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/assets/libs/layui/css/layui.css"/>
    <script type="text/javascript" src="${ctx }/assets/libs/jquery/jquery-3.2.1.min.js"></script>

</head>
<body>
<%--<form style="width:100%;height:100vh;margin:0px 0px 0px 0px;" id=frmSolution action="submit.php" method="post">
    <font style='color:#333;line-height:30px'>
        Problem <span class=blue><b>1000</b></span>
        <input id=problem_id type='hidden' value='1000' name="id">
        &nbsp; | &nbsp; </font>
    <span id="language_span"><font style='color:#009688;'>语言</font>
<select id="language" name="language" onChange="reloadtemplate(this);">
<option value=0>
C
</option><option value=1>
C++
</option><option value=2>
Pascal
</option><option value=3 selected>
Java
</option></select>

</span>
    <input id="Submit" class="layui-btn layui-btn-xs" type=button value="提交" onclick="submit_code();">
    <input id="myselftest" class="layui-btn layui-btn-normal layui-btn-xs" type=button value="自测">
    <pre style="width:100%;height:93vh;margin:0px 0px 0px 0px;border-color:#303030" id="source"> </pre>
    <input type=hidden id="hide_source" name="source" value=""/>

</form>--%>
<textarea style="display: none" name="editor">${submissions.code}</textarea>
<pre style="width:100%;height:93vh;margin:0px 0px 0px 0px;border-color:#303030" id="source"> </pre>
<!-- js部分 -->
<script type="text/javascript" src="${ctx }/assets/libs/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/assets/js/common.js?v=316"></script>

<script>
    //更换编程语言
    function switchLang(lang) {
        var langnames = new Array("c_cpp", "c_cpp", "pascal", "java", "ruby", "sh", "python", "php", "perl", "csharp", "objectivec", "vbscript", "scheme", "c_cpp", "c_cpp", "lua", "javascript", "golang");
        editor.getSession().setMode("ace/mode/" + langnames[lang]);
    }

    function reloadtemplate(lang) {
        document.cookie = "lastlang=" + lang.value;
        //alert(document.cookie);
        var url = window.location.href;
        var i = url.indexOf("sid=");
        if (i != -1) url = url.substring(0, i - 1);
        //  if(confirm("是否加载默认模板?\n 如果选择是，当前代码将被覆盖!"))
        //       document.location.href=url;
        switchLang(lang.value);
    }


</script>
<script src="${ctx}/assets/libs/ace/src-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/assets/libs/ace/src-noconflict/ext-language_tools.js"></script>
<script>
    ace.require("ace/ext/language_tools");
    var editor = ace.edit("source");
    editor.setTheme("ace/theme/monokai");
    editor.setShowPrintMargin(false);
    switchLang(3);
    editor.setFontSize(18);
    editor.setOptions({
        enableBasicAutocompletion: true,
        enableSnippets: true,
        enableLiveAutocompletion: true
    });
    var txtAra = document.querySelector('textarea[name="editor"]');
    editor.getSession().setValue(txtAra.value);
</script>
</body>
</html>
