/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package gzhu.edu.cn.problem.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import gzhu.edu.cn.base.entity.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 用户试题购买Entity
 */
public class ProblemVisit extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	private Integer userid;		// 用户id
	private Long problemid;		// 试题id
	private Date startTime;		// 开始时间
	private Date expiretime;		// 过期时间
	private ProblemBaseInformation problemBase;//题目实体类
	private String judgeStr;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	public Long getProblemid() {
		return problemid;
	}

	public void setProblemid(Long problemid) {
		this.problemid = problemid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpiretime() {
		return expiretime;
	}

	public void setExpiretime(Date expiretime) {
		this.expiretime = expiretime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public ProblemBaseInformation getProblemBase() {
		return problemBase;
	}

	public void setProblemBase(ProblemBaseInformation problemBase) {
		this.problemBase = problemBase;
	}

	public String getJudgeStr() {
		return judgeStr;
	}

	public void setJudgeStr(String judgeStr) {
		this.judgeStr = judgeStr;
	}

	

	
}