<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/WEB-INF/views/include/head.jsp"%>
<!DOCTYPE HTML>
<html>
<head>
    <title>章节练习--${problem.title }</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="中小学编程,小学生编程,编程,科技高考">
    <meta http-equiv="description" content="慧通教育">
</head>
<body>
<!-- 内容区域 -->
<div class="main">
    <div class="insideBox">
        <div class="wrap3">
            <input type="hidden" id="id" value="${problem.id}"> <input
                type="hidden" id="whereId" value="${chapterBase.id}">
            <div class="Checkpoint clearfix">
                <div class="course_left" style="width:100%">
                    <i class="quan"></i>
                    <div class="course_title">
                        <h3 class="clearfix">
                            <strong>${problem.id}.</strong><span style="width: 600px;">${problem.title }</span>
                        </h3>
                        <!-- <dl class="clearfix">
                            <dd>
                                <a href="javascript:;" class="notice"><i>&#xe631;</i>提示</a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="help"><i>&#xe602;</i>帮助</a>
                            </dd>
                        </dl> -->
                    </div>
                    <div class="title_description" style="height: 100%;">
                        <div class="description">
                            <h3>题目描述</h3>
                            <p>${programming.description}</p>
                        </div>
                        <c:if test="${not empty programming.inputstyle}">
                            <div class="description border">
                                <h3>输入格式</h3>
                                <p>
                                    <c:choose>
                                        <c:when test="${empty programming.inputstyle}">
                                            无
                                        </c:when>
                                        <c:otherwise>
                                            ${programming.inputstyle}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </c:if>
                        <c:if test="${not empty programming.outputstyle}">
                            <div class="description border">
                                <h3>输出格式</h3>
                                <p>
                                    <c:choose>
                                        <c:when test="${empty programming.outputstyle}">
                                            无
                                        </c:when>
                                        <c:otherwise>
                                            ${programming.outputstyle}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </c:if>
                        <c:if test="${fn:length(programming.samples)>0}">
                            <c:choose>
                                <c:when test="${empty programming.samples}">
                                    <div class="description">
                                        <h3>输入/输出例子</h3>
                                        <p>输入：</p>
                                        <p>输出：</p>
                                    </div>
                                </c:when>
                                <c:when test="${fn:length(programming.samples)>0}">
                                    <c:forEach items="${programming.samples }" var="sample"
                                               varStatus="status">
                                        <div class="description">
                                            <h3>输入/输出例子${status.index+1}</h3>
                                            <p>输入：${sample.inputsample}</p>
                                            <p>输出：${sample.outputsample}</p>
                                        </div>
                                    </c:forEach>
                                </c:when>
                            </c:choose>
                        </c:if>
                        <c:if test="${not empty programming.solution}">
                            <div class="description border">
                                <h3>样例解释</h3>
                                <p>
                                    <c:choose>
                                        <c:when test="${empty programming.solution}">
                                            无
                                        </c:when>
                                        <c:otherwise>
                                            ${programming.solution}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </c:if>
                        <%--<c:if test="${not empty problem.problemProgrammingTages}">
                            <div class="description">
                                <h3 id="tages">
                                    <a style="color: #ff8525;" href="javascript:return false;">标签</a>
                                </h3>
                                <p id="tagesTxt" style="display: none;">
                                    <c:choose>
                                        <c:when test="${!empty problem.problemProgrammingTages }">
                                            <c:forEach items="${problem.problemProgrammingTages  }"
                                                       var="koInfo">
                                                ${koInfo.kopoint.name },
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            暂未设置关联标签
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </c:if>
                        <c:if test="${not empty problem.problemKoinfo}">
                            <div class="description">
                                <h3 id="problemKoinfo">
                                    <a style="color: #ff8525;" href="javascript:return false;">知识点</a>
                                </h3>
                                <p id="problemKoinfoTxt" style="display: none;">
                                    <c:choose>
                                        <c:when test="${!empty problem.problemKoinfo }">
                                            <c:forEach items="${problem.problemKoinfo  }" var="koInfo">
                                                ${koInfo.kopoint.name }(${koInfo.percent }%),
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            暂未设置关联知识点
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                            </div>
                        </c:if>--%>
                        <%-- <div class="description">
                            <h3 id="solution">
                                <a style="color: #ff8525;" href="javascript:return false;">题解</a>
                            </h3>
                            <div id="solutionTxt" style="display: none;">${programming.solution}</div>
                        </div> --%>
                    </div>

                </div>
                <div class="course_right" style="width:100%;float: left;">
                    <div class="course_title">
                        <h3 class="clearfix">
                            <span>提交测评</span>
                        </h3>
                        <!--
                        <dl class="clearfix">
                            <dd>
                                <a href="javascript:;" class="collect"><i>&#xe61b;</i>收藏</a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="note"><i>&#xe675;</i>笔记</a>
                            </dd>
                            <dd>
                                <a href="javascript:;" class="shares"><i>&#xe67e;</i>分享</a>
                            </dd>
                        </dl>
                         -->
                    </div>
                    <div class="code_area" style="padding: 10px;">
                        <div id="code" style="width: 100%; height: 400px; text-align: left; border-radius: 10px; padding: 15px;">
                            var i;
                            for (i = 0; i < items.length; i++) {
                            alert("Ace Rocks " + items[i]);
                            }
                        </div>
                        <a href="javascript:submitCode();" class="submit_code"; >提交测评</a>
                        <div class="convertImage">
                            <div class="title">
                                Json 工具
                            </div>
                            <div class="content">
                                <div id="editor">{name: 'jack', age: '20'}</div>
                                <div class="btn-group button" role="group" aria-label="...">
                                    <button type="button" class="btn btn-default" @click="jsonFormat">Json 格式化（format)</button>
                                    <button type="button" class="btn btn-default" @click="jsonEncode">Json 压缩</button>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" id="submissionId"> <input
                            type="hidden" id="finish">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 内容区域结束 -->
