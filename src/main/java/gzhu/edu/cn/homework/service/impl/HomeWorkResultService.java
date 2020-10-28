package gzhu.edu.cn.homework.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.homework.entity.HomeWorkResult;
import gzhu.edu.cn.homework.service.IHomeWorkResultService;
import gzhu.edu.cn.homework.service.IHomeWorkService;
import gzhu.edu.cn.profile.service.IClassInfoService;
import gzhu.edu.cn.student.entity.Student;
import gzhu.edu.cn.student.service.IStudentService;
import gzhu.edu.cn.system.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: exam
 * @description: 作业结果服务
 * @author: 丁国柱
 * @create: 2020-10-22 00:30
 */
@Service("homeWorkResultService")
public class HomeWorkResultService extends BaseDAOImpl<HomeWorkResult,Long> implements IHomeWorkResultService {

    @Autowired
    private IHomeWorkService homeWorkService;
    @Autowired
    private IClassInfoService classInfoService;
    @Autowired
    private IStudentService studentService;


    @Override
    public void updateHomeWorkResult(long homeWorkId, long myHomeWorkId, int problemId, boolean isPassed, User user) {
        /*Student student  = this.studentService.find();
        student.getClassInfo()*/
    }
}