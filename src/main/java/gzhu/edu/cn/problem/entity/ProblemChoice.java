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
@Entity(name="problem_chioce")
public class ProblemChoice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "content",columnDefinition="text")
    private String content;

    public ProblemBaseInformation getProblemBaseInformation() {
        return problemBaseInformation;
    }

    public void setProblemBaseInformation(ProblemBaseInformation problemBaseInformation) {
        this.problemBaseInformation = problemBaseInformation;
    }

    @ManyToOne
    @JoinColumn(name="problem_id")
    private ProblemBaseInformation problemBaseInformation;

    @Transient
    private List<ProblemChoiceItem> choiceItem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<ProblemChoiceItem> getChoiceItem() {
        return choiceItem;
    }

    public void setChoiceItem(List<ProblemChoiceItem> choiceItem) {
        this.choiceItem = choiceItem;
    }
}