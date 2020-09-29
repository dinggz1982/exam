package gzhu.edu.cn.problem.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 试题篮子Entity
 */
public class ProblemBasket extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String userId;		// 用户id
	private String problemId;		// 题目id
	private ProblemBaseInformation problemBase;
	private String judgeStr;
	



	@Length(min=0, max=11, message="用户id长度必须介于 0 和 11 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=11, message="题目id长度必须介于 0 和 11 之间")
	public String getProblemId() {
		return problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}

	public ProblemBaseInformation getProblemBase() {
		return problemBase;
	}

	public void setProblemBase(ProblemBaseInformation problemBase) {
		this.problemBase = problemBase;
	}

	public String getJudgeStr() {
		return judgeStr;
	}

	public void setJudgeStr(String judgeStr) {
		this.judgeStr = judgeStr;
	}
	
	
}