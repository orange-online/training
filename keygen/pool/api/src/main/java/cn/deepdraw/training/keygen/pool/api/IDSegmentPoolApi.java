package cn.deepdraw.training.keygen.pool.api;

import cn.deepdraw.training.keygen.pool.api.dto.IDSegmentDTO;

/**
 * IDSegment Pool Api
 * @author huangjiancheng
 * @Date 2021-07-02
 */
public interface IDSegmentPoolApi {
	
	IDSegmentDTO getIDSegment();
	
	boolean provide(IDSegmentDTO segment);
}