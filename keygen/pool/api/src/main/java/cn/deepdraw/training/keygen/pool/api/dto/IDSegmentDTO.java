package cn.deepdraw.training.keygen.pool.api.dto;

import java.io.Serializable;

/**
 * IDSegment DTO
 * @author huangjiancheng
 * @Date 2021-07-02
 */
public class IDSegmentDTO implements Serializable {
	
	private static final long serialVersionUID = 20210702L;
	
	private long startInclusive;
	
	private long endExclusive;
	
	private IDSegmentDTO() {}
	
	private IDSegmentDTO(long startInclusive, long endExclusive) {
		
		this.startInclusive = startInclusive;
		this.endExclusive = endExclusive;
	}
	
	public static IDSegmentDTO of(long startInclusive, long endExclusive) {
		
		return new IDSegmentDTO(startInclusive, endExclusive);
	}

	public long getStartInclusive() {
		return startInclusive;
	}

	public void setStartInclusive(long startInclusive) {
		this.startInclusive = startInclusive;
	}

	public long getEndExclusive() {
		return endExclusive;
	}

	public void setEndExclusive(long endExclusive) {
		this.endExclusive = endExclusive;
	}
}