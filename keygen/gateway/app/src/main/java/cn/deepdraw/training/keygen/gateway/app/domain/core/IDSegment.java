package cn.deepdraw.training.keygen.gateway.app.domain.core;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.LongStream;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;
import org.apache.commons.lang3.math.NumberUtils;

import cn.deepdraw.training.keygen.gateway.app.domain.shared.ValueObject;

/**
 * ID Segment
 * @author huangjiancheng
 * @Date 2021-05-28
 */
public class IDSegment implements ValueObject<IDSegment> {
	
	private static final long serialVersionUID = 20210528L;
	
	private ConcurrentLinkedDeque<Long> ids;

	private long startInclusive;
	
	private long endExclusive;
	
	private long capacity;
	
	private IDSegment() {}
	
	private IDSegment(long startInclusive, long endExclusive) {
		
		Validate.notNull(this.startInclusive = startInclusive, "start_cannot_be_null");
		Validate.notNull(this.endExclusive = endExclusive, "end_cannot_be_null");
		this.capacity = this.endExclusive - this.startInclusive;
		this.ids = new ConcurrentLinkedDeque<>();
		offerIds();
	}
	
	private void offerIds() {
		
		LongStream.range(this.startInclusive, this.endExclusive).forEach(id -> this.ids.offer(id));
	}
	
	public static IDSegment of(long startInclusive, long endExclusive) {
		
		return new IDSegment(startInclusive, endExclusive);
	}
	
	public long startInclusive() {
		
		return this.startInclusive;
	}
	
	public long endExclusive() {
		
		return this.endExclusive;
	}
	
	public long capacity() {
		
		return this.capacity;
	}
	
	public Long pollId() {
		
		return this.ids.poll();
	}
	
	public int size() {
		
		return this.ids.size();
	}
	
	public boolean isEmpty() {
		
		return size() == NumberUtils.INTEGER_ZERO;
	}
	
	protected <T extends IDSegment> boolean equals(T segment) {
		
		return segment != null && (this == segment || (ObjectUtils.equals(this.startInclusive(), segment.startInclusive()) && ObjectUtils.equals(this.endExclusive(), segment.endExclusive())));
	}

	@Override
	public boolean equalTo(IDSegment segment) {
		
		return equals(segment);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			
			return false;
		} else if (this == obj) {
			
			return true;
		} else if (obj instanceof IDSegment) {
			
			return equals((IDSegment) obj);
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
		
		return "{\"startInclusive\":" + this.startInclusive() + ",\"endExclusive\":" + this.endExclusive() + ",\"capacity\":" + this.capacity() + "}";
	}
}