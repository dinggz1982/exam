package gzhu.edu.cn.homework.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.homework.entity.HomeWork;
import gzhu.edu.cn.homework.service.IHomeWorkService;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description: 学生作业服务实现
 * @author: 丁国柱
 * @create: 2020-03-27 16:05
 */
@Service("homeWorkService")
public class HomeWorkService extends BaseDAOImpl<HomeWork,Long> implements IHomeWorkService {

}