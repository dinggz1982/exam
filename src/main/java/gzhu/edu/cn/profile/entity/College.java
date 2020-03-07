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
 * 学院
 * <p>Title : ICollege</p>
 * <p>Description : </p>
 * <p>Company : </p>
 * @author 丁国柱
 */
@Entity
@Table(name = "profile_college")
public class College extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	//学院名
	private String name;
	
	@ManyToOne
	@JoinColumn(name="school_id")
	private School school;


	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
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
	
}
