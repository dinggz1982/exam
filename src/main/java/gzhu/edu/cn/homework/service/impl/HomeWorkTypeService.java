package gzhu.edu.cn.homework.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.homework.entity.HomeWorkType;
import gzhu.edu.cn.homework.service.IHomeWorkTypeService;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description: 学生作业类型
 * @author: 丁国柱
 * @create: 2020-03-27 16:05
 */
@Service("homeWorkTypeService")
public class HomeWorkTypeService extends BaseDAOImpl<HomeWorkType, Integer> implements IHomeWorkTypeService {


}