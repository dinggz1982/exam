package gzhu.edu.cn.problem.entity;


import gzhu.edu.cn.base.entity.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 填空题答案Entity
 */
public class ProblemFillBlanksAnswer extends BaseEntity {

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
	private Integer problemId;		// 试题id
	private String answer;		// 答案
	private Integer answerIndex;		// 第几个空
	private Integer judgeType;		// 数据类型

	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public Integer getAnswerIndex() {
		return answerIndex;
	}

	public void setAnswerIndex(Integer answerIndex) {
		this.answerIndex = answerIndex;
	}
	
	public Integer getJudgeType() {
		return judgeType;
	}

	public void setJudgeType(Integer judgeType) {
		this.judgeType = judgeType;
	}
	
}