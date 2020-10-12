package gzhu.edu.cn.homework.entity;

import gzhu.edu.cn.problem.entity.ProblemBaseInformation;
import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.problem.entity.ProblemSubmissions;
import gzhu.edu.cn.system.entity.User;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Date;

/**
 * @program: exam
 * @description: 我的编程作业
 * @author: 丁国柱
 * @create: 2020-10-08 23:19
 */
@Entity(name="its_myhomework_problem")
public class MyHomeWorkProblem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="myhomework_id")
    private MyHomeWork myHomeWork;

    private boolean isPass;

    private Date passTime;

    private int submissionTimes;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public String getSubmissionIds() {
        return submissionIds;
    }

    public void setSubmissionIds(String submissionIds) {
        this.submissionIds = submissionIds;
    }

    @Type(type = "text")
    @Column(columnDefinition = "text comment '针对当前试题提交的id，用;号隔开'")
    private String submissionIds;

    //排序
    private int sort;

    public ProblemSubmissions getProblemSubmission() {
        return problemSubmission;
    }

    public void setProblemSubmission(ProblemSubmissions problemSubmission) {
        this.problemSubmission = problemSubmission;
    }

    @ManyToOne
    @JoinColumn(name="submission_id")
    private ProblemSubmissions problemSubmission;

    @ManyToOne
    @JoinColumn(name="problem_id")
    private ProblemBaseInformation problem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public MyHomeWork getMyHomeWork() {
        return myHomeWork;
    }

    public void setMyHomeWork(MyHomeWork myHomeWork) {
        this.myHomeWork = myHomeWork;
    }

    public boolean isPass() {
        return isPass;
    }

    public void setPass(boolean pass) {
        isPass = pass;
    }

    public Date getPassTime() {
        return passTime;
    }

    public int getSubmissionTimes() {
        return submissionTimes;
    }

    public void setSubmissionTimes(int submissionTimes) {
        this.submissionTimes = submissionTimes;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public ProblemBaseInformation getProblem() {
        return problem;
    }

    public void setProblem(ProblemBaseInformation problem) {
        this.problem = problem;
    }
}