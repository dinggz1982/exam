package gzhu.edu.cn.problem.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.problem.entity.ProblemTag;
import gzhu.edu.cn.problem.service.IProblemTagService;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description: 标签服务
 * @author: 丁国柱
 * @create: 2020-10-10 14:20
 */
@Service("problemTagsService")
public class ProblemTagService extends BaseDAOImpl<ProblemTag,Integer> implements IProblemTagService {
}