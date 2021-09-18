package cn.deepdraw.training.keygen.gateway.app.infrastructure.facade.dubbo;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.cache.sdk.caffeine.setting.CaffeineCacheSetting;
import cn.deepdraw.training.keygen.config.center.api.dto.IDSegmentPoolConfigDTO;

/**
 * IDSegmentPoolConfig CacheSetting
 * @author huangjiancheng
 * @Date 2021-06-07
 */
@Component
public class IDSegmentPoolConfigCacheSetting implements CaffeineCacheSetting<String, IDSegmentPoolConfigDTO> {
	
	public static final String POLICY = "id_segment_pool_config";

	@Override
	public String policy() {
		
		return POLICY;
	}

	@Override
	public long expireAfterWrite() {
		
		return 60L;
	}

	@Override
	public int initialCapacity() {
		
		return 1;
	}

	@Override
	public long maximumSize() {
		
		return 1;
	}

}