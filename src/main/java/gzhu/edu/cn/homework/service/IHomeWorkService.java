package gzhu.edu.cn.homework.service;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.homework.entity.HomeWork;

import java.util.List;

/**
 * 作业服务接口
 */
public interface IHomeWorkService extends BaseService<HomeWork,Long> {

    /**
     * 保存多个作业
     * @param homeWorks
     */
    public void saveHomeWorks(List<HomeWork> homeWorks);
}
