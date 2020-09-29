package gzhu.edu.cn.problem.service;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.problem.entity.ProblemProgrammingDeatil;

import java.util.List;

public interface IProblemProgrammingDeatilService extends BaseService<ProblemProgrammingDeatil,Integer> {

    /**
     * 根据试题id获取编程题的具体信息
     * @param problemId
     * @return
     */
    public ProblemProgrammingDeatil getByProblemId(int problemId);

    /**
     * 根据题目id获取测评数据
     * @param problemId
     * @return
     */
    public List<String> getOutTestCaseByProblemId(int problemId);

    /**
     * 根据题目ID，获取输入输出测评点数据列表
     * @param problemId
     * @return
     */
    public List<String[]> getInAndOutTestCaseByProblemId(int problemId);

}
