package gzhu.edu.cn.problem.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import gzhu.edu.cn.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 编程题标签Entity
 */
public class ProblemTages extends BaseEntity {
	
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
	private String kopointId;		//标签id
	private String tagname;		// 标签名称
	private Kopoint kopoint;


	@NotNull(message="关联题目基本信息表不能为空")
	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	
	@Length(min=0, max=50, message="标签名称长度必须介于 0 和 50 之间")
	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public String getKopointId() {
		return kopointId;
	}

	public void setKopointId(String kopointId) {
		this.kopointId = kopointId;
	}

	public Kopoint getKopoint() {
		return kopoint;
	}

	public void setKopoint(Kopoint kopoint) {
		this.kopoint = kopoint;
	}
	
	
	
}