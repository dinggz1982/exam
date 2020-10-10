package gzhu.edu.cn.problem.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import gzhu.edu.cn.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 知识点Entity
 * @author liangsw
 * @version 2018-11-30
 */
public class ProblemKoinfo extends BaseEntity {
	
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

	private Integer problemId;		// 题目id
	private String kopointid;		// 知识点id
	private Integer percent;		// 试题对应的知识点所占百分比
	private KoPoint kopoint;


	@NotNull(message="题目id不能为空")
	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	
	@Length(min=0, max=36, message="知识点id长度必须介于 0 和 36 之间")
	public String getKopointid() {
		return kopointid;
	}

	public void setKopointid(String kopointid) {
		this.kopointid = kopointid;
	}
	
	public Integer getPercent() {
		return percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

	public KoPoint getKopoint() {
		return kopoint;
	}

	public void setKopoint(KoPoint kopoint) {
		this.kopoint = kopoint;
	}
	
	
	
}