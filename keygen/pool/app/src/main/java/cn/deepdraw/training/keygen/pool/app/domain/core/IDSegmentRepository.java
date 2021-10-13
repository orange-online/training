package cn.deepdraw.training.keygen.pool.app.domain.core;

import java.util.List;

import cn.deepdraw.training.keygen.pool.app.domain.shared.IdEntityRepository;

/**
 * IDSegment Repository
 * @author huangjiancheng
 * @Date 2021-10-13
 */
public interface IDSegmentRepository extends IdEntityRepository<IDSegment> {
	
	/**
	 * Query all IDSegments in the DB that contain the startInclusive.
	 * @param startInclusive
	 * @return
	 */
	List<IDSegment> findByStartInclusive(long startInclusive);
	
	List<IDSegment> findByConsumed(boolean consumed);
}