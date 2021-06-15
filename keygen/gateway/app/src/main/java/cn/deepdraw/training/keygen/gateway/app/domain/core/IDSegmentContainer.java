package cn.deepdraw.training.keygen.gateway.app.domain.core;

/**
 * IDSegment Container
 * @author huangjiancheng
 * @Date 2021-06-07
 */
public class IDSegmentContainer {
	
	private volatile IDSegment offset; // always pointing to current available IDSegment.
	
	private volatile IDSegmentPool segmentPool; // pointing to the unique IDSegmentPool pool.
	
	private IDSegmentContainer() {}
	
	private static final class IDSegmentContainerHolder {
		
		public static final IDSegmentContainer INSTANCE = new IDSegmentContainer();
	}
	
	public static IDSegmentContainer getInstance() {
		
		return IDSegmentContainerHolder.INSTANCE;
	}
	
	public IDSegment offset() {
		
		return this.offset;
	}
	
	public void offset(IDSegment offset) {
		
		this.offset = offset;
	}
	
	public IDSegmentPool segmentPool() {
		
		return this.segmentPool;
	}
	
	public void segmentPool(IDSegmentPool segmentPool) {
		
		this.segmentPool = segmentPool;
	}
}