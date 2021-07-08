package cn.deepdraw.training.keygen.gateway.app.domain.event;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * IDSegmentOfferEventQueue Center
 * @author huangjiancheng
 * @Date 2021-06-07
 */
@Slf4j
@Component
public class IDSegmentOfferEventQueueCenter extends IDSegmentOfferEventCenter {
	
	private LinkedBlockingQueue<IDSegmentOfferEvent> queue = new LinkedBlockingQueue<>();
	
	@Override
	public IDSegmentOfferEvent take() {
		
		try {
			
			return queue.take();
		} catch (InterruptedException e) {
			
			log.error("method {} param: event = {}, method {} execution fails, exception message: {}, caused by: {}.",
						"IDSegmentOfferEventQueueCenter::take", "{}", "LinkedBlockingQueue::take", e.getMessage(), JsonUtils.toJson(e.getCause()));
			return null;
		}
	}

	@Override
	public boolean offer(IDSegmentOfferEvent event) {
		
		return queue.offer(event);
	}
}