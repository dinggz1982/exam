package gzhu.edu.cn.knowledge.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.profile.entity.Course;

import javax.persistence.*;

/**
 * @program: exam
 * @description: 课程大纲
 * @author: 丁国柱
 * @create: 2020-10-18 17:30
 */
@Entity(name="its_outline")
public class Outline extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}