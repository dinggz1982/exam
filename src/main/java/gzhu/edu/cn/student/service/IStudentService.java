package gzhu.edu.cn.student.service;

import gzhu.edu.cn.base.service.BaseService;
import gzhu.edu.cn.student.entity.Student;

import java.util.List;

public interface IStudentService extends BaseService<Student,Long> {

    /**
     * 批量保存学生
     * @param students
     */
    public void batchSave(List<Student> students);
}
