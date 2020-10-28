<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<link rel="stylesheet" href="${ctx }/assets/css/evaluate.css">
<link rel="stylesheet" href="${ctx }/assets/libs/bootstrap-3.3.7/css/bootstrap.min.css">
<%@include file="/WEB-INF/views/include/head.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>课堂测评</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
</head>
<script type="text/javascript" src="${ctx }/assets/libs/jquery/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${ctx }/assets/libs/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<style>
    #choiceDesc p{
        display:inline;
    }
    .question_info pre{
        display:inline;
    }
</style>
<script>
    var HH = 0;//时
    var mm = 0;//分
    var ss = 0;//秒
    var timeState = true;//时间状态 默认为true 开启时间
    var questions = ${programmingList};
    var itemList = ["A", "B", "C", "D", "E", "F"]
    var activeQuestion = 0; //当前操作的考题编号
    var questioned = 0; //
    var checkQues = []; //已做答的题的集合
    let hasChecked = new Set();
    /*实现计时器*/
    var time = setInterval(function () {
        if (timeState) {
            if (HH == 24) HH = 0;
            str = "";
            if (++ss == 60) {
                if (++mm == 60) {
                    HH++;
                    mm = 0;
                }
                ss = 0;
            }
            str += HH < 10 ? "0" + HH : HH;
            str += ":";
            str += mm < 10 ? "0" + mm : mm;
            str += ":";
            str += ss < 10 ? "0" + ss : ss;
            $(".time").text(str);
        } else {
            $(".time").text(str);
        }
    }, 1000);

    //展示考卷信息
    function showQuestion(id) {
        $(".questioned").text(id + 1);
        questioned = (id + 1) / questions.length;
        //alert(activeQuestion);
        if (activeQuestion != undefined) {
            $("#ques" + activeQuestion).removeClass("question_id").addClass("active_question_id");
        }
        activeQuestion = id;
        $(".question").find(".question_info").remove();
        var question = questions[id];
        if (question.type == 5) {
            //编程题
            parseProgramming(question, id);
        } else if (question.type == 3) {
            //单选题
            parseSingleChoice(question, id);
        }

        progress();
    }

    /*解析编程题*/
    function parseProgramming(question, id) {
        //隐藏非编程题
        $("#notProgrammingQuestion").css("display", "none");
        $("#programmingQuestion").css("display", "block");
        $("#questionType").text("【编程题】(题目id：" + question.id + ")");
        $("#programming_title").html("<strong>第 " + (id + 1) + " 题 、</strong>" + question.title);
        $("#question_desc").html(question.description);
        $("#inputStyle").html(question.inputStyle);
        $("#outputStyle").html(question.outputStyle);
        $("#submitCode").val(question.id);
        //渲染输入输出样例
        $.each(question.samples, function (n, value) {
            //alert(n+' '+value);
            j = n + 1;
            $("#samples").empty();
            $("#samples").append("<div class='panel panel-default'><div class='panel-heading'><h3 class='panel-title'>输入输出样例" + j + "</div> <div class='panel-body'>输入：<code>" + value.inputsample + "</code>输出：<code>" + value.outputsample + "</code></div></div>");
        });
    }

    /*解析单选题*/
    function parseSingleChoice(question, id) {
        //隐藏非编程题
        $("#notProgrammingQuestion").css("display", "block");
        $("#programmingQuestion").css("display", "none");
        $("#questionType").text("【单选题】");
        //$(".question_title").html("<strong>第 " + (id + 1) + " 题 、" + question.title + "</strong>");
        //$("#titleId").html("第 " + (id + 1) + " 题 、");
        $("#choiceDesc").html("第" + (id + 1) + " 题(题目id：" + question.id + ") 、" + question.title);
        //渲染选项
        var items = question.items;
        for (var i = 0; i < items.length; i++) {
            var item = " <li class='question_info' onclick='clickTrim(this)' id='"
                + items[i].itemId + "'><input name='itemInfo' type='hidden' value='" + items[i].item + "'/><input name='thisProblem' type='hidden' value='" + question.id + "'/><input name='choiceId' type='hidden' value='" + question.choiceId + "'/><div class='radio'><label style='display:inline'><input type='radio' name='item' id='item_" + items[i].itemId + "' value='" + items[i].itemId + "'>"+itemList[i]+"." + items[i].item + "</label></div></li>";
            $(".question").append(item);
        }

    }

    /*答题卡*/
    function answerCard() {
        $(".question_sum").text(questions.length);
        for (var i = 0; i < questions.length; i++) {
            var questionId = "<li id='ques" + i + "'onclick='saveQuestionState(" + i + ")' class=' questionId'>" + (i + 1) + "</li>";
            $("#answerCard ul").append(questionId);
        }
    }

    /*选中考题*/
    var Question;

    function clickTrim(source) {
        var id = source.id;
        var pid = $("input[name='thisProblem']").val();
        var choiceId = $("input[name='choiceId']").val();
        var itemInfo = $("input[name='itemInfo']").val();
        $("#" + id).find("input").prop("checked", "checked");
        $("#" + id).addClass("clickTrim");
        $("#ques" + activeQuestion).removeClass("question_id").addClass("clickQue");
        var ques = 0;
        for (var i = 0; i < checkQues.length; i++) {
            if (checkQues[i].id == activeQuestion && checkQues[i].item != id) {
                ques = checkQues[i].id;
                checkQues[i].item = itemInfo;//获取当前考题的选项ID
                checkQues[i].pid = pid;
                checkQues[i].choiceId = choiceId;
                checkQues[i].answer = $("#" + id).find("input[name=item]:checked").val();//获取当前考题的选项值
            }
        }
        if (checkQues.length == 0 || Question != activeQuestion && activeQuestion != ques) {
            var check = {};
            check.id = pid;//获取当前考题的id
            check.pid = pid;
            check.item = itemInfo;//获取当前考题的选项内容
            check.choiceId = choiceId;
            check.answer = $("#" + id).find("input[name=item]:checked").val();//获取当前考题的选项值
            hasChecked.add(id);
            //console.log(id);
            checkQues.push(check);
        }
        //console.log(checkQues);
        $(".question_info").each(function () {
            var otherId = $(this).attr("id");
            if (otherId != id) {
                $("#" + otherId).find("input").prop("checked", false);
                $("#" + otherId).removeClass("clickTrim");
            }
        });
        progress();
        Question = activeQuestion;
    }

    /*设置进度条*/
    function progress() {
        //alert($(".active_question_id").length);
        var hasCheck = hasChecked.size;
        var prog = hasCheck / questions.length;
        if (prog <= 1) {
            var pro = $(".progress").parent().width() * prog;
            $(".progres").text((prog * 100).toString().substr(0, 5) + "%")
            $(".progress").animate({
                width: pro,
                opacity: 0.5
            }, 1000);
        }
    }

    //获取已经提交的试题数
    function getSubmitQuestion() {

    }

    /*保存考题状态 已做答的状态*/
    function saveQuestionState(clickId) {
        showQuestion(clickId)
    }

    $(function () {
        $(".middle-top-left").width($(".middle-top").width() - $(".middle-top-right").width())
        $(".progress-left").width($(".middle-top-left").width() - 200);
        progress();
        answerCard();
        showQuestion(0);
        /*alert(QuestionJosn.length);*/
        /*实现进度条信息加载的动画*/
        var str = '';
        /*开启或者停止时间*/
        $(".time-stop").click(function () {
            timeState = false;
            $(this).hide();
            $(".time-start").show();
        });
        $(".time-start").click(function () {
            timeState = true;
            $(this).hide();
            $(".time-stop").show();
        });

        /*收藏按钮的切换*/
        $("#unHeart").click(function () {
            $(this).hide();
            $("#heart").show();
        })
        $("#heart").click(function () {
            $(this).hide();
            $("#unHeart").show();
        })

        /*答题卡的切换*/
        $("#openCard").click(function () {
            $("#closeCard").show();
            $("#answerCard").slideDown();
            $(this).hide();
        })
        $("#closeCard").click(function () {
            $("#openCard").show();
            $("#answerCard").slideUp();
            $(this).hide();
        })

        //提交试卷
        $("#submitQuestions").click(function () {
            /*alert(JSON.stringify(checkQues));*/
            //("已做答:" + ($(".clickQue").length) + "道题,还有" + (questions.length - ($(".clickQue").length)) + "道题未完成");
            if (hasChecked.size == questions.length) {
                if (confirm('确认提交？')) {
                    doSubmist();
                }
            } else {
                if (hasChecked.size < questions.length) {
                    if (confirm('还有试题未完成确认提交？')) {
                        doSubmist();
                    }
                }
            }
        })

        function doSubmist() {
            $('#loadingModal').modal('show');
            //$('#loading').modal('hide');
            var checkQuesJson = JSON.stringify(checkQues);
            $.ajax({
                type: "POST",
                url: "/course/evalutionMyHomeWork/${myhomework_id}",
                data: {checkQuesJson: checkQuesJson},
                cache: false,
                success: function (data) {
                    console.log(data);
                    //alert("OK");
                    $('#loadingModal').modal('hide');
                    if (data.result == "success") {
                        alert("测评成功！");
                    } else {
                        alert("测评有误！请联系管理员！"+data.status);
                    }
                    window.open("${ctx}/course/evalution/result/"+${myhomework_id});
                }
            });
        }

        //进入下一题
        $("#nextQuestion").click(function () {
            if ((activeQuestion + 1) != questions.length) showQuestion(activeQuestion + 1);
            showQuestion(activeQuestion)
        })
    })

    layui.use(['layer', 'util'], function () {
        var $ = layui.jquery;
        var layer = layui.layer;
        //拿到当前id

        //监听按钮
        $('#submitCode').click(function () {
            var id = $("#submitCode").val();
            hasChecked.add(id);//认为用户已完成了此题
            var index = layer.open({
                type: 2
                , title: '提交代码'
                , area: ['60%', '100%']
                , shade: false
                , maxmin: true
                , offset: 'rt'
                , content: '/problem/submissionCodeForHomeWork/121/' + id
                , anim: 2
                , zIndex: layer.zIndex //重点1
                , success: function (layero) {
                    layer.setTop(layero); //重点2
                }, end: function () {
                    progress();
                }
            });
        });
    });
