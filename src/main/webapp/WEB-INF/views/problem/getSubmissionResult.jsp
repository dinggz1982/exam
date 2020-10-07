<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测评结果</title>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <link rel="stylesheet" href="${ctx }/assets/libs/layui/css/layui.css"/>
    <script type="text/javascript" src="${ctx }/assets/libs/jquery/jquery-3.2.1.min.js"></script>
</head>

<body>


<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>测评结果</legend>
</fieldset>
<div class="layui-tab layui-steps layui-steps-readonly" lay-filter="stepsDemoForget"
     style="max-width: 600px;">
    <ul class="layui-tab-title">
        <li id="step1" class="layui-this">
            <i class="layui-icon layui-icon-ok">1</i>
            <span class="layui-steps-title">正在等待测评</span>
        </li>
        <li id="step2">
            <i class="layui-icon layui-icon-ok">2</i>
            <span class="layui-steps-title">测评中</span>
        </li>
        <li id="step3">
            <i class="layui-icon layui-icon-ok">3</i>
            <span class="layui-steps-title">完成测评</span>
        </li>
    </ul>
    <blockquote id="blockquote" class="layui-elem-quote layui-quote-nm layui-text">
        <div id="s1"></div><br/>
        <div id="s2"></div><br/>
        <div id="s3"></div>
    </blockquote>
    <div class="layui-form">
        <table class="layui-table">
            <colgroup>
                <col width="150">
                <col width="150">
                <col width="150">
                <col width="150">
                <col>
            </colgroup>
            <thead>
            <tr>
                <th>测评点</th>
                <th>结果</th>
                <th>耗时</th>
                <th>内存</th>
            </tr>
            </thead>
            <tbody id="testResult">

            </tbody>
        </table>
    </div>
</div>
</body>
<!-- js部分 -->
<script type="text/javascript" src="${ctx }/assets/libs/layui/layui.js"></script>
<script type="text/javascript" src="${ctx }/assets/js/common.js?v=316"></script>
<script>
    $(document).ready(function () {
        c = setInterval(getSubmitResult, 1500);//每1.5秒执行一次getSubmitResult方法
    });

    function getSubmitResult() {
        $.ajax({
            type: "GET",
            url: "/problem/getSubmissionResult/${submissionId}",
            success:
                function (problemSubmissions) {
                    var now = getNowFormatDate();//当前时间
                    var status = problemSubmissions.status;
                    var judgeInfo = "";
                    if (status == 1) {
                        judgeInfo = "等待测评";
                        $("#step1").addClass("layui-this");
                        $("#step2").removeClass("layui-this");
                        $("#step3").removeClass("layui-this");

                        $("#s1").html("<p>从服务器获取结果，状态为：" + judgeInfo + "---" + now + "</p>");

                    } else if (status == 2) {
                        judgeInfo = "正在测评中";
                        $("#step1").addClass("layui-this");
                        $("#step2").addClass("layui-this");
                        $("#step3").removeClass("layui-this");
                        $("#s2").html("<p>从服务器获取结果，状态为：" + judgeInfo + "---" + now + "</p>");
                    } else if (status == 3){
                        judgeInfo = "完成测评";
                        $("#step1").addClass("layui-this");
                        $("#step2").addClass("layui-this");
                        $("#step3").addClass("layui-this");
                        $("#s3").html("<p>从服务器获取结果，状态为：" + judgeInfo + "---" + now + "</p>");
                    }
                    if (problemSubmissions.status == 3) {
                        window.clearInterval(c);
                        //完成测评,拿到测评结果
                        var judgeResult = problemSubmissions.judgeResult;
                        var infos = new Array();
                        infos = judgeResult.split(";");
                        var index = 1;
                        for (var result of infos) {
                            //console.log(result);
                            var info = new Array();
                            info = result.split(":");
                            if(info.length>0&&info[1]!=null&&info[1]!=""){
                            $("#testResult").append("<tr><td>" + index + "</td><td>" + info[0] + "</td><td>" + info[1] + "毫秒</td><td>" + info[2] + "(byte)</td></tr>");
                            index++;
                            }
                        }
                    }
                }
        })
    }


    //取得当前系统时间
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = "/";                 //设置成自己想要的日期格式 年/月/日
        var seperator2 = ":";                 //设置成自己想要的时间格式 时:分:秒
        var month = date.getMonth() + 1;      //月
        var strDate = date.getDate();         //日
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + date.getHours() + seperator2 + date.getMinutes()
            + seperator2 + date.getSeconds();
        return currentdate;
    }

    // 计算文件大小函数(保留两位小数),Size为字节大小
    // size：初始文件大小
    function getfilesize(size) {
        if (!size)
            return "";
        var num = 1024.00; //byte

        if (size < num)
            return size + "B";
        if (size < Math.pow(num, 2))
            return (size / num).toFixed(2) + "K"; //kb
    }

</script>
</html>
