package gzhu.edu.cn.problem.entity;

import gzhu.edu.cn.base.entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 选择题、判断题目选项Entity
 */
@Entity(name="problem_choice_item")
public class ProblemChoiceItem extends BaseEntity {
	
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

	private boolean isRightAnswer;		// 本选项是否是正确答案
	private String item;		// 选项的内容
	private Integer itemIndex;		// 第几个选项
	

	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	
	public boolean getIsRightAnswer() {
		return isRightAnswer;
	}

	public void setIsRightAnswer(boolean isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Integer getItemIndex() {
		return itemIndex;
	}

	public void setItemIndex(Integer itemIndex) {
		this.itemIndex = itemIndex;
	}
	
	
	
}