package gzhu.edu.cn.problem.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 完善程序题代码Entity
 */
public class ProblemCompleteCode extends BaseEntity {
	
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

	private Integer problemId;		// 关联题目基本信息表problem_base_information的id
	private Integer programmingId;		// 对应编程题的id
	private String completeCode;		// 完整的代码
	private String uncompleteCode;		// 填空题的题目内容
	private String content;		// 替换后的内容
	private String solution;		// 题解
	private String code;//不带html的代码
	private String[] answers;
	private String fillCode;//engoc加密的挖空代码
	private String fillMsgCode;//engocBB加密的挖空代码

	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	
	public Integer getProgrammingId() {
		return programmingId;
	}

	public void setProgrammingId(Integer programmingId) {
		this.programmingId = programmingId;
	}
	
	public String getCompleteCode() {
		return completeCode;
	}

	public void setCompleteCode(String completeCode) {
		this.completeCode = completeCode;
	}
	
	public String getUncompleteCode() {
		return uncompleteCode;
	}

	public void setUncompleteCode(String uncompleteCode) {
		this.uncompleteCode = uncompleteCode;
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

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFillCode() {
		return fillCode;
	}

	public void setFillCode(String fillCode) {
		this.fillCode = fillCode;
	}

	public String getFillMsgCode() {
		return fillMsgCode;
	}

	public void setFillMsgCode(String fillMsgCode) {
		this.fillMsgCode = fillMsgCode;
	}
	
	
	
}