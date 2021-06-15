package cn.deepdraw.training.keygen.gateway.app.domain.core;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.stereotype.Component;

/**
 * IDSegmentOfferEventQueue
 * @author huangjiancheng
 * @Date 2021-06-07
 */
@Component
public class IDSegmentOfferEventQueue {
	
	private LinkedBlockingQueue<IDSegmentOfferEvent> queue = new LinkedBlockingQueue<>();
	
	public boolean offer(int capacity, int size) {
		
		return queue.offer(IDSegmentOfferEvent.of(capacity, size));
	}
	
	public IDSegmentOfferEvent take() throws InterruptedException {
		
		return queue.take(); // TODO there need a consumer to consume the queue.
	}
}