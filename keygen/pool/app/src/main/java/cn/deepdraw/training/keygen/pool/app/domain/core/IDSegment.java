package cn.deepdraw.training.keygen.pool.app.domain.core;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import cn.deepdraw.training.framework.orm.mysql.domain.IdLongEntity;

/**
 * ID Segment
 * @author huangjiancheng
 * @Date 2021-10-13
 */
@Entity
@Table(name = "idsegment")
@Cacheable
public class IDSegment extends IdLongEntity {

	private static final long serialVersionUID = 20211013L;
	
	@Column(name = "start_inclusive")
	private long startInclusive;
	
	@Column(name = "end_exclusive")
	private long endExclusive;

	@Column(name = "consumed")
	private boolean consumed;
	
	private IDSegment() {}
	
	private IDSegment(long startInclusive, long endExclusive) {
		
		Validate.exclusiveBetween(0, Long.MAX_VALUE, startInclusive, "start_inclusive_must_be_between_0_and_9223372036854775807");
		Validate.exclusiveBetween(0, Long.MAX_VALUE, endExclusive, "end_exclusive_must_be_between_0_and_9223372036854775807");
		Validate.isTrue(endExclusive > startInclusive, "end_exclusive_must_be_greater_than_start_inclusive");
		this.startInclusive = startInclusive;
		this.endExclusive = endExclusive;
		this.consumed = false;
	}
	
	public static IDSegment of(long startInclusive, long endExclusive) {
		
		return new IDSegment(startInclusive, endExclusive);
	}
	
	public long startInclusive() {
		
		return startInclusive;
	}
	
	public long endExclusive() {
		
		return endExclusive;
	}
	
	public boolean consumed() {
		
		return consumed;
	}
	
	public IDSegment consume() {
		
		this.consumed = true;
		return this;
	}
	
	@Override
	public String toString() {
		
		return "[" + startInclusive + ", " + endExclusive + ")";
	}
}
