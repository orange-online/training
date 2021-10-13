package cn.deepdraw.training.keygen.pool.app.domain.core;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

@Component
public class IDSegmentService {
	
	@Autowired
	private IDSegmentRepository segmentRepo;
	
	public IDSegment create(IDSegment segment) {
		
		List<IDSegment> segments = segmentRepo.findByStartInclusive(segment.startInclusive());
		if(CollectionUtils.isNotEmpty(segments)) {
			
			throw new WebAppRuntimeException("start_inclusive_must_not_be_included");
		}
		return segmentRepo.create(segment);
	}
	
	public IDSegment getLatestIDSegment() {
		// TODO cache
		List<IDSegment> segments = segmentRepo.findByConsumed(false);
		IDSegment segment = CollectionUtils.isEmpty(segments) ? null : segments.get(0);
		return !Objects.isNull(segment) ? segmentRepo.update(segment.consume()) : segment;
	}
}