package cn.deepdraw.training.framework.motan.spring.listener;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;

/**
 * 项目启动时，自动开启Motan注册心跳
 * @author huangjiancheng
 * 2020-05-21
 */
@Order(1)
@Component
public class MotanHeartbeatOnRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {

		MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, true); // 在使用注册中心时要主动开启心跳
		System.out.println("motan registry heartbeat on...");
	}
}