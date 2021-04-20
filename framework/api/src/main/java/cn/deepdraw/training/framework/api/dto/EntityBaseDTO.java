package cn.deepdraw.training.framework.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * DTO基类
 * @author huangjiancheng
 * 2018-08-22
 */
public abstract class EntityBaseDTO implements Serializable {

	private static final long serialVersionUID = Long.MAX_VALUE;

	private boolean removed;

	private Date createdDate;

	private Long createdBy;

	private Date lastModifiedDate;

	private Long lastModifiedBy;

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(Long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
}