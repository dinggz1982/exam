package gzhu.edu.cn.problem.entity;

import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.system.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 题目基本信息Entity
 * @author 丁国柱
 */
@Entity(name="problem_base_information")
public class ProblemBaseInformation extends BaseEntity {

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "type", columnDefinition = ("int(2) comment '题目类型(1.填空题,2.判断题3.单选题,4.多选题5.编程题 6.程序填空题 7.选择填空题' "))
	private Integer type;		// 题目类型(1.填空题,2.判断题3.单选题,4.多选题5.编程题 6.程序填空题 7.选择填空题)
	private String title;		// 题目标题

	@Column(name = "difficulty", columnDefinition = ("int(2) comment '难度系数(1-5)，必填' default 1 "))
	private Integer difficulty;		// 难度系数(1-5)，必填

	@Column(name = "quality", columnDefinition = ("int(2) comment '题目质量(1-5,默认3,AI自动调整)' default 3 "))
	private Integer quality;		// 题目质量(1-5,默认3,AI自动调整)

	@Column(name = "recommendValue", columnDefinition = ("int(2) comment '推荐度(1-5,默认1,AI自动调整)' default 1 "))
	private Integer recommendValue;		// 推荐度(1-5,默认1,AI自动调整)

	@Column(name = "thumbsup", columnDefinition = ("int(2) comment '点赞' default 0 "))
	private Integer thumbsup;		// 点赞
	@Column(name = "thumbsdown", columnDefinition = ("int(2) comment '差评' default 0 "))
	private Integer thumbsdown;		// 差评
	@Column(name = "confirmStatus", columnDefinition = ("int(2) comment '审核状态：0未提交、1待审核、2:审核通过、3审核未通过' default 2 "))
	private Integer confirmStatus;		// 审核状态：0未提交、1待审核、2:审核通过、3审核未通过
	private String confirmuserid;		// 审核人的userID，支持多人审核

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	@ManyToOne
	@JoinColumn(name="create_user_id")
	private User createUser;
	@Column(name = "submitcounter", columnDefinition = ("int(32) comment '提交次数' default 0 "))
	private Integer submitcounter;		// 提交次数

	@Column(name = "acceptcounter", columnDefinition = ("int(32) comment 'accept次数' default 0 "))
	private Integer acceptcounter;		// accept次数
	@Column(name = "isPass", columnDefinition = ("bit(1) comment '用户状态' default 0 "))
	private boolean isPass;//是否发布

	@Transient
	private Integer programmingId;		// 对应编程题的id
	private Date publishTime;			//发布时间  私有题：publishTime自动为上传题目时的时间，公开题:publishTime为管理员审核通过的时间

	@Column(name = "isAccept", columnDefinition = ("bit(1) comment '是否通过' default 1 "))
	private boolean isAccept=false;				//是否通过
	private ProblemFillBlanksDescription fillDescription;//填空题描述
	private ProblemChoiceDescription choiceDescription;//选择题、判断题描述
	private ProblemChoiceItem choiceItem;//判断题
	private ProblemProgrammingDeatil programmingDeatil;//程序题详细
	private ProblemCompleteCode completeCode;//程序填空题描述
	private ProblemChoiceFillDescription choiceFillDescription;//选择填空题描述
	private ProblemChoiceSubmissions choiceSubmissions;//填空题、判断题、选择题的测评结果
	private ProblemSubmissions submissions;//程序题、程序填空题提交的测试结果
	private ProblemChoiceSubmissionsOffline choiceSubmissionsOffline;//填空题、判断题、选择题的测评结果
	private ProblemSubmissionsOffline submissionsOffline;//程序题、程序填空题提交的测试结果
	private String judgeStr;
	private Integer judgeType; //Judge类型,包括1：normal_judge、2：goc_judge、3：lemon_judge
	private String startId;
	private String endId;
	private String timeLimit;	//虚拟字段   时限
	private String space;	//虚拟字段  空间限制
	private String cogitation;	//计算思维

	//提示信息
	@Column(columnDefinition = "text")
	private String tips;

	public List<ProblemTag> getProblemTags() {
		return ProblemTags;
	}

	public void setProblemTags(List<ProblemTag> problemTags) {
		ProblemTags = problemTags;
	}

	@Transient
	private List<ProblemTag> ProblemTags;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=255, message="题目标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}
	
	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}
	
	public Integer getRecommendValue() {
		return recommendValue;
	}

	public void setRecommendValue(Integer recommendValue) {
		this.recommendValue = recommendValue;
	}
	
	public Integer getThumbsup() {
		return thumbsup;
	}

	public void setThumbsup(Integer thumbsup) {
		this.thumbsup = thumbsup;
	}
	
	public Integer getThumbsdown() {
		return thumbsdown;
	}

	public void setThumbsdown(Integer thumbsdown) {
		this.thumbsdown = thumbsdown;
	}
	
	public Integer getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(Integer confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	
	@Length(min=0, max=100, message="审核人的userID，支持多人审核长度必须介于 0 和 100 之间")
	public String getConfirmuserid() {
		return confirmuserid;
	}

	public void setConfirmuserid(String confirmuserid) {
		this.confirmuserid = confirmuserid;
	}
	
	public Integer getSubmitcounter() {
		return submitcounter;
	}

	public void setSubmitcounter(Integer submitcounter) {
		this.submitcounter = submitcounter;
	}
	
	public Integer getAcceptcounter() {
		return acceptcounter;
	}

	public void setAcceptcounter(Integer acceptcounter) {
		this.acceptcounter = acceptcounter;
	}


	public Integer getProgrammingId() {
		return programmingId;
	}

	public void setProgrammingId(Integer programmingId) {
		this.programmingId = programmingId;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public boolean isAccept() {
		return isAccept;
	}

	public void setAccept(boolean isAccept) {
		this.isAccept = isAccept;
	}

	public ProblemFillBlanksDescription getFillDescription() {
		return fillDescription;
	}

	public void setFillDescription(ProblemFillBlanksDescription fillDescription) {
		this.fillDescription = fillDescription;
	}


	public ProblemChoiceDescription getChoiceDescription() {
		return choiceDescription;
	}

	public void setChoiceDescription(ProblemChoiceDescription choiceDescription) {
		this.choiceDescription = choiceDescription;
	}


	public ProblemProgrammingDeatil getProgrammingDeatil() {
		return programmingDeatil;
	}

	public void setProgrammingDeatil(ProblemProgrammingDeatil programmingDeatil) {
		this.programmingDeatil = programmingDeatil;
	}


	public ProblemCompleteCode getCompleteCode() {
		return completeCode;
	}

	public void setCompleteCode(ProblemCompleteCode completeCode) {
		this.completeCode = completeCode;
	}



	public ProblemChoiceFillDescription getChoiceFillDescription() {
		return choiceFillDescription;
	}

	public void setChoiceFillDescription(ProblemChoiceFillDescription choiceFillDescription) {
		this.choiceFillDescription = choiceFillDescription;
	}



	public ProblemChoiceItem getChoiceItem() {
		return choiceItem;
	}

	public void setChoiceItem(ProblemChoiceItem choiceItem) {
		this.choiceItem = choiceItem;
	}

	public ProblemChoiceSubmissions getChoiceSubmissions() {
		return choiceSubmissions;
	}

	public void setChoiceSubmissions(ProblemChoiceSubmissions choiceSubmissions) {
		this.choiceSubmissions = choiceSubmissions;
	}

	public ProblemSubmissions getSubmissions() {
		return submissions;
	}

	public void setSubmissions(ProblemSubmissions submissions) {
		this.submissions = submissions;
	}

	public boolean isPass() {
		return isPass;
	}

	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}

	public String getJudgeStr() {
		return judgeStr;
	}

	public void setJudgeStr(String judgeStr) {
		this.judgeStr = judgeStr;
	}

	public Integer getJudgeType() {
		return judgeType;
	}

	public void setJudgeType(Integer judgeType) {
		this.judgeType = judgeType;
	}

	public String getStartId() {
		return startId;
	}

	public void setStartId(String startId) {
		this.startId = startId;
	}

	public String getEndId() {
		return endId;
	}

	public void setEndId(String endId) {
		this.endId = endId;
	}

	public ProblemChoiceSubmissionsOffline getChoiceSubmissionsOffline() {
		return choiceSubmissionsOffline;
	}

	public void setChoiceSubmissionsOffline(ProblemChoiceSubmissionsOffline choiceSubmissionsOffline) {
		this.choiceSubmissionsOffline = choiceSubmissionsOffline;
	}

	public ProblemSubmissionsOffline getSubmissionsOffline() {
		return submissionsOffline;
	}

	public void setSubmissionsOffline(ProblemSubmissionsOffline submissionsOffline) {
		this.submissionsOffline = submissionsOffline;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getCogitation() {
		return cogitation;
	}

	public void setCogitation(String cogitation) {
		this.cogitation = cogitation;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}
}