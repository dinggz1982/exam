package gzhu.edu.cn.problem.web;

import gzhu.edu.cn.homework.entity.MyHomeWork;
import gzhu.edu.cn.homework.entity.MyHomeWorkProblem;
import gzhu.edu.cn.homework.service.IMyHomeWorkProblemService;
import gzhu.edu.cn.homework.service.IMyHomeWorkService;
import gzhu.edu.cn.problem.entity.*;
import gzhu.edu.cn.problem.service.IProblemBaseInformationService;
import gzhu.edu.cn.problem.service.IProblemChoiceService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import gzhu.edu.cn.problem.utils.ProblemCacheUtils;
import gzhu.edu.cn.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: exam
 * @description: 在线测评
 * @author: 丁国柱
 * @create: 2020-10-19 10:46
 */
@Controller
public class EvaluateController {

    @Autowired
    private IProblemBaseInformationService problemBaseInformationService;
    @Autowired
    private HttpSession session;
    @Autowired
    private IProblemChoiceService problemChoiceService;
    @Autowired
    private IMyHomeWorkService myHomeWorkService;
    @Autowired
    private IMyHomeWorkProblemService myHomeWorkProblemService;

    //查看自己的测评结果
    @GetMapping("/course/evalution/result/{myhomework_id}")
    public String showResult(@PathVariable Long myhomework_id,Model model){
        User user = (User)session.getAttribute("currentUser");
        MyHomeWork myHomeWork = this.myHomeWorkService.findById(myhomework_id);
        String ids = myHomeWork.getHomeWork().getProblemIds();
        model.addAttribute("homework", myHomeWork.getHomeWork());
        model.addAttribute("myHomeWork", myHomeWork);
        //拿到相关试题
        List<MyHomeWorkProblem> problems = this.myHomeWorkProblemService.find(" myhomework_id=" + myHomeWork.getId());
        model.addAttribute("problems", problems);
        return "student/myhomework/evalution_result";

    }

    @GetMapping("/course/evaluate/{id}")
    public String evaluate(@PathVariable Integer id, Model model) throws Exception {
        List<ProblemBaseInformation> problems = this.problemBaseInformationService.find(" id in(533,606,888,889,5765)");
        JSONArray jsonArray = new JSONArray();
        for (ProblemBaseInformation problem : problems) {
            //单选题
            if (problem.getType() == 3) {
                jsonArray.add(parseSingleChoice(problem));
            }
            //编程题
            else if (problem.getType() == 5) {
                jsonArray.add(parseProgramming(problem));

            }
        }
        model.addAttribute("programmingList", jsonArray);
        return "course/evaluate";
    }

    private JSONObject parseSingleChoice(ProblemBaseInformation problem) {
        JSONObject object = new JSONObject();
        object.put("type", "3");
        ProblemChoice problemChoice = this.problemChoiceService.getItemsByProblemId(problem.getId());
        object.put("title", problemChoice.getDescription());
        object.put("id", problem.getId());
        JSONArray jsonArray = new JSONArray();
        for (ProblemChoiceItem item : problemChoice.getChoiceItem()) {
            JSONObject itemObject = new JSONObject();
            itemObject.put("itemId", item.getId());
            itemObject.put("item", item.getItem());
            jsonArray.add(itemObject);
        }
        object.put("items", jsonArray);
        return object;
    }


    private JSONObject parseProgramming(ProblemBaseInformation problem) throws Exception {
        JSONObject object = new JSONObject();
        int problemId = problem.getId();

        ProblemProgrammingDeatil programming = ProblemCacheUtils.getProgrammingDeatil(problemId);
        object.put("type", "5");
        object.put("title", problem.getTitle());
        object.put("id", problem.getId());
        object.put("description", programming.getDescription());
        object.put("inputStyle", programming.getInputstyle());
        object.put("outputStyle", programming.getOutputstyle());

        //编程题样例
        List<ProblemProgrammingSamples> samples = ProblemCacheUtils.getProgrammingSamplesList(problemId);
        JSONArray jsonArray = new JSONArray();
        for (ProblemProgrammingSamples sample : samples) {
            JSONObject sampleObject = new JSONObject();
            sampleObject.put("inputSample", sample.getInputsample());
            sampleObject.put("outputSample", sample.getOutputsample());
            jsonArray.add(sampleObject);
        }
        object.put("samples", samples);
        return object;
    }

    @ResponseBody
    @PostMapping("/course/evalutionMyHomeWork/{myhomework_id}")
    public Map<String, Object> evalutionMyHomeWork(@PathVariable Long myhomework_id, @RequestParam String checkQuesJson) {
        MyHomeWork myHomeWork = this.myHomeWorkService.findById(myhomework_id);
        myHomeWork.setSubmissioned(true);
        this.myHomeWorkService.update(myHomeWork);
        Map<String, Object> map = new HashMap<>();
        try {
            JSONArray array = JSONObject.parseArray(checkQuesJson);
            User user = (User) session.getAttribute("currentUser");
            for (int i = 0; i < array.size(); i++) {
                JSONObject obj = array.getJSONObject(i);
                int pid = Integer.parseInt(obj.get("pid").toString());
                int choiceId = Integer.parseInt(obj.get("choiceId").toString());
                String userChoiceItem = obj.get("item").toString();
                int userChoiceItemId = Integer.parseInt(obj.get("answer").toString());
                this.problemChoiceService.judgeProblemChoice(myhomework_id, pid, choiceId, user, userChoiceItemId, userChoiceItem);
            }
            map.put("result", "success");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("result", "failed");
        }

        return map;

    }

}