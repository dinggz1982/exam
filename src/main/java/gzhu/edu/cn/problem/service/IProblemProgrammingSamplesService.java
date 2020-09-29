package gzhu.edu.cn.problem.service;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.problem.entity.ProblemProgrammingSamples;

import java.util.List;

public interface IProblemProgrammingSamplesService extends BaseService<ProblemProgrammingSamples, Integer> {

    /**
     * 根据试题id，获得相关样例list
     * @param problemId
     * @return
     */
    public List<ProblemProgrammingSamples> getSamplesListByProblemId(int problemId);

}
