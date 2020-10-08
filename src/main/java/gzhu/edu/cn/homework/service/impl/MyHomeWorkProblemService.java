package gzhu.edu.cn.homework.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.homework.entity.MyHomeWorkProblem;
import gzhu.edu.cn.homework.service.IMyHomeWorkProblemService;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description:
 * @author: 丁国柱
 * @create: 2020-10-08 23:29
 */
@Service("myHomeWorkProblemService")
public class MyHomeWorkProblemService extends BaseDAOImpl<MyHomeWorkProblem,Long> implements IMyHomeWorkProblemService {
}