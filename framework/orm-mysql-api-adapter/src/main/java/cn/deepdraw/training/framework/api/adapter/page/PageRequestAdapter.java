package cn.deepdraw.training.framework.api.adapter.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.api.dto.page.PageRequest;

/**
 * PageRequest创建器
 * @author huangjiancheng
 * 2018-09-22
 */
@Component
public class PageRequestAdapter {

	@Autowired
	private OrderByAdapter adapter;

	private org.springframework.data.domain.PageRequest adapt(int offset, int limit, String orderBy) {

		return org.springframework.data.domain.PageRequest.of(offset, limit, adapter.adapt(orderBy));
	}

	public org.springframework.data.domain.PageRequest adapt(PageRequest request) {

		return adapt(request.offset(), request.limit(), request.getOrderBy());
	}

	public org.springframework.data.domain.PageRequest adapt(Integer offset, Integer limit, String orderBy, Boolean count) {

		return adapt(PageRequest.instance().offset(offset).limit(limit).orderBy(orderBy).count(count));
	}

	public org.springframework.data.domain.PageRequest adapt(Integer offset, Integer limit, String orderBy) {

		return adapt(PageRequest.instance().offset(offset).limit(limit).orderBy(orderBy));
	}

	public org.springframework.data.domain.PageRequest adapt(Integer offset, Integer limit) {

		return adapt(PageRequest.instance().offset(offset).limit(limit));
	}

	public org.springframework.data.domain.PageRequest adapt(Integer offset) {

		return adapt(PageRequest.instance().offset(offset));
	}
}