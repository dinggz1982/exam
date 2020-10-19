package gzhu.edu.cn.knowledge.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.profile.entity.Course;

import javax.persistence.*;
import java.util.Objects;

/**
 * @program: exam
 * @description: 知识节点
 * @author: 丁国柱
 * @create: 2020-06-11 21:33
 */
@Entity(name = "its_knowledge")
public class Knowledge extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String knowledge;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Knowledge knowledge = (Knowledge) o;
        return id.equals(knowledge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "description",  columnDefinition = "Text")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}