package gzhu.edu.cn.profile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import gzhu.edu.cn.base.entity.BaseEntity;

/**
 * 学期
 * @author dingguozhu
 * @date 2020年3月10日 下午10:43:15
 * @description
 */
@Entity
@Table(name = "profile_study_team")
public class StudyTeam extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	// 学期名字
	private String name;

	// 对应的年份
	private String year;

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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
}
