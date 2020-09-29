package gzhu.edu.cn.problem.entity;


import gzhu.edu.cn.base.entity.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 试题订单Entity
 */
public class ProblemOrderList extends BaseEntity {
	
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

	private Integer orderid;		// 关联：试题订单表
	private Integer problemid;		// 试题id
	private Integer price;		// 下单时的价格
	private ProblemBaseInformation probleBaseInformation;//题目实体类
	
	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	public Integer getProblemid() {
		return problemid;
	}

	public void setProblemid(Integer problemid) {
		this.problemid = problemid;
	}
	
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public ProblemBaseInformation getProbleBaseInformation() {
		return probleBaseInformation;
	}

	public void setProbleBaseInformation(ProblemBaseInformation probleBaseInformation) {
		this.probleBaseInformation = probleBaseInformation;
	}
	
	
}