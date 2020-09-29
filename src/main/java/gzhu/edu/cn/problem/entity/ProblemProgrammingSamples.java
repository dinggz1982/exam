package gzhu.edu.cn.problem.entity;

import javax.persistence.*;
import gzhu.edu.cn.base.entity.BaseEntity;

/**
 * 编程题输入输出Entity
 */
@Entity(name="problem_programming_samples")
public class ProblemProgrammingSamples extends BaseEntity {
	
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

	@ManyToOne
	@JoinColumn(name = "problemId")
	private ProblemBaseInformation problem;		// 关联题目

	private String inputsample;		// 输入样例
	private String outputsample;		// 输出样例
	private Integer sampleindex;		// 样例编号

	public ProblemBaseInformation getProblem() {
		return problem;
	}

	public void setProblem(ProblemBaseInformation problem) {
		this.problem = problem;
	}

	public String getInputsample() {
		return inputsample;
	}

	public void setInputsample(String inputsample) {
		this.inputsample = inputsample;
	}
	
	public String getOutputsample() {
		return outputsample;
	}

	public void setOutputsample(String outputsample) {
		this.outputsample = outputsample;
	}
	
	public Integer getSampleindex() {
		return sampleindex;
	}

	public void setSampleindex(Integer sampleindex) {
		this.sampleindex = sampleindex;
	}
	
}