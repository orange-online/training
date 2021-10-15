package cn.deepdraw.training.keygen.pool.app.domain.core;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import lombok.extern.slf4j.Slf4j;

/**
 * IDSegment Service
 * @author huangjiancheng
 * @Date 2021-10-15
 */
@Slf4j
@Component
public class IDSegmentService {
	
	@Autowired
	private IDSegmentRepository segmentRepo;
	
	@Autowired
	private IDSegmentPool segmentPool;
	
	public IDSegment provide(IDSegment segment) {
		
		List<IDSegment> segments = segmentRepo.findByStartInclusive(segment.startInclusive());
		if(CollectionUtils.isNotEmpty(segments)) {
			
			throw new WebAppRuntimeException("start_inclusive_must_not_be_included");
		}
		segment = segmentRepo.create(segment);
		segmentPool.add(segment);
		log.info("class: {}, method: {}, segment: {} has been provided.", "IDSegmentService", "provide", segment.toString());
		return segment;
	}
	
	public IDSegment getLatestIDSegment() {
		
		IDSegment segment = segmentPool.get();
		segment = segmentRepo.findByEntityId(segment.entityId());
		segment = segmentRepo.update(segment.consume());
		log.info("class: {}, method: {}, segment: {} has been consumed.", "IDSegmentService", "getLatestIDSegment", segment.toString());
		return segment;
	}
}