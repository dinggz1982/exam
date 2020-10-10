package gzhu.edu.cn.problem.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.profile.entity.Course;

import javax.persistence.*;

/**
 * @program: exam
 * @description: 知识点信息
 * @author: 丁国柱
 * @create: 2020-09-20 23:43
 */
@Entity(name="its_knowledge_point")
public class KoPoint extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String name;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * 对应的课程
     */
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    public KoPoint getParent() {
        return parent;
    }

    public void setParent(KoPoint parent) {
        this.parent = parent;
    }

    @ManyToOne
    @JoinColumn(name="parentId")
    private KoPoint parent;

    @Lob
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}