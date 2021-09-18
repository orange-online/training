package cn.deepdraw.training.keygen.gateway.app.infrastructure.facade.dubbo;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.cache.sdk.caffeine.core.CaffeineCacher;
import cn.deepdraw.training.framework.cache.sdk.caffeine.core.CaffeineCacherFactory;
import cn.deepdraw.training.keygen.config.center.api.IDSegmentPoolConfigApi;
import cn.deepdraw.training.keygen.config.center.api.dto.IDSegmentPoolConfigDTO;

/**
 * IDSegmentPoolConfigApi Proxy
 * @author huangjiancheng
 * @Date 2021-06-03
 */
@Component
public class IDSegmentPoolConfigClient {
	
	private CaffeineCacher<String, IDSegmentPoolConfigDTO> cacher = CaffeineCacherFactory.getInstance(new IDSegmentPoolConfigCacheSetting());
	
	@DubboReference
	private IDSegmentPoolConfigApi poolConfigApi;
	
	public IDSegmentPoolConfigDTO getLatestConfig() {
		
		return poolConfigApi.getLatestConfig();
	}
	
	public IDSegmentPoolConfigDTO getCachedLatestConfig() {
		
		IDSegmentPoolConfigDTO poolConfig = cacher.get(IDSegmentPoolConfigCacheSetting.POLICY);
		if (poolConfig == null) {
			
			poolConfig = poolConfigApi.getLatestConfig();
			cacher.put(IDSegmentPoolConfigCacheSetting.POLICY, poolConfig);
		}
		return poolConfig;
	}
}