package cn.deepdraw.training.framework.api.dto.page;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * 分页请求DTO
 * @author huangjiancheng
 * 2018-09-22
 */
public class PageRequest implements Serializable {

	private static final long serialVersionUID = Long.MAX_VALUE;

	public static final int DEFAULT_OFFSET = 1;

	public static final int DEFAULT_LIMIT = 20;

	public static final int MAX_LIMIT = 100;

	public static final int MIN_LIMIT = 1;

	private Integer limit;

	private Integer offset;

	private String orderBy;

	private boolean count = false;

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public boolean isCount() {
		return count;
	}

	public void setCount(boolean count) {
		this.count = count;
	}

	public int offset() {

		return (this.offset == null || this.offset < 1 ? DEFAULT_OFFSET : this.offset.intValue()) - 1;
	}

	public PageRequest offset(Integer offset) {

		if (offset != null) {

			this.offset = offset;
		}
		return this;
	}

	public int limit() {

		if (this.limit == null) {

			return DEFAULT_LIMIT;
		}
		if (this.limit > MAX_LIMIT) {

			return MAX_LIMIT;
		} else if (this.limit < MIN_LIMIT) {

			return MIN_LIMIT;
		}
		return this.limit.intValue();
	}

	public PageRequest limit(Integer limit) {

		if (limit != null) {

			this.limit = limit;
		}
		return this;
	}

	public PageRequest orderBy(String orderBy) {

		if (StringUtils.isNotBlank(orderBy)) {

			this.orderBy = orderBy;
		}
		return this;
	}

	public PageRequest count(Boolean count) {

		if (count != null) {

			this.count = count.booleanValue();
		}
		return this;
	}

	public static PageRequest instance() {

		return new PageRequest();
	}
}