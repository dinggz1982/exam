package gzhu.edu.cn.problem.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import gzhu.edu.cn.base.entity.BaseEntity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 编程题测评记录Entity
 * @author liangsw
 * @version 2019-01-11
 */
public class ProblemSubmissionsOffline extends BaseEntity {
	
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
	private Integer problemId;		// 题目的ID
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
	private ProblemBaseInformation problemBase;
	private String finish;	//虚拟记录 0：未结束 1:已结束 
	
	private String contestTitle;	//虚拟字段
	private String userName;	//虚拟字段
	private String userId;	//虚拟字段
	private String groupId;	//虚拟字段
	private String town;	//虚拟字段
	private String school;	//虚拟字段
	private String phone;	//虚拟字段


	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}