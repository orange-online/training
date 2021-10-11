package cn.deepdraw.training.keygen.config.center.app.interfaces;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.keygen.config.center.api.IDSegmentPoolConfigApi;
import cn.deepdraw.training.keygen.config.center.api.dto.IDSegmentPoolConfigDTO;
import cn.deepdraw.training.keygen.config.center.app.domain.core.IDSegmentPoolConfig;

/**
 * IDSegmentPoolConfigApi Dubbo Service
 * @author huangjiancheng
 * @Date 2021-10-11
 */
@DubboService
public class IDSegmentPoolConfigApiDubboService implements IDSegmentPoolConfigApi {

	@Autowired
	private IDSegmentPoolConfig poolConf;

	@Override
	public IDSegmentPoolConfigDTO getLatestConfig() {
		
		IDSegmentPoolConfigDTO configDTO = new IDSegmentPoolConfigDTO();
		configDTO.setCapacity(poolConf.capacity());
		return configDTO;
	}
}
