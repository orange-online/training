package cn.deepdraw.training.keygen.gateway.app.domain.core;

import org.apache.commons.lang.ObjectUtils;

import cn.deepdraw.training.keygen.gateway.app.domain.shared.ValueObject;

/**
 * IDSegment Offer Event
 * @author huangjiancheng
 * @Date 2021-06-07
 */
public class IDSegmentOfferEvent implements ValueObject<IDSegmentOfferEvent> {
	
	private static final long serialVersionUID = 20210607L;

	private int capacity;
	
	private int size;
	
	private long time;
	
	private IDSegmentOfferEvent() {}
	
	private IDSegmentOfferEvent(int capacity, int size) {
		
		this.capacity = capacity;
		this.size = size;
		this.time = System.currentTimeMillis();
	}
	
	public static IDSegmentOfferEvent of(int capacity, int size) {
		
		return new IDSegmentOfferEvent(capacity, size);
	}
	
	public int capacity() {
		
		return this.capacity;
	}
	
	public int size() {
		
		return this.size;
	}
	
	public long time() {
		
		return this.time;
	}
	
	protected <T extends IDSegmentOfferEvent> boolean equals(T event) {
		
		return event != null && (this == event || (ObjectUtils.equals(this.capacity(), event.capacity()) && ObjectUtils.equals(this.size(), event.size()) && ObjectUtils.equals(this.time(), event.time())));
	}

	@Override
	public boolean equalTo(IDSegmentOfferEvent event) {
		
		return equals(event);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			
			return false;
		} else if (this == obj) {
			
			return true;
		} else if (obj instanceof IDSegmentOfferEvent) {
			
			return equals((IDSegmentOfferEvent) obj);
		} else {
			
			return false;
		}
	}

	@Override
	public int hashCode() {
		
		return this.toString().hashCode();
	}

	@Override
	public String toString() {
		
		return "{\"capacity\":" + this.capacity() + ",\"size\":" + this.size() + ",\"time\":" + this.time() + "}";
	}
}