package gzhu.edu.cn.problem.entity;

import java.util.List;
import gzhu.edu.cn.base.entity.BaseEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 选择填空题描述Entity
 * @version 2018-12-06
 */
public class ProblemChoiceFillDescription extends BaseEntity {
	
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
	private String content;		// 填空题转换后的内容
	private String[] answers;
	private List<ProblemChoiceFillItem> choiceFillItem;

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
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}

	public List<ProblemChoiceFillItem> getChoiceFillItem() {
		return choiceFillItem;
	}

	public void setChoiceFillItem(List<ProblemChoiceFillItem> choiceFillItem) {
		this.choiceFillItem = choiceFillItem;
	}
	
	
	
}