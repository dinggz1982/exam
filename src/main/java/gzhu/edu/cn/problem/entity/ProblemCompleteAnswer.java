package gzhu.edu.cn.problem.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 完善程序题答案Entity
 */
public class ProblemCompleteAnswer extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	private static final long serialVersionUID = 1L;
	private Integer problemId;		// 关联题目基本信息表problem_base_information的id
	private Integer answerIndex;		// 第几个空
	private String answer;		// 答案

	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	
	public Integer getAnswerIndex() {
		return answerIndex;
	}

	public void setAnswerIndex(Integer answerIndex) {
		this.answerIndex = answerIndex;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
}