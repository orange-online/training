package cn.deepdraw.training.keygen.gateway.app.domain.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.keygen.gateway.app.domain.event.IDSegmentOfferEventQueueCenter;
import cn.deepdraw.training.keygen.gateway.app.infrastructure.facade.dubbo.IDSegmentPoolConfigClient;
import lombok.extern.slf4j.Slf4j;

/**
 * IDGen Service
 * @author huangjiancheng
 * @Date 2021-05-28
 */
@Slf4j
@Component
public class IDGenService {
	
	private IDSegmentContainer segmentContainer = IDSegmentContainer.getInstance();
	
	@Autowired
	private IDSegmentOfferEventQueueCenter eventCenter;
	
	@Autowired
	private IDSegmentPoolConfigClient poolConfClient;
	
	private boolean isEmpty(IDSegment segment) {
		
		return segment == null || segment.isEmpty();
	}

	private IDSegment ensureSegment() throws InterruptedException {
		
		if (isEmpty(segmentContainer.offset())) {
			
			synchronized (IDGenService.class) {
				
				if (isEmpty(segmentContainer.offset())) {
					
					segmentContainer.offset(ensureSegmentPool().take());
					if (!segmentContainer.segmentPool().isFull()) {
						
						eventCenter.offer(segmentContainer.segmentPool().capacity(), segmentContainer.segmentPool().size()); // send a event to the provider to offer the ID segment.
					}
				}
			}
		}
		return segmentContainer.offset();
	}

	private int getLatestCapacity() {
		
		return Validate.notNull(poolConfClient.getCachedLatestConfig(), "get latest segment pool config failed.").getCapacity();
	}

	private IDSegmentPool ensureSegmentPool() throws InterruptedException {
		
		if (ObjectUtils.isEmpty(segmentContainer.segmentPool())) {
			
			synchronized (IDGenService.class) {
				
				if (ObjectUtils.isEmpty(segmentContainer.segmentPool())) {
					
					 // getting the pool capacity from config center to initialize the IDSegmentPool instance.
					segmentContainer.segmentPool(IDSegmentPool.getInstance(getLatestCapacity()));
				}
			}
		}
		segmentContainer.segmentPool().resizeIfNeccessary(getLatestCapacity());
		return segmentContainer.segmentPool();
	}

	public List<Long> genIds(int size) {
		
		try {
			
			List<Long> genIds = new ArrayList<>(size);
			for (int i = 0; i < size; i++) {
				
				Long pollId = ensureSegment().pollId();
				if (Objects.isNull(pollId)) {
					
					i--;
					continue;
				}
				genIds.add(pollId);
			}
			return genIds;
		} catch (InterruptedException e) {
			
			log.error("IdGenService::genIds Interrupted Exception", e);
		}
		return Collections.emptyList();
	}
}