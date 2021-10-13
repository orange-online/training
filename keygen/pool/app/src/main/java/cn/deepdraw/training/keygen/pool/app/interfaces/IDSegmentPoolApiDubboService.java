package cn.deepdraw.training.keygen.pool.app.interfaces;

import java.util.Objects;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.keygen.pool.api.IDSegmentPoolApi;
import cn.deepdraw.training.keygen.pool.api.dto.IDSegmentDTO;
import cn.deepdraw.training.keygen.pool.app.application.IDSegmentPoolService;
import cn.deepdraw.training.keygen.pool.app.domain.core.IDSegment;

/**
 * IDSegmentPoolApi Dubbo Service
 * @author huangjiancheng
 * @Date 2021-10-13
 */
@DubboService
public class IDSegmentPoolApiDubboService implements IDSegmentPoolApi {
	
	@Autowired
	private IDSegmentPoolService poolService;

	@Override
	public IDSegmentDTO getIDSegment() {
		
		IDSegment segment = poolService.getIDSegment();
		return IDSegmentDTO.of(segment.startInclusive(), segment.endExclusive());
	}

	@Override
	public boolean provide(IDSegmentDTO segment) {
		
		return !Objects.isNull(poolService.provide(segment.getStartInclusive(), segment.getEndExclusive()));
	}
}
