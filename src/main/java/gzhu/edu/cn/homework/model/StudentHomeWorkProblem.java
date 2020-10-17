package gzhu.edu.cn.homework.model;

import gzhu.edu.cn.homework.entity.MyHomeWorkProblem;
import gzhu.edu.cn.student.entity.Student;

import java.util.List;

/**
 * @program: exam
 * @description: 学生作业汇总
 * @author: 丁国柱
 * @create: 2020-10-15 10:24
 */
public class StudentHomeWorkProblem {
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<MyHomeWorkProblem> getMyHomeWorkProblems() {
        return myHomeWorkProblems;
    }

    public void setMyHomeWorkProblems(List<MyHomeWorkProblem> myHomeWorkProblems) {
        this.myHomeWorkProblems = myHomeWorkProblems;
    }

    private Student student;

    private List<MyHomeWorkProblem> myHomeWorkProblems;


}