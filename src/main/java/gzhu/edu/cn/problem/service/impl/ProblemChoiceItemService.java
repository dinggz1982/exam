package gzhu.edu.cn.problem.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.problem.entity.ProblemChoiceItem;
import gzhu.edu.cn.problem.service.IProblemChoiceItemService;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description: 选择题服务实现
 * @author: 丁国柱
 * @create: 2020-10-18 18:42
 */
@Service("problemChoiceItemService")
public class ProblemChoiceItemService extends BaseDAOImpl<ProblemChoiceItem,Integer> implements IProblemChoiceItemService {
}