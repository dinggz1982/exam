package gzhu.edu.cn.homework.service;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.homework.entity.HomeWorkResult;
import gzhu.edu.cn.system.entity.User;

public interface IHomeWorkResultService extends BaseService<HomeWorkResult,Long> {

    public void updateHomeWorkResult(long homeWorkId, long myHomeWorkId, int problemId, boolean isPassed, User user);

}
