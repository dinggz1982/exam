package gzhu.edu.cn.problem.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.util.Set;

/**
 * @program: exam
 * @description:
 * @author: 丁国柱
 * @create: 2020-09-26 20:05
 */
@Entity(name="homework_problem")
public class HomeWorkProblem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Type(type = "text")
    @Column(columnDefinition = "text comment '作业说明'")
    private String description;

    /**
     * 一个作业有若干试题
     */
    @ManyToMany(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER)
    @OrderBy("id asc")
    private Set<ProblemBaseInformation> problemBaseInformations;

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

    public Set<ProblemBaseInformation> getProblemBaseInformations() {
        return problemBaseInformations;
    }

    public void setProblemBaseInformations(Set<ProblemBaseInformation> problemBaseInformations) {
        this.problemBaseInformations = problemBaseInformations;
    }
}