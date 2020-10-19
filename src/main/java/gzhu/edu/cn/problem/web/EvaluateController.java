package gzhu.edu.cn.problem.web;

import gzhu.edu.cn.problem.entity.*;
import gzhu.edu.cn.problem.service.IProblemBaseInformationService;
import gzhu.edu.cn.problem.service.IProblemChoiceService;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import gzhu.edu.cn.problem.utils.ProblemCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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
    private IProblemChoiceService problemChoiceService;

    @GetMapping("/course/evaluate/{id}")
    public String evaluate(@PathVariable Integer id, Model model) throws Exception {
        List<ProblemBaseInformation> problems = this.problemBaseInformationService.find(" id in(888,889)");
        JSONArray jsonArray = new JSONArray();
        for(ProblemBaseInformation problem:problems){
            //单选题
            if(problem.getType()==3){
                jsonArray.add(parseSingleChoice(problem));
                model.addAttribute("");
            }
            //编程题
            else if(problem.getType()==5){
                jsonArray.add(parseProgramming(problem));
                model.addAttribute("programmingList",jsonArray);
            }
        }
        return "course/evaluate";
    }

    private JSONObject parseSingleChoice(ProblemBaseInformation problem){
        JSONObject object = new JSONObject();
        object.put("type","3");
        ProblemChoice problemChoice = this.problemChoiceService.getItemsByProblemId(problem.getId());
        object.put("questionTitle",problemChoice.getContent());
        object.put("id",problem.getId());
        JSONArray jsonArray = new JSONArray();
        for(ProblemChoiceItem item:problemChoice.getChoiceItem()){
            JSONObject itemObject = new JSONObject();
            itemObject.put("itemId",item.getId());
            itemObject.put("item",item.getItem());
            jsonArray.add(itemObject);
        }
        object.put("items",jsonArray);
        return object;
    }



    private JSONObject parseProgramming(ProblemBaseInformation problem) throws Exception {
        JSONObject object = new JSONObject();
        int problemId = problem.getId();

        ProblemProgrammingDeatil programming = ProblemCacheUtils.getProgrammingDeatil(problemId);
        object.put("type","5");
        object.put("title",problem.getTitle());
        object.put("id",problem.getId());
        object.put("description",programming.getDescription());
        object.put("inputStyle",programming.getInputstyle());
        object.put("outputStyle",programming.getOutputstyle());

        //编程题样例
        List<ProblemProgrammingSamples> samples = ProblemCacheUtils.getProgrammingSamplesList(problemId);
        JSONArray jsonArray = new JSONArray();
        for(ProblemProgrammingSamples sample:samples){
            JSONObject sampleObject = new JSONObject();
            sampleObject.put("inputSample",sample.getInputsample());
            sampleObject.put("outputSample",sample.getOutputsample());
            jsonArray.add(sampleObject);
        }
        object.put("samples",samples);
        return object;
    }


}