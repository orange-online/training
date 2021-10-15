package cn.deepdraw.training.keygen.pool.app.domain.core;

import java.util.Collection;
import java.util.concurrent.PriorityBlockingQueue;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * IDSegment Pool
 * @author huangjiancheng
 * @Date 2021-10-14
 */
@Component
public class IDSegmentPool {
	
	private PriorityBlockingQueue<IDSegment> segments = new PriorityBlockingQueue<>(20, IDSegmentComparators.asc()); // TODO capacity
	
	public void addAll(Collection<IDSegment> segmts) {
		
		segments.addAll(segmts);
	}
	
	public void add(IDSegment segment) {
		
		segments.add(segment);
	}
	
	public IDSegment get() {
		
		try {
			
			return segments.take();
		} catch (InterruptedException e) {
			
			throw new WebAppRuntimeException("id_segment_pool_take_interrupted");
		}
	}
	
	public boolean isEmpty() {
		
		return segments.isEmpty();
	}
}