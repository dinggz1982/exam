package gzhu.edu.cn.problem.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.knowledge.entity.ItsTag;
import org.hibernate.validator.constraints.Length;

/**
 * 题目标签Entity
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

	@ManyToOne
	@JoinColumn(name = "tag_id")
	private ItsTag tag;

	@ManyToOne
	@JoinColumn(name="problem_id")
	private ProblemBaseInformation problem;

	public void setId(Integer id) {
		this.id = id;
	}

	public ItsTag getTag() {
		return tag;
	}

	public void setTag(ItsTag tag) {
		this.tag = tag;
	}

	public ProblemBaseInformation getProblem() {
		return problem;
	}

	public void setProblem(ProblemBaseInformation problem) {
		this.problem = problem;
	}
}