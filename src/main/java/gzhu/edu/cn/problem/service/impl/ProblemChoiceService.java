package gzhu.edu.cn.problem.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import gzhu.edu.cn.problem.entity.ProblemChoice;
import gzhu.edu.cn.problem.entity.ProblemChoiceItem;
import gzhu.edu.cn.problem.service.IProblemBaseInformationService;
import gzhu.edu.cn.problem.service.IProblemChoiceItemService;
import gzhu.edu.cn.problem.service.IProblemChoiceService;
import gzhu.edu.cn.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: exam
 * @description:
 * @author: 丁国柱
 * @create: 2020-10-18 18:45
 */
@Service("problemChoiceService")
public class ProblemChoiceService extends BaseDAOImpl<ProblemChoice, Integer> implements IProblemChoiceService {

    @Autowired
    private IProblemBaseInformationService problemBaseInformationService;

    @Autowired
    private IProblemChoiceItemService problemChoiceItemService;

    @Override
    @Transactional
    public void saveSingleChoice(User user, Integer problemId, String problemTitle, int score, Integer choiceId, String content, String itemA, String itemB, String itemC, String itemD, String itemE, String itemF, String answer) {
        //如果problemId存在，在更新
        ProblemBaseInformation problem = null;
        if (problemId != null && problemId > 0) {
            problem = this.problemBaseInformationService.findById(problemId);
            problem.setTitle(problemTitle);
            problem.setType(3);
            this.problemBaseInformationService.update(problem);
        } else {
            //不存在则保存
            problem = new ProblemBaseInformation();
            problem.setTitle(problemTitle);
            problem.setType(3);
            problem.setCreateUser(user);
            this.problemBaseInformationService.save(problem);
        }
        //如果单选题对应的id已经存在
        ProblemChoice choice = null;
        if (choiceId != null && choiceId > 0) {
            choice = this.findById(choiceId);
            choice.setContent(content);
            choice.setProblemBaseInformation(problem);
            this.update(choice);
        }else{
            //如果选择题不存在
            choice = new ProblemChoice();
            choice.setContent(content);
            choice.setProblemBaseInformation(problem);
            this.save(choice);
        }

        //保存选项
        //1.先删除以前的选项
         problemId = problem.getId();
        this.problemChoiceItemService.executeSql("delete from problem_choice_item where problemId="+problemId);
        List<ProblemChoiceItem> problemChoiceItems = new ArrayList<>();
        if(itemA!=null&&itemA.length()>0){
            ProblemChoiceItem item1 = new ProblemChoiceItem();
            item1.setItemIndex(1);
            item1.setProblemId(problemId);
            item1.setItem(itemA);
            if(answer.equals("A")){
                item1.setIsRightAnswer(true);
            }else{
                item1.setIsRightAnswer(false);
            }
            problemChoiceItems.add(item1);
        }
        if(itemB!=null&&itemB.length()>0){
            ProblemChoiceItem item2 = new ProblemChoiceItem();
            item2.setItemIndex(2);
            item2.setProblemId(problemId);
            item2.setItem(itemB);
            if(answer.equals("B")){
                item2.setIsRightAnswer(true);
            }else{
                item2.setIsRightAnswer(false);
            }
            problemChoiceItems.add(item2);
        }
        if(itemC!=null&&itemC.length()>0){
            ProblemChoiceItem item3 = new ProblemChoiceItem();
            item3.setItemIndex(3);
            item3.setProblemId(problemId);
            item3.setItem(itemC);
            if(answer.equals("C")){
                item3.setIsRightAnswer(true);
            }else{
                item3.setIsRightAnswer(false);
            }
            problemChoiceItems.add(item3);
        }
        if(itemD!=null&&itemD.length()>0){
            ProblemChoiceItem item4 = new ProblemChoiceItem();
            item4.setItemIndex(4);
            item4.setProblemId(problemId);
            item4.setItem(itemD);
            if(answer.equals("D")){
                item4.setIsRightAnswer(true);
            }else{
                item4.setIsRightAnswer(false);
            }
            problemChoiceItems.add(item4);
        }
        if(itemE!=null&&itemE.length()>0){
            ProblemChoiceItem item5 = new ProblemChoiceItem();
            item5.setItemIndex(5);
            item5.setProblemId(problemId);
            item5.setItem(itemE);
            if(answer.equals("E")){
                item5.setIsRightAnswer(true);
            }else{
                item5.setIsRightAnswer(false);
            }
            problemChoiceItems.add(item5);
        }
        if(itemF!=null&&itemF.length()>0){
            ProblemChoiceItem item6 = new ProblemChoiceItem();
            item6.setItemIndex(6);
            item6.setProblemId(problemId);
            item6.setItem(itemF);
            if(answer.equals("F")){
                item6.setIsRightAnswer(true);
            }else{
                item6.setIsRightAnswer(false);
            }
            problemChoiceItems.add(item6);
        }
        this.problemChoiceItemService.batchSave(problemChoiceItems);
    }

    @Override
    public ProblemChoice getItemsByProblemId(Integer problemId) {
        List<ProblemChoiceItem> items =  this.problemChoiceItemService.find(" problemId="+problemId);
        ProblemChoice choice = this.getByHql(" problem_id="+problemId);
        choice.setChoiceItem(items);
        return choice;
    }
}