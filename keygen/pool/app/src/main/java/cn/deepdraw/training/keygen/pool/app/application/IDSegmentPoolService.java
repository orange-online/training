package cn.deepdraw.training.keygen.pool.app.application;

import cn.deepdraw.training.keygen.pool.app.domain.core.IDSegment;

/**
 * IDSegmentPool Service
 * @author huangjiancheng
 * @Date 2021-10-13
 */
public interface IDSegmentPoolService {

	public IDSegment getIDSegment();

	public IDSegment provide(long startInclusive, long endExclusive);
}