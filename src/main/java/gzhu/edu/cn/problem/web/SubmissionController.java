package gzhu.edu.cn.problem.web;

import gzhu.edu.cn.base.util.HttpUtils;
import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import gzhu.edu.cn.problem.entity.ProblemProgrammingDeatil;
import gzhu.edu.cn.problem.entity.ProblemSubmissions;
import gzhu.edu.cn.problem.sender.SubmissionSender;
import gzhu.edu.cn.problem.service.IProblemProgrammingDeatilService;
import gzhu.edu.cn.problem.service.IProblemSubmissionsService;
import gzhu.edu.cn.system.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: exam
 * @description: 代码提交
 * @author: 丁国柱
 * @create: 2020-09-26 21:57
 */
@Controller
public class SubmissionController {
    @Autowired
    private HttpSession session;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IProblemSubmissionsService problemSubmissionsService;

    @Autowired
    private SubmissionSender submissionSender;

    @Autowired
    private IProblemProgrammingDeatilService problemProgrammingDeatilService;

    @GetMapping("/problem/submissionCode/{id}")
    public String post_submission(@PathVariable Integer id, Model model) {
        model.addAttribute("id", id);
        return "problem/submissionCode";
    }

    @GetMapping("/problem/doJudge/{id}")
    @ResponseBody
    public String doJudge(@PathVariable Long id) {
        try {
            this.problemSubmissionsService.doJudge(id);
            return "success";
        } catch (Exception e) {
            return "fail:" + e.toString();
        }
    }

    /**
     * 提交编程题代码
     *
     * @return
     */
    @PostMapping("/problem/submitProgrammingCode")
    @ResponseBody
    public Map<String, Object> doSubmitCode(Integer id, String code, String language) {
        Map<String, Object> map = new HashMap();
        User user = (User) session.getAttribute("currentUser");
        ProblemProgrammingDeatil problemProgrammingDeatil = problemProgrammingDeatilService.getByProblemId(id);
        ProblemBaseInformation problemBase = new ProblemBaseInformation();
        problemBase.setId(id);
        try {
            ProblemSubmissions problemSubmissions = new ProblemSubmissions();
            problemSubmissions.setSubmitTime(new Date());
            problemSubmissions.setCode(code);
            problemSubmissions.setStatus(1);
            problemSubmissions.setIsCompleteProgramming("0");// 0表示程序题
            problemSubmissions.setTestNumber(String.valueOf(problemProgrammingDeatil.getTestnumber()));
            problemSubmissions.setAcceptNumber("0");
            problemSubmissions.setSubmitUser(user);
            problemSubmissions.setLanguage(language);
            problemSubmissions.setProblemBase(problemBase);
            this.problemSubmissionsService.save(problemSubmissions);
            map.put("status", "success");
            map.put("submissionId", problemSubmissions.getId());

             //上一个版本：通过访问url来触发测评
            /* Thread t = new Thread() {
                public void run() {
                    try {
                        //请求测评的url
                        String judgeUrl = "http://127.0.0.1/problem/doJudge/"+id;
                        String returnStr = HttpUtils.httpGet(judgeUrl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();*/
            //this.rabbitTemplate.convertAndSend("judge_queue", problemSubmissions.getId());
            this.submissionSender.sendSubmission(problemSubmissions.getId());
            //rabbitTemplate.convertAndSend("judge_topic_exchange","submissionId",problemSubmissions.getId());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "fail");
        }
        return map;
    }

    /**
     * 获取测评结果
     *
     * @param submissionId
     * @return
     */
    @GetMapping("/problem/getSubmissionResult/{submissionId}")
    @ResponseBody
    public ProblemSubmissions getSubmissionResult(@PathVariable Long submissionId) {
        ProblemSubmissions problemSubmissions = this.problemSubmissionsService.findById(submissionId);
        return problemSubmissions;
    }

    /**
     * 测评结果页面
     *
     * @param submissionId
     * @param model
     * @return
     */
    @GetMapping("/problem/goToSubmissionResult/{submissionId}")
    public String goToSubmissionResult(@PathVariable Long submissionId, Model model) {
        model.addAttribute("submissionId", submissionId);
        return "problem/getSubmissionResult";
    }


}