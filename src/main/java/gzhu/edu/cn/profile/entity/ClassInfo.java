package gzhu.edu.cn.profile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import gzhu.edu.cn.base.entity.BaseEntity;

/**
 * 班级信息
 * <p>Title : ClassInfo</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 */
@Entity
@Table(name = "profile_classinfo")
public class ClassInfo extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 10)
	private int id;

	//班级名字
	private String name;
	
	//年级代码
	private String grade;

	//对应专业
	@ManyToOne
	@JoinColumn(name = "major_id")
	private Major major;

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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Major getMajor() {
		return major;
	}

	public void setMajor(Major major) {
		this.major = major;
	}
}
