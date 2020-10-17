package gzhu.edu.cn.homework.web;

import gzhu.edu.cn.homework.entity.MyHomeWork;
import gzhu.edu.cn.homework.service.IMyHomeWorkService;
import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import gzhu.edu.cn.problem.entity.ProblemSubmissions;
import gzhu.edu.cn.problem.service.IProblemBaseInformationService;
import gzhu.edu.cn.problem.service.IProblemSubmissionsService;
import gzhu.edu.cn.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @program: exam
 * @description: 查看我的代码
 * @author: 丁国柱
 * @create: 2020-10-13 21:30
 */
@Controller
public class ViewMyCodeController {
    @Autowired
    private HttpSession session;

    @Autowired
    private IMyHomeWorkService myHomeWorkService;

    @Autowired
    private IProblemSubmissionsService problemSubmissionsService;

    @Autowired
    private IProblemBaseInformationService problemBaseInformationService;


    @GetMapping("/viewMyCode/{myHomeWorkId}/{problemId}")
    public String viewMyCode(@PathVariable Long myHomeWorkId,@PathVariable Integer problemId, Model model){
        User user = (User) session.getAttribute("currentUser");

        MyHomeWork myHomeWork = this.myHomeWorkService.findById(myHomeWorkId);

        //取得当前用户这道题的全部答题信息
        List<ProblemSubmissions> submissions = this.problemSubmissionsService.find(" submitUserId="+user.getId()+" and problemId="+problemId);

        ProblemBaseInformation problemBaseInformation = problemBaseInformationService.findById(problemId);

        model.addAttribute("submissions",submissions);
        model.addAttribute("myHomeWork",myHomeWork);
        model.addAttribute("problem",problemBaseInformation);
        return "student/myhomework/viewMyCode";
    }

    @GetMapping("/showCode/{id}")
    public String showCode(@PathVariable Long id,Model model){
        ProblemSubmissions submissions  = this.problemSubmissionsService.findById(id);
        User user = (User) session.getAttribute("currentUser");
        if(submissions.getSubmitUser().getId()==user.getId()){
            model.addAttribute("submissions",submissions);
            return "problem/showCode";
        }
        else{
            return "/403.html";
        }
    }


}