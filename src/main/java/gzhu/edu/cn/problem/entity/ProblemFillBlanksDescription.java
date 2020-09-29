package gzhu.edu.cn.problem.entity;


import gzhu.edu.cn.base.entity.BaseEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 填空题描述Entity
 */
public class ProblemFillBlanksDescription extends BaseEntity {
	
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
	private String description;		// 填空题的题目内容。遇到预设的特殊字符串，则替换为用户要填的空
	private String content;			//填空题转换后的内容
	private String[] answer;		//答案集合
	private String[] judgeType;		//类型集合


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

	public String[] getAnswer() {
		return answer;
	}

	public void setAnswer(String[] answer) {
		this.answer = answer;
	}

	public String[] getJudgeType() {
		return judgeType;
	}

	public void setJudgeType(String[] judgeType) {
		this.judgeType = judgeType;
	}
	
	
	
	
}