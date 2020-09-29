package gzhu.edu.cn.problem.entity;

import java.util.List;
import gzhu.edu.cn.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * 编程题详细Entity
 */
@Entity(name="problem_programming_deatil")
public class ProblemProgrammingDeatil extends BaseEntity {
	
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

	public ProblemBaseInformation getProblem() {
		return problem;
	}

	public void setProblem(ProblemBaseInformation problem) {
		this.problem = problem;
	}

	@ManyToOne
	@JoinColumn(name = "problemId")
	private ProblemBaseInformation problem;		// 关联题目

	private String description;		// 题目描述
	private String inputstyle;		// 输入格式
	private String outputstyle;		// 输出格式
	private Integer testnumber;		// 测试点个数
	private String judgetype;		// Judge类型,包括normal_judge、goc_judge、lemon_judge
	private Integer plugMemorylimit;		// Judge插件空间限制,当Judge类型是：goc_judge、lemon_judge时，才需要填judge内存
	private Integer plugTimelimit;		// Judge插件时间限制,当Judge类型是：goc_judge、lemon_judge时，才需要填judge时限
	private String testpointinfo;		// 测试点信息格式:  时限:空间:输入文件名:输出文件名
	private String stdcode;		// 标准程序
	private String solution;		// 样例解释
	private String answerAnalysis;	//答案解析
	@Transient
	private List<ProblemProgrammingSamples> samples;
	@Transient
	private String[] inputSamples;		//输入样例
	@Transient
	private String[] outputSamples;		//输出样例
	private Integer deltDis;		// 距离抖动误差
	private Integer deltCol;		// 颜色抖动误差
	private Integer deltAp;		// 正确点数通过的比例
	private Integer deltCouts;		// 是否测试
	private String testMsg;//goc前端测评加密数据

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getInputstyle() {
		return inputstyle;
	}

	public void setInputstyle(String inputstyle) {
		this.inputstyle = inputstyle;
	}
	
	public String getOutputstyle() {
		return outputstyle;
	}

	public void setOutputstyle(String outputstyle) {
		this.outputstyle = outputstyle;
	}
	
	public Integer getTestnumber() {
		return testnumber;
	}

	public void setTestnumber(Integer testnumber) {
		this.testnumber = testnumber;
	}
	
	@Length(min=0, max=30, message="Judge类型,包括normal_judge、goc_judge、lemon_judge长度必须介于 0 和 30 之间")
	public String getJudgetype() {
		return judgetype;
	}

	public void setJudgetype(String judgetype) {
		this.judgetype = judgetype;
	}
	
	public Integer getPlugMemorylimit() {
		return plugMemorylimit;
	}

	public void setPlugMemorylimit(Integer plugMemorylimit) {
		this.plugMemorylimit = plugMemorylimit;
	}
	
	public Integer getPlugTimelimit() {
		return plugTimelimit;
	}

	public void setPlugTimelimit(Integer plugTimelimit) {
		this.plugTimelimit = plugTimelimit;
	}
	
	public String getTestpointinfo() {
		return testpointinfo;
	}

	public void setTestpointinfo(String testpointinfo) {
		this.testpointinfo = testpointinfo;
	}
	
	public String getStdcode() {
		return stdcode;
	}

	public void setStdcode(String stdcode) {
		this.stdcode = stdcode;
	}
	
	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public List<ProblemProgrammingSamples> getSamples() {
		return samples;
	}

	public void setSamples(List<ProblemProgrammingSamples> samples) {
		this.samples = samples;
	}
	
	public String[] getInputSamples() {
		return inputSamples;
	}

	public void setInputSamples(String[] inputSamples) {
		this.inputSamples = inputSamples;
	}
	
	public String[] getOutputSamples() {
		return outputSamples;
	}

	public void setOutputSamples(String[] outputSamples) {
		this.outputSamples = outputSamples;
	}

	public Integer getDeltDis() {
		return deltDis;
	}

	public void setDeltDis(Integer deltDis) {
		this.deltDis = deltDis;
	}

	public Integer getDeltCol() {
		return deltCol;
	}

	public void setDeltCol(Integer deltCol) {
		this.deltCol = deltCol;
	}

	public Integer getDeltAp() {
		return deltAp;
	}

	public void setDeltAp(Integer deltAp) {
		this.deltAp = deltAp;
	}

	public Integer getDeltCouts() {
		return deltCouts;
	}

	public void setDeltCouts(Integer deltCouts) {
		this.deltCouts = deltCouts;
	}

	public String getTestMsg() {
		return testMsg;
	}

	public void setTestMsg(String testMsg) {
		this.testMsg = testMsg;
	}

	public String getAnswerAnalysis() {
		return answerAnalysis;
	}

	public void setAnswerAnalysis(String answerAnalysis) {
		this.answerAnalysis = answerAnalysis;
	}
	
	
	
}