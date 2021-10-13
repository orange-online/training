package cn.deepdraw.training.keygen.pool.app.infrastructure.core.orm.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.deepdraw.training.keygen.pool.app.domain.core.IDSegment;
import cn.deepdraw.training.keygen.pool.app.domain.core.IDSegmentRepository;
import cn.deepdraw.training.keygen.pool.app.infrastructure.shared.orm.repository.jpa.IdEntityJpaRepository;

/**
 * IDSegment Jpa Repository
 * @author huangjiancheng
 * 2020-06-19
 */
@Repository
public interface IDSegmentJpaRepository extends IDSegmentRepository, IdEntityJpaRepository<IDSegment> {
	
	@Override
	default List<IDSegment> findByConsumed(boolean consumed) {

		return findByConsumed(consumed, false);
	}
	
	@Query("select segment from IDSegment segment where segment.consumed = :consumed and segment.removed = :removed")
	List<IDSegment> findByConsumed(@Param("consumed") boolean consumed, @Param("removed") boolean removed);
	
	@Override
	default List<IDSegment> findByStartInclusive(long startInclusive) {

		return findByStartInclusive(startInclusive, startInclusive, false);
	}
	
	@Query("select segment from IDSegment segment where segment.startInclusive <= :startInclusive and segment.endExclusive > :endExclusive and segment.removed = :removed")
	List<IDSegment> findByStartInclusive(@Param("startInclusive") long startInclusive, @Param("endExclusive") long endExclusive, @Param("removed") boolean removed);
}