package gzhu.edu.cn.homework.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import gzhu.edu.cn.profile.entity.ClassInfo;

import javax.persistence.*;

/**
 * @program: exam
 * @description: 作业分析
 * @author: 丁国柱
 * @create: 2020-10-22 00:23
 */
@Entity(name="its_homework_result")
public class HomeWorkResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="homework_id")
    private HomeWork homeWork;

    @ManyToOne
    @JoinColumn(name="classinfo_id")
    private ClassInfo classInfo;

    @ManyToOne
    @JoinColumn(name="problem_id")
    private ProblemBaseInformation problem;

    //提交的人数
    private int submissionUserCount;

    //通过的人数
    private int passedUserCount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HomeWork getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(HomeWork homeWork) {
        this.homeWork = homeWork;
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public ProblemBaseInformation getProblem() {
        return problem;
    }

    public void setProblem(ProblemBaseInformation problem) {
        this.problem = problem;
    }

    public int getSubmissionUserCount() {
        return submissionUserCount;
    }

    public void setSubmissionUserCount(int submissionUserCount) {
        this.submissionUserCount = submissionUserCount;
    }

    public int getPassedUserCount() {
        return passedUserCount;
    }

    public void setPassedUserCount(int passedUserCount) {
        this.passedUserCount = passedUserCount;
    }
}