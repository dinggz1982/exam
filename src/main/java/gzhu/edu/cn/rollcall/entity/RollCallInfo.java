package gzhu.edu.cn.rollcall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import gzhu.edu.cn.base.entity.BaseEntity;
import gzhu.edu.cn.system.entity.User;

import java.util.Date;

/**
 * 点名的具体情况
 * <p>Title : RollCallInfo</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 */
@Entity
@Table(name = "its_rollcallinfo")
public class RollCallInfo extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	//被点名的学生
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	//被点名的学生
	@ManyToOne
	@JoinColumn(name = "rollCall_id")
	private RollCall rollCall;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	private Date createTime;
	
	
	public RollCall getRollCall() {
		return rollCall;
	}

	public void setRollCall(RollCall rollCall) {
		this.rollCall = rollCall;
	}

	//1.上课 2.迟到  3.早退 4.请假  
	private int type;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
