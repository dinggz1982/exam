package gzhu.edu.cn.profile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import gzhu.edu.cn.base.entity.BaseEntity;

/**
 * 科目--课元
 * <p>Title : Subject</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 * @date 2018年3月6日 下午1:03:45
 */
@Entity
@Table(name = "profile_subject")
public class Subject extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	

	//科目名字
	private String name;
	
	//科目代码
	private String code;
	
	
	//对应的专业
	@ManyToOne
	@JoinColumn(name = "major_id")
	private Major major;


	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}

	//科目描述
	@Type(type = "text")
	@Column(columnDefinition = "text comment '科目说明'")
	private String description;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
