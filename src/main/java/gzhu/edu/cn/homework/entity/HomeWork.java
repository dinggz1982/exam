package gzhu.edu.cn.homework.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.profile.entity.ClassInfo;
import gzhu.edu.cn.profile.entity.Course;
import gzhu.edu.cn.system.entity.Role;
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

    @ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @OrderBy("id asc")
    private Set<ClassInfo> classInfos;

    public String getProblemIds() {
        return problemIds;
    }

    public void setProblemIds(String problemIds) {
        this.problemIds = problemIds;
    }

    //测评对应的id，用;号隔开
    private String problemIds;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    //作业的类型1.文本/附件2.知识建构
    private int type;

    public int getEvalutionType() {
        return evalutionType;
    }

    public void setEvalutionType(int evalutionType) {
        this.evalutionType = evalutionType;
    }

    //作业完成的类型，1.课后作业, 2.课堂测评
    @Column(name = "evalutionType", columnDefinition = ("int(2) comment '作业完成的类型，1：课堂测评，2.课后作业' default 1 "))
    private int evalutionType;

    @Column(name = "content",columnDefinition="text")
    private String content;

    public Set<ClassInfo> getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(Set<ClassInfo> classInfos) {
        this.classInfos = classInfos;
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