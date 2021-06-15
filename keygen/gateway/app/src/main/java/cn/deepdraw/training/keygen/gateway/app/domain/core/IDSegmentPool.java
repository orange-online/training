package cn.deepdraw.training.keygen.gateway.app.domain.core;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.math.NumberUtils;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * IDSegment Pool
 * @author huangjiancheng
 * @Date 2021-05-31
 */
public class IDSegmentPool {
	
	private static final int DEFAULT_CAPACITY = 2;
	
	private static final int MAX_CAPACITY = 2 << 5; // max pool size set to 64.
	
	private LinkedBlockingQueue<IDSegment> queue;
	
	private AtomicInteger capacity;
	
	private IDSegmentPool() {
		
		initCapacity(DEFAULT_CAPACITY);
		ensureQueue();
	}
	
	private IDSegmentPool(int capacity) {
		
		initCapacity(capacity);
		ensureQueue();
	}
	
	private void initCapacity(int capacity) {
		
		this.capacity = new AtomicInteger(ensureCapacity(capacity));
	}
	
	private int ensureCapacity(int capacity) {
		
		if (capacity <= NumberUtils.INTEGER_ZERO) {
			
			throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		} else {
			
			return NumberUtils.min(capacity, MAX_CAPACITY);
		}
	}
	
	private void ensureQueue() {
		
		this.queue = new LinkedBlockingQueue<IDSegment>(MAX_CAPACITY);
	}

	public static IDSegmentPool getInstance(int capacity) {
		
		return new IDSegmentPool(capacity);
	}
	
	public boolean offer(IDSegment segment) {
		
		if (this.isFull()) {
			
			throw new WebAppRuntimeException("segment pool is full");
		}
		return this.queue.offer(segment);
	}
	
	public IDSegment take() throws InterruptedException {
		
		return this.queue.take();
	}
	
	public int capacity() {
		
		return this.capacity.get();
	}
	
	public int size() {
		
		return this.queue.size();
	}
	
	public boolean resize(int capacity) {
		
		return this.capacity.compareAndSet(this.capacity(), ensureCapacity(capacity));
	}
	
	public boolean resizeIfNeccessary(int capacity) {
		
		return this.capacity() == capacity ? true : resize(capacity);
	}
	
	public boolean isFull() {
		
		return this.size() >= this.capacity();
	}
	
	public boolean isEmpty() {
		
		return this.size() == 0;
	}
}