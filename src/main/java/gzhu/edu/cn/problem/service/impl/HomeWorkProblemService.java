package gzhu.edu.cn.problem.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.problem.entity.HomeWorkProblem;
import gzhu.edu.cn.problem.service.IHomeWorkProblemService;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description: 编程作业
 * @author: 丁国柱
 * @create: 2020-09-26 20:13
 */
@Service("homeWorkProblemService")
public class HomeWorkProblemService extends BaseDAOImpl<HomeWorkProblem,Integer> implements IHomeWorkProblemService {
}