</script>
</head>
<body>

<div>

    <div class="col-md-1"></div>
    <div class="col-md-10" style="margin: 0 auto;">
        <div class="content">
                <h1 style="margin: 0 auto;text-align: center;margin-top: 20px;margin-bottom: 10px;">课堂练习：${myHomeWork.homeWork.title}</h1>
            <div id="answerCard" style="width: 80%;margin: 0 auto;">
                <div class="panel-body form-horizontal" style="padding: 0px;">
                    <ul class="list-unstyled">
                    </ul>
                </div>
            </div>

            <div style="width: 100%;height:auto;display: inline-block;border: 1px solid white;position: relative;margin-top:10px;">
                <div style="width: 100%;">
                    <div style="width: 80%;margin: 0px auto">
                        <div style="width: 100%;height:100px;border: 1px solid #CCC;border-bottom:none;background:#FFF;">
                            <div class="middle-top"
                                 style="width: 100%;height: 50px;border-bottom: 1px solid #CCC;background:#2D3339; position:relative;">
                                <div class="middle-top-left pull-left"
                                     style="height: 100%;padding-left: 20px;;background:#232C31;color:#FFF;">
                                    <div class="text-center pull-left progress-left"
                                         style="border: 1px solid #A2C69A;background:#FFF;border-radius:10px;line-height:20px;height:20px;margin:15px 0px 15px 0px;font-size: 11px;position:relative;">
                                        <div class="progress pull-left"
                                             style="background: #609E53;width: 0px;height:18px;position:absolute;left: 0px;"></div>
                                        <div class="pro-text"
                                             style="left: 0px;color: #609E53;position:absolute;top:0px;width:100%;"> 已完成<span
                                                class="progres"></span></div>
                                    </div>
                                    <div class="pull-left"
                                         style=" width:135px;line-height:20px;height:20px;margin:15px;font-size:15px;">
                                        <!--已做答的数量和考题总数-->
                                        当前第<span class="questioned"></span>题/共<span class="question_sum"></span>题
                                    </div>
                                </div>
                                <div class="middle-top-right text-center pull-left"
                                     style="width:160px; height: 100%;border-left: 1px solid red;position: absolute;right: 0px;">
                                    <div class="stop pull-left" style="width: 50px; height: 100%;padding: 10px;"><a
                                            href="javascript:void(0);" class="text-center" style="color: #FE6547;">
                                        <div class="time-stop glyphicon glyphicon-pause" title="暂停"
                                             style="width:30px;height: 30px;line-height:30px; border-radius:15px;border: 1px solid #FE6547;"></div>
                                        <div class="time-start glyphicon glyphicon-play" title="开始"
                                             style="width:30px;height: 30px;line-height:30px;border-radius:15px;border: 1px solid #FE6547;display:none;"></div>
                                    </a></div>
                                    <div class="pull-left"
                                         style="width: 100px; height: 100%;padding: 10px 0px 10px 0px;">
                                        <div class="time"
                                             style="width:100px;height: 30px;line-height:30px; border-radius:15px;font-size:20px;color:#FFF;"></div>
                                    </div>
                                </div>
                            </div>
                            <div style="width: 100%;height: 50px;font-size:15px;color: #000;line-height: 50px;padding-left: 20px;">
                                <div style="color:#FFF;background: red;width: 22px;height: 22px;border-radius:11px;line-height:22px;font-size:13px; text-align: center;"
                                     class="glyphicon glyphicon-map-marker"></div>
                                <span id="questionType"><span></div>
                        </div>
                        <div style="width: 100%;height:auto;display: inline-block;border: 1px solid #CCC;border-bottom:1px dashed #CCC;background:#FFF;">
                            <div style="width: 100%;height: 90%;padding:20px 20px 0px 20px;">
                                <!--试题区域-->
                                <!--非编程题 -->
                                <div class="row" style="display: inline-block;margin: 0 auto;">
                                    <div>
                                        <h3 id="choiceDesc" class="form-inline;display:inline; "></h3>

                                        <%--<label class="form-inline"><span id="titleId"></span><span
                                                id="choiceDesc" ></span></label>--%>
                                    </div>
                                </div>
                                <div style="clear: both;"></div>
                                <ul class="list-unstyled question" id="notProgrammingQuestion" name="">

                                </ul>
                                <!--编程题 -->
                                <div id="programmingQuestion" style="display: none" class="grid-demo grid-demo-bg1"
                                     style="padding:0px 0px;">
                                    <strong id="programming_title" style="font-size:20px;"></strong>
                                    <div>
                                        <center>
                                            <button id='submitCode' value="" class='btn btn-primary'>提交代码</button>
                                        </center>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">题目描述</div>
                                        <div class="panel-body" id="question_desc">
                                            Panel content
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">输入格式</div>
                                        <div class="panel-body" id="inputStyle">
                                            Panel content
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading">输出格式</div>
                                        <div class="panel-body" id="outputStyle">
                                            Panel content
                                        </div>
                                    </div>
                                    <div id="samples"></div>
                                    <p>
                                    <div class="alert alert-warning">
                                        <strong>提示信息</strong>
                                    </div>

                                </div>
                            </div>
                            <!--考题的操作区域-->
                            <div class="operation" style="margin-top: 20px;">
                                <div class="text-right" style="margin-right: 20px;">
                                    <div class="form-group" style="color: #FFF;">
                                        <button class="btn btn-success" id="submitQuestions">提交试卷</button>
                                        <button class="btn btn-info" id="nextQuestion">下一题</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!--loading-->
<div class="modal fade" id="loadingModal" backdrop="static" keyboard="false">
    　　
    <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
        　　　　
        <div class="progress progress-striped active" style="margin-bottom: 0;">
            　　　　　　
            <div class="progress-bar" style="width: 100%;"></div>
            　　　　
        </div>
        　　　　<h5 id="loadText">loading...</h5>
        　　
    </div>
</div>
</body>

</html>