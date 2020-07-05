package gzhu.edu.cn.homework.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.profile.entity.Course;
import gzhu.edu.cn.system.entity.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @program: exam
 * @description: 学生作业
 * @author: 丁国柱
 * @create: 2020-03-27 15:54
 */
@Entity(name = "its_homework")
public class HomeWork extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String title;


    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    private Date createTime;

    private Date startTime;

    private Date endTime;

    @ManyToOne
    @JoinColumn(name = "classInfo_id")
    private ClassInfo classInfo;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    //作业的类型1.文本/附件2.知识建构
    private int type;

    private String content;

    public ClassInfo getClassInfo() {
        return classInfo;
    }

    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }

    public long getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getTeacher() {
        return teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }


}