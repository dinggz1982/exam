package gzhu.edu.cn.problem.service;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.problem.entity.KoPoint;

public interface IKoPointService extends BaseService<KoPoint,Integer> {

    /**
     * 根据课程信息获取知识树
     * @return
     */
    public String getKoTreeByCourseId(int courseId);
}
