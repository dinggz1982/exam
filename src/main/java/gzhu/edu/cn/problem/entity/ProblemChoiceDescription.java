package gzhu.edu.cn.problem.entity;

import java.util.List;
import gzhu.edu.cn.base.entity.BaseEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 选择题目描述Entity
 */
public class ProblemChoiceDescription extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private Integer problemId;		// 试题id
	private String description;		// 描述
	private List<ProblemChoiceItem> choiceItem;	
	private String isRightAnswer;


	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
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

	public String getIsRightAnswer() {
		return isRightAnswer;
	}

	public void setIsRightAnswer(String isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}

}