package gzhu.edu.cn.problem.web;

import gzhu.edu.cn.problem.entity.*;
import gzhu.edu.cn.problem.service.IProblemChoiceItemService;
import gzhu.edu.cn.problem.service.IProblemChoiceService;
import gzhu.edu.cn.problem.utils.ProblemCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @program: exam
 * @description: 编程作业Controller
 * @author: 丁国柱
 * @create: 2020-09-26 20:14
 */
@Controller
public class HomeWorkProblemController {

    @Autowired
    private IProblemChoiceService problemChoiceService;
    @Autowired
    private IProblemChoiceItemService problemChoiceItemService;

    @GetMapping("/student/problem")
    public String index() {

        return "problem/index";
    }

    /**
     * 访问编程题
     * @param problemId
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/myHomeWorkForProgramming/{myHomeWorkId}/{problemId}")
    public String myHomeWorkForProgramming(@PathVariable Long myHomeWorkId, @PathVariable Integer problemId, Model model) throws Exception {
        ProblemBaseInformation problemBase = ProblemCacheUtils.getProblemBaseInformation(problemId);
        ProblemProgrammingDeatil programming = ProblemCacheUtils.getProgrammingDeatil(problemId);
        if (programming != null) {
            List<ProblemProgrammingSamples> samples = ProblemCacheUtils.getProgrammingSamplesList(problemId);
            programming.setSamples(samples);
            model.addAttribute("programming", programming);
            if (programming.getTestpointinfo() != null && programming.getTestpointinfo().length() > 0) {
                String[] testPointArray = programming.getTestpointinfo().split(",");
                if (testPointArray.length > 0) {
                    model.addAttribute("timeLimit", testPointArray[0].split(":")[2]);
                    model.addAttribute("space", testPointArray[0].split(":")[3]);
                }

            } else {
                model.addAttribute("timeLimit", 0);
                model.addAttribute("space", 0);
            }
        } else {
            model.addAttribute("programming", programming);
            model.addAttribute("timeLimit", 0);
            model.addAttribute("space", 0);
        }

        model.addAttribute("myHomeWorkId", myHomeWorkId);
        model.addAttribute("code", programming.getTestMsg());
        model.addAttribute("problem", problemBase);
        return "problem/myHomeWorkForProgramming";
    }

    /**
     * 访问编程题
     * @param problemId
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/myHomeWorkForChoice/{myHomeWorkId}/{problemId}")
    public String myHomeWorkForSingleChoice(@PathVariable Long myHomeWorkId, @PathVariable Integer problemId, Model model) throws Exception {
        ProblemBaseInformation problemBase = ProblemCacheUtils.getProblemBaseInformation(problemId);
        int type  =problemBase.getType();
        //2.判断题3.单选题,4.多选题
        if(type>=2&&type<=4){
            ProblemChoice problemChoice= problemChoiceService.getByHql(" and problem_id="+problemId);
            ProblemChoiceItem problemChoiceItem = problemChoiceItemService.getByHql( "and problemId="+problemId);
            model.addAttribute("problemChoice",problemChoice);
            model.addAttribute("problemChoiceItem",problemChoiceItem);
        }
        return "problem/myHomeWorkForChoice";
    }


}