package cn.deepdraw.training.framework.motan.spring.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;

/**
 * Motan心跳关闭监听器
 * @author huangjiancheng
 * 2020-05-21
 */
@Order(1)
@Component
public class MotanHeartbeatOffListener implements ApplicationListener<ContextClosedEvent> {

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {

		MotanSwitcherUtil.setSwitcherValue(MotanConstants.REGISTRY_HEARTBEAT_SWITCHER, false); // 在应用关闭时主动关闭心跳
		System.out.println("motan registry heartbeat off...");
	}
}