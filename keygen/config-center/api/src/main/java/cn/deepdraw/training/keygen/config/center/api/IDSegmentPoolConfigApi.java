package cn.deepdraw.training.keygen.config.center.api;

import cn.deepdraw.training.keygen.config.center.api.dto.IDSegmentPoolConfig;

/**
 * SegmentPool Config Api
 * @author huangjiancheng
 * @Date 2021-06-03
 */
public interface IDSegmentPoolConfigApi {
	
	IDSegmentPoolConfig getLatestConfig();
}