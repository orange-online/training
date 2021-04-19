package cn.deepdraw.training.framework.api.conv.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.api.dto.page.PageRequest;

/**
 * PageRequest Conv
 * @author huangjiancheng
 * @Date 2021-04-19
 */
@Component
public class PageRequestConv {

	@Autowired
	private OrderByConv conv;

	private org.springframework.data.domain.PageRequest conv(int offset, int limit, String orderBy) {

		return org.springframework.data.domain.PageRequest.of(offset, limit, conv.conv(orderBy));
	}

	public org.springframework.data.domain.PageRequest conv(PageRequest request) {

		return conv(request.offset(), request.limit(), request.getOrderBy());
	}

	public org.springframework.data.domain.PageRequest conv(Integer offset, Integer limit, String orderBy, Boolean count) {

		return conv(PageRequest.instance().offset(offset).limit(limit).orderBy(orderBy).count(count));
	}

	public org.springframework.data.domain.PageRequest conv(Integer offset, Integer limit, String orderBy) {

		return conv(PageRequest.instance().offset(offset).limit(limit).orderBy(orderBy));
	}

	public org.springframework.data.domain.PageRequest conv(Integer offset, Integer limit) {

		return conv(PageRequest.instance().offset(offset).limit(limit));
	}

	public org.springframework.data.domain.PageRequest conv(Integer offset) {

		return conv(PageRequest.instance().offset(offset));
	}
}