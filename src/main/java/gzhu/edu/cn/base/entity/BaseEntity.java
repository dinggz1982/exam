package gzhu.edu.cn.base.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 实体基类，应用于所有表中的公共字段
 * @author dingguozhu
 *
 */
@MappedSuperclass
public abstract class BaseEntity implements java.io.Serializable{

	// 删除标示
	@Column(columnDefinition = "bit(1) DEFAULT 0 comment '软删除标识'")
	private boolean delFlag;

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

}
