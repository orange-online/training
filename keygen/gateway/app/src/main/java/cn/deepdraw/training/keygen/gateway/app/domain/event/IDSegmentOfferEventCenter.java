package cn.deepdraw.training.keygen.gateway.app.domain.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.keygen.gateway.app.domain.event.listener.IDSegmentOfferEventListener;

/**
 * IDSegmentOfferEvent Center
 * @author huangjiancheng
 * @Date 2021-07-08
 */
public abstract class IDSegmentOfferEventCenter {
	
	@Autowired
	private List<IDSegmentOfferEventListener> listeners;
	
	public abstract boolean offer(IDSegmentOfferEvent event);
	
	public boolean offer(int capacity, int size) {
		
		IDSegmentOfferEvent event = IDSegmentOfferEvent.of(capacity, size);
		if (offer(event)) {
			
			listeners.forEach(listener -> listener.listen(event));
			return true;
		}
		return false;
	}
	
	public abstract IDSegmentOfferEvent take();
}