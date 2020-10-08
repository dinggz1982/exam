package gzhu.edu.cn.homework.service;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.homework.entity.HomeWork;
import gzhu.edu.cn.homework.entity.MyHomeWork;

import java.util.List;


/**
 * 第二步：创建实体的服务层接口
 * @author dingguozhu
 *
 */
public interface IMyHomeWorkService extends BaseService<MyHomeWork, Long>{

    //批量保存学生的作业信息
    public void batchSaveMyHomeWork(List<MyHomeWork> myHomeWorks,HomeWork homeWork);

}
