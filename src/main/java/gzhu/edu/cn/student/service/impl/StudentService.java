package gzhu.edu.cn.student.service.impl;

import gzhu.edu.cn.base.dao.impl.BaseDAOImpl;
import gzhu.edu.cn.student.entity.Student;
import gzhu.edu.cn.student.service.IStudentService;
import org.springframework.stereotype.Service;


/**
 * @program: exam
 * @description:学生服务层实现
 * @author: 丁国柱
 * @create: 2020-03-25 08:10
 */
@Service("studentService")
public class StudentService extends BaseDAOImpl<Student,Long> implements IStudentService {


}