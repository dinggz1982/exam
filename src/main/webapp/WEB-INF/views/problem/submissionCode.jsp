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
<form style="width:100%;height:100vh;margin:0px 0px 0px 0px;" id=frmSolution action="submit.php" method="post">
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
</option><%--<option value=4>
Ruby
</option><option value=5>
Bash
</option><option value=6>
Python
</option><option value=7>
PHP
</option><option value=8>
Perl
</option><option value=9>
C#
</option>--%></select>

</span>
    <input id="Submit" class="layui-btn layui-btn-xs" type=button value="提交" onclick="submit_code();">
    <input id="myselftest" class="layui-btn layui-btn-normal layui-btn-xs" type=button value="自测">
    <pre style="width:100%;height:93vh;margin:0px 0px 0px 0px;border-color:#303030" id="source"> </pre>
    <input type=hidden id="hide_source" name="source" value=""/>

</form>

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

    //提交代码
    function submit_code() {
        var language = $('#language').val();
        if(language==3){
            language = "java";
        }
        else if(language==0){
            language = "c";
        }
        else if(language==1){
            language = "cpp";
        }
        //拿到代码
        if (typeof (editor) != "undefined") {
            $("#hide_source").val(editor.getValue());
        }
        $.ajax({
            url: "${ctx}/problem/submitProgrammingCode",    // 提交到controller的url路径
            type: "post",    // 提交方式
            data: {"id": ${id}, "code": $("#hide_source").val(), "language": language},
            dataType: "json",    // 服务器端返回的数据类型
            success: function (data) {    // 请求成功后的回调函数，其中的参数data为controller返回的map,也就是说,@ResponseBody将返回的map转化为JSON格式的数据，然后通过data这个参数取JSON数据中的值
                if (data.status == "success") {
                    //alert("提交成功！");
                    var submissionId=data.submissionId;
                    layer.open({
                        type: 2,
                        title: '测评信息',
                        shadeClose: true,
                        shade: 0.8,
                        area: ['800px', '60%'],
                        content: '${ctx}/problem/goToSubmissionResult/'+submissionId //获取结果
                    });
                } else {
                    alert("提交出错！请联系管理员！");
                }
            },
        });

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
</script>
</body>
</html>
