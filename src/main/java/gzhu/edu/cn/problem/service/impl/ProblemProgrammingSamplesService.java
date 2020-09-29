package gzhu.edu.cn.problem.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.problem.entity.ProblemProgrammingSamples;
import gzhu.edu.cn.problem.service.IProblemProgrammingSamplesService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: exam
 * @description: 编程题样例服务
 * @author: 丁国柱
 * @create: 2020-09-21 01:01
 */
@Service("problemProgrammingSamplesService")
public class ProblemProgrammingSamplesService extends BaseDAOImpl<ProblemProgrammingSamples,Integer> implements IProblemProgrammingSamplesService {
    @Override
    public List<ProblemProgrammingSamples> getSamplesListByProblemId(int problemId) {
        return this.find(" problemId="+problemId);
    }
}