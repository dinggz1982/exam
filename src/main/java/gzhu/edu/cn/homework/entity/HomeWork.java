package gzhu.edu.cn.homework.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

    private float score;

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @OneToOne
    @JoinColumn(name="creator_id")
    private User creator;

    private Date createTime;

    @ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @OrderBy("id asc")
    private Set<User> students;

    public long getId() {
        return id;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Set<User> getStudents() {
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }
}