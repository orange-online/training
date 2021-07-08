package cn.deepdraw.training.keygen.gateway.app.domain.event.listener;

import cn.deepdraw.training.keygen.gateway.app.domain.event.IDSegmentOfferEvent;

/**
 * IDSegmentOfferEvent Listener
 * @author huangjiancheng
 * @Date 2021-07-08
 */
public interface IDSegmentOfferEventListener {
	
	void listen(IDSegmentOfferEvent event);
}