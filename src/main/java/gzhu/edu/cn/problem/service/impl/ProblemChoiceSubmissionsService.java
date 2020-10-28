package gzhu.edu.cn.problem.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.problem.entity.ProblemChoiceSubmissions;
import gzhu.edu.cn.problem.service.IProblemChoiceSubmissionsService;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description: 客观题（单选多选题测评）
 * @author: 丁国柱
 * @create: 2020-10-21 22:21
 */
@Service("ProblemChoiceSubmissionsService")
public class ProblemChoiceSubmissionsService extends BaseDAOImpl<ProblemChoiceSubmissions,Long> implements IProblemChoiceSubmissionsService {
}