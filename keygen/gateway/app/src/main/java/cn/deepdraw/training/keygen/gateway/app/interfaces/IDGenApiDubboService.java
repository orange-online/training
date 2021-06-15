package cn.deepdraw.training.keygen.gateway.app.interfaces;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.keygen.gateway.api.IDGenApi;
import cn.deepdraw.training.keygen.gateway.app.application.IDGenAppService;

/**
 * IdGenApi DubboService
 * @author huangjiancheng
 * @Date 2021-05-28
 */
@DubboService
public class IDGenApiDubboService implements IDGenApi {
	
	@Autowired
	private IDGenAppService appService;

	@Override
	public Long genId() {
		
		return appService.genId();
	}

	@Override
	public List<Long> genIds(int size) {
		
		return appService.genIds(size);
	}
}