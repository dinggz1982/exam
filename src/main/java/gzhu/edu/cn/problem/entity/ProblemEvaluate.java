package gzhu.edu.cn.problem.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 编程题评价Entity
 */
public class ProblemEvaluate extends BaseEntity {
	
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

	private String type;		// 题目类型
	private String problemId;		// 题目ID
	private String userId;		// 用户ID
	private String koinfos;		// 知识点名称
	private String evaluate;		// 评价:1 点赞 2 差评

	@Length(min=0, max=11, message="题目类型长度必须介于 0 和 11 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=11, message="题目ID长度必须介于 0 和 11 之间")
	public String getProblemId() {
		return problemId;
	}

	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}
	
	@Length(min=0, max=11, message="用户ID长度必须介于 0 和 11 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=500, message="知识点名称长度必须介于 0 和 500 之间")
	public String getKoinfos() {
		return koinfos;
	}

	public void setKoinfos(String koinfos) {
		this.koinfos = koinfos;
	}
	
	@Length(min=0, max=11, message="评价:1 点赞 2 差评长度必须介于 0 和 11 之间")
	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	
}