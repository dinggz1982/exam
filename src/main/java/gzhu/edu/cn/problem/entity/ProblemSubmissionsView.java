/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package gzhu.edu.cn.problem.entity;


import gzhu.edu.cn.base.entity.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


/**
 * 我的做题记录
 * @author liangsw
 * @version 2019-02-22
 */
public class ProblemSubmissionsView extends BaseEntity {
	
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

	private String problemId;		// 题目的ID
	private String problemTitle;		// 题目标题
	private Date submitTime;		// 提交时间
	private String judgeResult;		// 测评的结果
	private Integer fromWhere;		// 来源 1.Contest 2.Chapter 3.其他
	private Integer fromWhereId;		// 来源ID
	private Date beginSubmitTime;		// 开始 提交时间
	private Date endSubmitTime;		// 结束 提交时间
	private String category;		// 类别 1：程序题 2客观题
	private String type;		// 试题类型
	private String answer;		//客观题参考答案
	private String selectedItems;		//客观题用户作答的选项答案
	private String acceptNumber;		//通过个数
	private String testNumber;			//测评点个数
	private Boolean isFullScore;			//是否满分

	public String getProblemId() {
		return problemId;
	}
	public void setProblemId(String problemId) {
		this.problemId = problemId;
	}
	public String getProblemTitle() {
		return problemTitle;
	}
	public void setProblemTitle(String problemTitle) {
		this.problemTitle = problemTitle;
	}
	public Date getSubmitTime() {
		return submitTime;
	}
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(String selectedItems) {
		this.selectedItems = selectedItems;
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
	public Boolean getIsFullScore() {
		return isFullScore;
	}
	public void setIsFullScore(Boolean isFullScore) {
		this.isFullScore = isFullScore;
	}
	
	
}