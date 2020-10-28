package gzhu.edu.cn.problem.entity;

import gzhu.edu.cn.base.entity.BaseEntity;

import javax.persistence.*;
import java.util.List;

/**
 * @program: exam
 * @description: 选择题
 * @author: 丁国柱
 * @create: 2020-10-18 18:22
 */
@Entity(name="problem_chioce_description")
public class ProblemChoice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description",columnDefinition="text")
    private String description;

    public ProblemBaseInformation getProblemBaseInformation() {
        return problemBaseInformation;
    }

    public void setProblemBaseInformation(ProblemBaseInformation problemBaseInformation) {
        this.problemBaseInformation = problemBaseInformation;
    }

    @ManyToOne
    @JoinColumn(name="problemId")
    private ProblemBaseInformation problemBaseInformation;

    @Transient
    private List<ProblemChoiceItem> choiceItem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ProblemChoiceItem> getChoiceItem() {
        return choiceItem;
    }

    public void setChoiceItem(List<ProblemChoiceItem> choiceItem) {
        this.choiceItem = choiceItem;
    }
}