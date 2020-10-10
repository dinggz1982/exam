/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package gzhu.edu.cn.problem.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.system.entity.User;

import javax.persistence.*;

/**
 * 编程题测评记录Entity
 */
@Entity(name = "problem_programming_submissions")
public class ProblemSubmissions extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	public User getSubmitUser() {
		return submitUser;
	}

	public void setSubmitUser(User submitUser) {
		this.submitUser = submitUser;
	}

	@ManyToOne
	@JoinColumn(name="submitUserId")
	private User submitUser;

	private String code;		// 代码
	private Date submitTime;		// 提交时间
	private Integer status;		// 状态 1.wating 2.judging 3.done
	private String compileInfo;		// 测试编译的信息
	private String judgeResult;		// 测评的结果
	private Integer fromWhere;		// 来源 1.Contest 2.Chapter 3.其他
	private Integer fromWhereId;		// 来源ID
	private String acceptNumber;		//通过个数
	private String testNumber;			//测评点个数
	private Integer score=0; 			//分数
	private String isCompleteProgramming;	//是否为程序填空题
	private String problemTitle;		// 题目标题
	private Date beginSubmitTime;		// 开始 提交时间
	private Date endSubmitTime;		// 结束 提交时间
	private String type;		// 试题类型
	private Boolean isFullScore;			//是否满分

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	private String language; //测评的语言

	@ManyToOne
	@JoinColumn(name="problemId")
	private ProblemBaseInformation problemBase;
	private String finish;	//虚拟记录 0：未结束 1:已结束 
	
	private String contestTitle;	//虚拟字段

	private Integer mark;//虚拟字段
	private Integer sort;//虚拟字段

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getCompileInfo() {
		return compileInfo;
	}

	public void setCompileInfo(String compileInfo) {
		this.compileInfo = compileInfo;
	}
	
	public String getJudgeResult() {
		return judgeResult;
	}

	public void setJudgeResult(String judgeResult) {
		this.judgeResult = judgeResult;
	}
	
	public Integer getFromWhere() {
		return fromWhere;
	}

	public void setFromWhere(Integer fromWhere) {
		this.fromWhere = fromWhere;
	}
	
	public Integer getFromWhereId() {
		return fromWhereId;
	}

	public void setFromWhereId(Integer fromWhereId) {
		this.fromWhereId = fromWhereId;
	}

	public String getAcceptNumber() {
		return acceptNumber;
	}

	public void setAcceptNumber(String acceptNumber) {
		this.acceptNumber = acceptNumber;
	}

	public String getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(String testNumber) {
		this.testNumber = testNumber;
	}

	public String getIsCompleteProgramming() {
		return isCompleteProgramming;
	}

	public void setIsCompleteProgramming(String isCompleteProgramming) {
		this.isCompleteProgramming = isCompleteProgramming;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getProblemTitle() {
		return problemTitle;
	}

	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}

	public Date getBeginSubmitTime() {
		return beginSubmitTime;
	}

	public void setBeginSubmitTime(Date beginSubmitTime) {
		this.beginSubmitTime = beginSubmitTime;
	}

	public Date getEndSubmitTime() {
		return endSubmitTime;
	}

	public void setEndSubmitTime(Date endSubmitTime) {
		this.endSubmitTime = endSubmitTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsFullScore() {
		return isFullScore;
	}

	public void setIsFullScore(Boolean isFullScore) {
		this.isFullScore = isFullScore;
	}

	public ProblemBaseInformation getProblemBase() {
		return problemBase;
	}

	public void setProblemBase(ProblemBaseInformation problemBase) {
		this.problemBase = problemBase;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
	}

	public String getContestTitle() {
		return contestTitle;
	}

	public void setContestTitle(String contestTitle) {
		this.contestTitle = contestTitle;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}