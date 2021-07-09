package cn.deepdraw.training.keygen.gateway.app.domain.event.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * IDSegmentOfferEventExecutor Service
 * @author huangjiancheng
 * @Date 2021-07-09
 */
public class IDSegmentOfferEventExecutorService implements IDSegmentOfferEventExecutor {
	
	private int capacity;
	
	private long createdDate;
	
	private ExecutorService service;
	
	private IDSegmentOfferEventExecutorService() {}
	
	private IDSegmentOfferEventExecutorService(int capacity) {
		
		this.capacity = capacity;
		this.createdDate = System.currentTimeMillis();
		this.service = getService(capacity);
	}
	
	public static IDSegmentOfferEventExecutorService of(int capacity) {
		
		return new IDSegmentOfferEventExecutorService(capacity);
	}
	
	private int calculateCorePoolSize(int capacity) {
		
		return Math.max(capacity / 2, NumberUtils.INTEGER_ONE);
	}
	
	private int calculateMaximumPoolSize(int capacity) {
		
		return capacity;
	}

	private ExecutorService getService(int capacity) {
		
		return new ThreadPoolExecutor(calculateCorePoolSize(capacity), calculateMaximumPoolSize(capacity), 5L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(capacity));
	}

	@Override
	public int getCapacity() {
		
		return this.capacity;
	}

	@Override
	public long getCreatedDate() {
		
		return createdDate;
	}

	@Override
	public ExecutorService getService() {
		
		return service;
	}

	public void shutdown() {
		
		this.service.shutdown();
	}

	public boolean awaitTermination(Integer timeout, TimeUnit unit) throws InterruptedException {
		
		return this.service.awaitTermination(timeout, unit);
	}
	
	@Override
	public String toString() {
		
		return "{\"capacity\":" + this.capacity + ",\"createdDate\":" + this.createdDate + "}";
	}

	public boolean isShutdown() {
		
		return this.service.isShutdown();
	}
}