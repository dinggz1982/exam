package gzhu.edu.cn.problem.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import gzhu.edu.cn.problem.service.IProblemBaseInformationService;
import org.springframework.stereotype.Service;
import scala.Int;

/**
 * @program: exam
 * @description: 试题基本信息服务
 * @author: 丁国柱
 * @create: 2020-09-21 00:01
 */
@Service("problemBaseInformationService")
public class ProblemBaseInformationService extends BaseDAOImpl<ProblemBaseInformation, Integer> implements IProblemBaseInformationService {
}