<!-- 悬浮左侧菜单 -->
<div class="left_suspension trans">
    <div class="but_suspension">
        <span>收起闯关列表</span> <i class="iconfont-shouqikechengliebiao trans"></i>
    </div>
    <div class="suspension_title">${chapterBase.title}</div>
    <div class="q_list">
        <ul>
            <c:forEach items="${chapterBase.chapterProblemList}" var="p"
                       varStatus="s">
                <c:choose>
                    <c:when test="${p.problemBase.type==1 }">
                        <c:set var="function" value="fill" />
                    </c:when>
                    <c:when test="${p.problemBase.type==2 }">
                        <c:set var="function" value="trueFalse" />
                    </c:when>
                    <c:when test="${p.problemBase.type==3 }">
                        <c:set var="function" value="singleChoice" />
                    </c:when>
                    <c:when test="${p.problemBase.type==4 }">
                        <c:set var="function" value="multipleChoice" />
                    </c:when>
                    <c:when test="${p.problemBase.type==5 }">
                        <c:set var="function" value="program" />
                    </c:when>
                    <c:when test="${p.problemBase.type==6 }">
                        <c:set var="function" value="programFill" />
                    </c:when>
                    <c:when test="${p.problemBase.type==7 }">
                        <c:set var="function" value="choiceFill" />
                    </c:when>
                    <c:when test="${p.problemBase.type==9 }">
                        <c:set var="function" value="modifyProblem"/>
                    </c:when>
                </c:choose>
                <c:choose>
                    <c:when test="${p.problemBase.accept }">
                        <c:choose>
                            <c:when test="${p.problemId == problem.id }">
                                <li class="choosed"><a
                                        href="${ctx}/level/${function}/${courseId}/${chapterBase.id }/${p.problemBase.id }"><strong>${p.problemBase.id }.</strong>${p.problemBase.title }</a><span
                                        class="passed"><i class="iconfont-dagou"></i>已通过</span></li>
                            </c:when>
                            <c:otherwise>
                                <li><a
                                        href="${ctx}/level/${function}/${courseId}/${chapterBase.id }/${p.problemBase.id }"><strong>${p.problemBase.id }.</strong>${p.problemBase.title }</a><span
                                        class="passed"><i class="iconfont-dagou"></i>已通过</span></li>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${p.problemBase.id == problem.id }">
                                <li class="choosed"><a
                                        href="${ctx}/level/${function}/${courseId}/${chapterBase.id }/${p.problemBase.id }"><strong>${p.problemBase.id }.</strong>${p.problemBase.title }</a><span
                                        class="passed no-passed" id="noPassed_${problem.id }"><i class="iconfont-dagou"></i><span id="title_${problem.id }">未通过</span></span></li>
                            </c:when>
                            <c:otherwise>
                                <li><a
                                        href="${ctx}/level/${function}/${courseId}/${chapterBase.id }/${p.problemBase.id }"><strong>${p.problemBase.id }.</strong>${p.problemBase.title }</a><span
                                        class="passed no-passed"><i class="iconfont-dagou"></i>未通过</span></li>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </ul>
    </div>
