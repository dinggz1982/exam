package gzhu.edu.cn.problem.web;

import gzhu.edu.cn.base.model.JsonData;
import gzhu.edu.cn.base.model.PageData;
import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import gzhu.edu.cn.problem.entity.ProblemChoice;
import gzhu.edu.cn.problem.entity.ProblemChoiceItem;
import gzhu.edu.cn.problem.service.IProblemBaseInformationService;
import gzhu.edu.cn.problem.service.IProblemChoiceItemService;
import gzhu.edu.cn.problem.service.IProblemChoiceService;
import gzhu.edu.cn.system.entity.User;
import org.bouncycastle.math.raw.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: exam
 * @description: 试题查看
 * @author: 丁国柱
 * @create: 2020-10-21 00:10
 */
@Controller
public class ViewProblemController {

    @Autowired
    private IProblemBaseInformationService problemBaseInformationService;

    @Autowired
    private IProblemChoiceService problemChoiceService;
    @Autowired
    private IProblemChoiceItemService problemChoiceItemService;

    @GetMapping("/viewAllProblem")
    public String viewAll(){

        return "problem/viewAllProblem";
    }

    @GetMapping("/viewAllProblem/list.json")
    @ResponseBody
    public JsonData<ProblemBaseInformation> userList1(Integer page, Integer limit,Integer type, String title,Integer judgeType) {
        page = page == null ? 1 : page < 1 ? 1 : page;
        limit = limit == null ? 10 : limit < 1 ? 1 : limit;

        String hql = " (judgeType!=2 or judgeType is null) and ";
        if(title!=null&&title!=""){
            hql =hql +  " title like '%"+title+"%' and";
        }
        if(judgeType!=null&&judgeType>0){
            hql = hql + " judgeType=" + judgeType +" and";
        }
        if(type!=null&&type>0){
            hql = hql + " type=" + type +" and";
        }
        if(hql.length()>0){
            hql = hql.substring(0, hql.length() - 4);
        }
        PageData<ProblemBaseInformation> pageData = this.problemBaseInformationService.getPageData(page, limit, hql);
        JsonData<ProblemBaseInformation> pageJson = new JsonData<ProblemBaseInformation>();
        pageJson.setCode(0);
        pageJson.setCount(pageData.getTotalCount());
        pageJson.setMsg("试题列表");
        pageJson.setData(pageData.getPageData());
        return pageJson;
    }


    @GetMapping("/viewProblem/{type}/{id}")
    public String viewProblem(Model model,@PathVariable Integer type,@PathVariable Integer id){
        model.addAttribute("type",type);
        //单选题
        if(type==3){
            return viewChoice(id,model);
        }
        else {
            return  null;
        }
    }

    /**
     * 查看选择题，判断题
     * @param id
     * @return
     */
    private String viewChoice(Integer id, Model model) {
        ProblemChoice problemChoice= problemChoiceService.getByHql(" and problemId="+id);
        List<ProblemChoiceItem> problemChoiceItems = problemChoiceItemService.find( " problemId="+id);
        model.addAttribute("problemChoice",problemChoice);
        model.addAttribute("problemChoiceItems",problemChoiceItems);
        return "problem/viewChoiceProblem";
    }


}