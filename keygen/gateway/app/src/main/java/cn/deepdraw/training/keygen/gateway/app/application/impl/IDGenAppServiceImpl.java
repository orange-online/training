package cn.deepdraw.training.keygen.gateway.app.application.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.keygen.gateway.app.application.IDGenAppService;
import cn.deepdraw.training.keygen.gateway.app.domain.core.IDGenService;

/**
 * IdGenAppService Impl
 * @author huangjiancheng
 * @Date 2021-05-28
 */
@Component
public class IDGenAppServiceImpl implements IDGenAppService {
	
	@Autowired
	private IDGenService service;

	@Override
	public Long genId() {
		
		List<Long> genIds = service.genIds(1);
		return CollectionUtils.isNotEmpty(genIds) ? genIds.get(0) : null;
	}

	@Override
	public List<Long> genIds(int size) {
		
		return service.genIds(size);
	}
}