</div>
</body>
<!-- 测评结果 -->
<script id="tpl11" type="text/template">
    <div class="test-state">
        <div class="state_top clearfix">
            <h3>测试状态：</h3>
            <div class="state_list" id="state_list">
                <!-- Waitting -->
                <p class="Waitting"><img src="${ctxStatic}/huitong/images/gif1.png" alt="">正在等待测评，状态：Waitting...</p>
            </div>
        </div>
        <div class="state_result" id="_state_result">
            <span>第1次从服务器获取测评结果信息</span>
        </div>
    </div>
    <div class="test_result" style="height:400px;">
        <h3>测评结果：<span id="score"></span></h3>
        <div class="result_desc clearfix" style="width:100%;padding:1px;">
            <div class="result_info" style="height:400px;width:100%;overflow-x: auto; overflow-y: auto;">
                <table class="layui-table" style="width:100%;overflow:scroll;">
                    <thead>
                    <tr>
                        <th>测试点</th>
                        <th>状态</th>
                        <th>耗时</th>
                        <th>内存</th>
                    </tr>
                    </thead>
                    <tbody id="resultinfo">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <a href="" class="test_sure">确定</a>
</script>
<script type="text/javascript">
    layui.use(['layer'],function(){
        $('#solution').on('click',function(){
            $("#solutionTxt").show();
        });

        $('#problemKoinfo').on('click',function(){
            $("#problemKoinfoTxt").show();
        });

        $('#tages').on('click',function(){
            $("#tagesTxt").show();
        });
    })
    var times =1;
    var but = true;
    var timer = setTimeout(function() {
        $(".left_suspension").addClass('on');
        $(".but_suspension").find("span").html('打开闯关列表');
        but = false
    }, 3000);
    $(".but_suspension").on("click", function() {
        clearTimeout(timer);
        if (but) {
            $(this).parents(".left_suspension").addClass('on');
            $(this).find("span").html('打开闯关列表');
        } else {
            $(this).parents(".left_suspension").removeClass('on');
            $(this).find("span").html('收起闯关列表');
        }
        but = !but;
    })

    function getCookie(key)
    {
        if (document.cookie.length>0)
        {
            c_start=document.cookie.indexOf(key + "=")
            if (c_start!=-1)
            {
                c_start = c_start + key.length + 1
                c_end = document.cookie.indexOf(";",c_start)
                if (c_end == -1) c_end = document.cookie.length
                return unescape(document.cookie.substring(c_start,c_end));
            }
        }
        return "";
    }

    function setCookie(key, value)
    {
        document.cookie = key + "=" + value + ";" ;
    }

    var judgeType='${programming.judgetype}';

    function returnJudgeResult(obj){
        var obj=degocBB(obj);
        var str="";
        var msg=obj.msg;
        var accept=0;
        for(var i=0;i<msg.length;i++){
            if(msg[i].score==100){
                str+=msg[i].msg+":1:0;";
                accept=accept+1;
            }else{
                str+=msg[i].msg+":1:0;";
            }
        }
        var code=frames['code'].getCode();
        var problemId = '${problem.id}';  //题目的problem ID
        var whereId=$("#whereId").val();
        $.ajax({
            dataType:"json",
            type:'post',
            url:"${ctx}/chapter/submissions/submitGocJudgeCode",
            data:{
                "problemId": problemId,
                "fromWhere": 2,
                "fromWhereId": whereId,
                "courseId": "${courseId}",
                "code": code,
                "acceptNumber":accept,
                "judgeResult":str
            },
            success:function(callback)  //注意callback的返回值必须是json格式
            {

            }
        });
        if(msg.length==accept){
            var classVal = document.getElementById("noPassed_${problem.id}").getAttribute("class");
            classVal = classVal.replace("no-passed","");
            document.getElementById("noPassed_${problem.id}").setAttribute("class",classVal );
            document.getElementById("title_${problem.id}").innerText="已通过";
            document.getElementById("noPassed_${problem.id}").innerHtml="<i class='iconfont-dagou'></i>";
        }
    }

    function codeSubmit(obj){
        submitCode(obj.code);
    }


    function submitCode(code)
    {
        //var code = document.getElementById('code').value;  //学生的代码
        var problemId = $("#id").val();  //题目的problem ID
        var whereId=$("#whereId").val();
        if(code.length < 10 ||  code.length > 32*1024)
        {
            layer.msg("代码过长或过短！");
            return false;
        }

        var lastsubmit = getCookie('lastsubmit');
        var d = new Date();
        var t = d.getTime();
        if(lastsubmit=="" || parseInt(t)-parseInt(lastsubmit)>5*1000 )
        {
            setCookie('lastsubmit',t);
            if(judgeType=="goc_judge"){
                var url="${ctx}/static/gocWebNet/judgeNetPrive.html?winName=${problem.id}&code=${code}&ansCode="+engocBB(code)+"&view=w11";
                layer.open({
                    type: 2,
                    area: ['90%', '90%'],
                    title:"gocJudge测评",
                    scrollbar: false,
                    content: url
                });
            }else{
                $.ajax({
                    dataType:"json",
                    type:'post',
                    url:"${ctx}/chapter/submissions/submitProblemCode",
                    data:{
                        "problemId": problemId,
                        "fromWhere": 2,
                        "fromWhereId": whereId,
                        "courseId": "${courseId}",
                        "code": code
                    },
                    success:function(callback)  //注意callback的返回值必须是json格式
                    {
                        var submissionId = callback.submissionId;
                        $("#submissionId").val(submissionId);
                        var layer = layui.layer;
                        updateResult();
                        // 测评结果
                        layer.open({
                            type: 1,
                            title:"<b></b>测评结果",
                            skin: 'demo-class5',
                            content: $("#tpl11").html(),
                            end: function(){
                                $("#finish").val(0);
                                times=1;
                                clearInterval(flag);
                            }
                        });
                    }
                });
            }
        }
        else{
            layer.msg("距离上次提交时间不足10秒，请稍候提交！");
            return false;
        }

    }
    function updateMsg(){
        times++;
        if($("#finish").val()!=1&&times<200){
            $.ajax({
                url: '/chapter/submissions/getInfo/'+$("#submissionId").val()+"/${courseId}",
                dataType: 'json',
                method: 'GET',
                success: function(data) {
                    //成功拿到测评结果
                    if (data.result == 1) {
                        if(data.status<3){
                            document.getElementById("state_list").innerHTML="<p class='Judging'><img src='${ctxStatic}/huitong/images/gif2.gif' alt=''>正在测评，状态：Judging...</p>";
                            document.getElementById("_state_result").innerHTML="<span>第"+times+"次("+data.date+")从服务器获取测评数据</span>";
                        }
                        if(data.status==3){
                            document.getElementById("state_list").innerHTML="<p class='done'><img src='${ctxStatic}/huitong/images/gif3.gif' alt=''>已完成测评，状态：done</p>";
                            document.getElementById("_state_result").innerHTML="<span>第"+times+"次("+data.date+")从服务器获取测评数据</span>";
                            $("#finish").val(1);
                            //更新resultinfo
                            var judgeResult = data.judgeResult;
                            var  html = work(judgeResult);
                            document.getElementById("resultinfo").innerHTML=html;
                            var testNumber = data.testNumber;//测试点
                            var acceptNumber = data.acceptNumber;//通过个数
                            if(testNumber==acceptNumber){//全部通过时才能评价
                                /* layer.open({
                                        type: 2,
                                        area: ['800px', '500px'],
                                         title:"<b></b>评价",
                                        content:'${ctx}/problemEvaluate/add?problemId=${problem.id}',
	 	                   		 	end: function(){

	 	                   		 	}
	 	                 		 });  */
                                var classVal = document.getElementById("noPassed_${problem.id}").getAttribute("class");
                                classVal = classVal.replace("no-passed","");
                                document.getElementById("noPassed_${problem.id}").setAttribute("class",classVal );
                                document.getElementById("title_${problem.id}").innerText="已通过";
                                document.getElementById("noPassed_${problem.id}").innerHtml="<i class='iconfont-dagou'></i>";
                            }
                        }
                        document.getElementById("score").innerHTML=data.mymark+"分";

                    }
                },
                error: function(xhr) {
                    // 导致出错的原因较多，以后再研究
                    // alert('error:' + JSON.stringify(xhr));
                }
            });
        }
        else{
            clearInterval(updateMsg);
        }
    }
    function updateResult(){

        if($("#finish").val()!=1){
            flag = setInterval(updateMsg, 2000);
        }else{
            clearInterval(flag);
        }
    }

    function work(res)
    {
        var y = new Array();
        y = res.split(";");
        var z = new Array();
        z = y[0].split(":");
        var arg = "table table-bordered table-hover";
        var pat = "style='font-size:12px;'" ;

        var myhtml="";
        for( var i=0; i<y.length-1; i++)
        {
            z = y[i].split(":");
            if(z[2]=="不可用"&&z[3]=="不可用")  myhtml = myhtml + "<tr class='active'>" + "<td>" + parseInt(i+1) + "</td>" + "<td>" + z[0] + "</td>" + "<td>不可用</td>" + "<td>不可用</td>" + "<td>" + parseInt(z[1]) + "</td>" + "</tr>";
            else myhtml = myhtml + "<tr class='active'>" + "<td>#" + parseInt(i+1) + "</td>" + "<td>" + z[0] + "</td>" + "<td>" + z[1] + "毫秒</td>" + "<td>" + parseInt(z[2]) + "K</td>" + "</tr>";
        }
        return myhtml;
    }
</script>
<script src="${ctx}/assets/libs/ace/src-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
<script>
    var editor = ace.edit("code");
    editor.setTheme("ace/theme/twilight");
    editor.session.setMode("ace/mode/javascript");

</script>
</html>