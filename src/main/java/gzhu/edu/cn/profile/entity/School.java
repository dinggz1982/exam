package gzhu.edu.cn.profile.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import gzhu.edu.cn.base.entity.BaseEntity;

/**
 * 学校类
 * @author dingguozhu
 *
 */
@Entity
@Table(name="profile_school")
public class School extends BaseEntity {
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",columnDefinition="int(11) comment '学校表主键' ")
	private int id;

	//学校名
	@Column(name="name",unique=true,columnDefinition="varchar(255) comment '学校名'")
	private String name;
	
	//学校地址
	@Column(name="address",columnDefinition="varchar(255) comment '学校地址'")
	private String address;
	
	//创建时间
	private Date createTime;

	
	public School() {
		super();
	}

	public School(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

}
