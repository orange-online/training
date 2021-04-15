package cn.deepdraw.training.framework.orm.mysql.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import cn.deepdraw.training.framework.orm.mysql.constants.ColumnDefinitionConstants;

/**
 * 抽象Entity
 * 所有属性通过底层自动赋值，禁止人为干预。
 * @author huangjiancheng
 * 2019-03-15
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityBase implements Serializable {

	private static final long serialVersionUID = Long.MAX_VALUE;

	@Column(name = "removed", columnDefinition = ColumnDefinitionConstants.TINYINT_1)
	private boolean removed = false;

	@CreatedDate
	@Column(name = "created_date", columnDefinition = ColumnDefinitionConstants.DATETIME)
	private Date createdDate;

	@CreatedBy
	@Column(name = "created_by", columnDefinition = ColumnDefinitionConstants.VARCHAR_45)
	private String createdBy;

	@LastModifiedDate
	@Column(name = "last_modified_date", columnDefinition = ColumnDefinitionConstants.DATETIME)
	private Date lastModifiedDate;

	@LastModifiedBy
	@Column(name = "last_modified_by", columnDefinition = ColumnDefinitionConstants.VARCHAR_45)
	private String lastModifiedBy;

	public boolean removed() {

		return removed;
	}

	public Date createdDate() {

		return createdDate;
	}

	public String createdBy() {

		return createdBy;
	}

	public Date lastModifiedDate() {

		return lastModifiedDate;
	}

	public String lastModifiedBy() {

		return lastModifiedBy;
	}

	public void remove() {

		this.removed = true;
	}
}