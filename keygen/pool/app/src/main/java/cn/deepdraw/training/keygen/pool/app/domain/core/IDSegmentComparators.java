package cn.deepdraw.training.keygen.pool.app.domain.core;

import java.util.Comparator;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * IDSegment Comparators
 * @author huangjiancheng
 * @Date 2021-10-14
 */
public final class IDSegmentComparators {
	
	private IDSegmentComparators() {}
	
	public static Comparator<IDSegment> asc() {
		
		return (sgmt1, sgmt2) -> NumberUtils.compare(sgmt1.startInclusive(), sgmt2.startInclusive());
	}
	
	public static Comparator<IDSegment> desc() {
		
		return (sgmt1, sgmt2) -> NumberUtils.compare(sgmt2.startInclusive(), sgmt1.startInclusive());
	}
}