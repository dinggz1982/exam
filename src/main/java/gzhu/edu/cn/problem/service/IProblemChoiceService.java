package gzhu.edu.cn.problem.service;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.problem.entity.ProblemChoice;
import gzhu.edu.cn.system.entity.User;


/**
 * @program: exam
 * @description: 选择题服务
 * @author: 丁国柱
 * @create: 2020-10-18 18:44
 */
public interface IProblemChoiceService extends BaseService<ProblemChoice,Integer> {

    /**
     * 保存或更新一个单选题
     * @param problemId
     * @param problemTitle
     * @param score
     * @param choiceId
     * @param content
     * @param itemA
     * @param itemB
     * @param itemC
     * @param itemD
     * @param itemE
     * @param itemF
     * @param answer
     */
    public void saveSingleChoice(User user, Integer problemId, String problemTitle, int score, Integer choiceId, String content, String itemA, String itemB, String itemC, String itemD, String itemE, String itemF, String answer);

    /**
     * 根据题目id获取到选项
     * @param problemId
     * @return
     */
    public ProblemChoice getItemsByProblemId(Integer problemId);

    /**
     * 对单选题进行测评
     * @param myhomeworkId
     * @param problemId
     * @param choiceId
     * @param user
     */
    public void judgeProblemChoice(Long myhomeworkId,int problemId,int choiceId,User user,int userChoiceItemId,String userChoiceItem);



}