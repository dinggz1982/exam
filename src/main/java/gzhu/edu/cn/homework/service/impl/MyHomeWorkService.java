package gzhu.edu.cn.homework.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.homework.entity.HomeWork;
import gzhu.edu.cn.homework.entity.MyHomeWork;
import gzhu.edu.cn.homework.service.IHomeWorkService;
import gzhu.edu.cn.homework.service.IMyHomeWorkService;
import org.springframework.stereotype.Service;

/**
 * Created by 丁国柱
 * 第三步：创建实现类
 * 1.要标注@service
 * 2.extends BaseDAOImpl
 * 3.implement IPaperService
 * @program: exam
 * @description:
 * @author: 丁国柱
 * @create: 2020-03-20 16:39
 */
@Service("myHomeWorkService")
public class MyHomeWorkService extends BaseDAOImpl<MyHomeWork,Long> implements IMyHomeWorkService {

}