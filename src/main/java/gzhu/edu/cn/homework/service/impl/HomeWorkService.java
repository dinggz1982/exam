package gzhu.edu.cn.homework.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.homework.entity.HomeWork;
import gzhu.edu.cn.homework.entity.MyHomeWork;
import gzhu.edu.cn.homework.service.IHomeWorkService;
import gzhu.edu.cn.homework.service.IMyHomeWorkService;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.student.entity.Student;
import gzhu.edu.cn.student.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: exam
 * @description: 学生作业服务实现
 * @author: 丁国柱
 * @create: 2020-03-27 16:05
 */
@Service("homeWorkService")
public class HomeWorkService extends BaseDAOImpl<HomeWork, Long> implements IHomeWorkService {

    @Autowired
    private IMyHomeWorkService myHomeWorkService;

    @Autowired
    private IStudentService studentService;

    @Override
    @Transactional
    public void saveHomeWorks(List<HomeWork> homeWorks) {
        for (HomeWork homework : homeWorks
        ) {
            //保存教师的作业
            if(homework.getId()>0){
                this.update(homework);
            }else{
                this.save(homework);
            }
            List<MyHomeWork> myHomeWorks = new ArrayList<>();
            //拿到作业对应的班级
            ClassInfo classInfo = homework.getClassInfo();
            List<Student> students = studentService.find(" classinfo_id=" + classInfo.getId());
            for (Student student : students
            ) {
                //查询当前用户的作业是否已经存在
                int count = this.myHomeWorkService.getCountBySql("select count(*) from its_myhomework where student_id=" + student.getId() + " and homework_id=" + homework.getId());
                if (count == 0) {
                    MyHomeWork myHomeWork = new MyHomeWork();
                    myHomeWork.setHomeWork(homework);
                    myHomeWork.setStudent(student);
                    myHomeWork.setUpdateTime(new Date());
                    myHomeWorks.add(myHomeWork);
                }
            }
            this.myHomeWorkService.batchSave(myHomeWorks);
        }
    }
}