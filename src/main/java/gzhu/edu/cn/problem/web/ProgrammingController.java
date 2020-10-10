package gzhu.edu.cn.problem.web;

import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import gzhu.edu.cn.problem.entity.ProblemProgrammingDeatil;
import gzhu.edu.cn.problem.entity.ProblemProgrammingSamples;
import gzhu.edu.cn.problem.utils.ProblemCacheUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @program: exam
 * @description: 编程题控制层
 * @author: 丁国柱
 * @create: 2020-09-21 00:43
 */
@Controller
@RequestMapping("/programming")
public class ProgrammingController {
    /**
     * 访问编程题
     * @param problemId
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/{problemId}")
    public String programming(@PathVariable Integer problemId, Model model) throws Exception {
        ProblemBaseInformation problemBase = ProblemCacheUtils.getProblemBaseInformation(problemId);
        ProblemProgrammingDeatil programming= ProblemCacheUtils.getProgrammingDeatil(problemId);
        if (programming != null) {
            List<ProblemProgrammingSamples> samples=ProblemCacheUtils.getProgrammingSamplesList(problemId);
            programming.setSamples(samples);
            model.addAttribute("programming", programming);
            if(programming.getTestpointinfo()!=null && programming.getTestpointinfo().length()>0){
                String[] testPointArray=programming.getTestpointinfo().split(",");
                if(testPointArray.length>0){
                    model.addAttribute("timeLimit", testPointArray[0].split(":")[2]);
                    model.addAttribute("space", testPointArray[0].split(":")[3]);
                }

            }else{
                model.addAttribute("timeLimit", 0);
                model.addAttribute("space", 0);
            }
        }else{
            model.addAttribute("programming", programming);
            model.addAttribute("timeLimit", 0);
            model.addAttribute("space", 0);
        }
        model.addAttribute("code", programming.getTestMsg());
        model.addAttribute("problem", problemBase);
        return "problem/programming";
    }



}