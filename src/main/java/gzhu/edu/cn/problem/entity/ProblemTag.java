package gzhu.edu.cn.problem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import gzhu.edu.cn.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 编程题标签Entity
 */
@Entity(name="problem_tags")
public class ProblemTag extends BaseEntity {
	
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
	private Integer problemId;		// 关联题目基本信息表

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;		// 标签名称


	@NotNull(message="关联题目基本信息表不能为空")
	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}