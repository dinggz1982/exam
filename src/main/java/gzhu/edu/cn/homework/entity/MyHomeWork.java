package gzhu.edu.cn.homework.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.student.entity.Student;
import javax.persistence.*;
import java.util.Date;

/**
 * @program: exam
 * @description: 我的作业
 * @author: 丁国柱
 * @create: 2020-05-27 22:46
 */
@Entity(name="its_myhomework")
public class MyHomeWork extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //谁的作业
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public boolean isSubmissioned() {
        return isSubmissioned;
    }

    public void setSubmissioned(boolean submissioned) {
        isSubmissioned = submissioned;
    }

    private float score;

    //是否已提交
    @Column(columnDefinition = "bit(1) DEFAULT 0 comment '是否已提交，课堂作业只能提交一次'")
    private boolean isSubmissioned;

    /**
     * 状态0:未开始,1：已开始，2：结束
     */
    @Column(nullable=false,name="status",columnDefinition="int default 1")
    private int status;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    private Date updateTime;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "homework_id")
    private HomeWork homeWork;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public HomeWork getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(HomeWork homeWork) {
        this.homeWork = homeWork;
    }
    public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

    public String getProblemIds() {
        return problemIds;
    }

    public void setProblemIds(String problemIds) {
        this.problemIds = problemIds;
    }

    //测评对应的id，用;号隔开
    private String problemIds;
}