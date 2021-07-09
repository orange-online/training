package cn.deepdraw.training.keygen.gateway.app.domain.event.executor;

import java.util.concurrent.ExecutorService;

/**
 * IDSegmentOfferEvent Executor
 * @author huangjiancheng
 * @Date 2021-07-09
 */
public interface IDSegmentOfferEventExecutor {
	
	int getCapacity();
	
	long getCreatedDate();
	
	ExecutorService getService();
}