package gzhu.edu.cn.homework.model;

import gzhu.edu.cn.homework.entity.HomeWork;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.student.entity.Student;

import java.util.List;
import java.util.Set;

/**
 * @program: exam
 * @description: 班级编程测评记录
 * @author: 丁国柱
 * @create: 2020-10-15 10:09
 */
public class ClassHomeWorkForProgrammingInfo {

    private ClassInfo classInfo;

    private HomeWork homeWork;

    private List<StudentHomeWorkProblem> studentHomeWorkProblems;

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public HomeWork getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(HomeWork homeWork) {
        this.homeWork = homeWork;
    }

    public List<StudentHomeWorkProblem> getStudentHomeWorkProblems() {
        return studentHomeWorkProblems;
    }

    public void setStudentHomeWorkProblems(List<StudentHomeWorkProblem> studentHomeWorkProblems) {
        this.studentHomeWorkProblems = studentHomeWorkProblems;
    }
}