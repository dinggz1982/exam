package gzhu.edu.cn.problem.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 客观题测评记录表Entity
 */
public class ProblemChoiceSubmissionsOffline extends BaseEntity {
	
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


	private Integer problemId;		// 题目的ID
	private String answer;
	private String selectedItems;		// 用户作答的选项答案
	private Date submitTime;		// 提交时间
	private Integer fromWhere;		// 来源 1.Contest 2.Chapter 3.其他
	private Integer fromWhereId;		// 来源ID
	private Integer acceptNumber;		//通过个数
	private Integer testNumber;			//测评点个数
	private Integer score; 			//分数
	private String problemTitle;		// 题目标题
	private Date beginSubmitTime;		// 开始 提交时间
	private Date endSubmitTime;		// 结束 提交时间
	private String type;		// 试题类型
	private Boolean isFullScore;			//是否满分
	private ProblemBaseInformation problemBase;
	private String finish;	//虚拟记录 0：未结束 1:已结束 
	
	private String town;	//虚拟字段
	private String school;	//虚拟字段


	public Integer getProblemId() {
		return problemId;
	}

	public void setProblemId(Integer problemId) {
		this.problemId = problemId;
	}
	
	@Length(min=0, max=100, message="选项答案长度必须介于 0 和 100 之间")
	public String getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
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

	public Integer getAcceptNumber() {
		return acceptNumber;
	}

	public void setAcceptNumber(Integer acceptNumber) {
		this.acceptNumber = acceptNumber;
	}

	public Integer getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(Integer testNumber) {
		this.testNumber = testNumber;
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getFinish() {
		return finish;
	}

	public void setFinish(String finish) {
		this.finish = finish;
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
	
	
}