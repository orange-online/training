package cn.deepdraw.training.keygen.pool.app.application.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.keygen.pool.app.application.IDSegmentPoolService;
import cn.deepdraw.training.keygen.pool.app.domain.core.IDSegment;
import cn.deepdraw.training.keygen.pool.app.domain.core.IDSegmentService;

/**
 * IDSegmentPoolService Impl
 * @author huangjiancheng
 * @Date 2021-10-13
 */
@Service
@Transactional
public class IDSegmentPoolServiceImpl implements IDSegmentPoolService {
	
	@Autowired
	private IDSegmentService segmentService;

	@Override
	public IDSegment getIDSegment() {
		
		return segmentService.getLatestIDSegment();
	}

	@Override
	public IDSegment provide(long startInclusive, long endExclusive) {
		
		return segmentService.provide(IDSegment.of(startInclusive, endExclusive));
	}
}
