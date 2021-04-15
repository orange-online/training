package cn.deepdraw.training.framework.rocketmq.core.producer;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.deepdraw.training.framework.rocketmq.core.setting.RocketmqSetting;

/**
 * Rocketmq Topic设置接口
 * @author huangjiancheng
 * 2020-07-24
 */
public interface RocketmqProducerSetting extends RocketmqSetting {
	
	static final int SEND_MSG_TIMEOUT = 10 * 1000;

	String getTopic();

	default String getTag() {

		return StringUtils.EMPTY;
	};

	default List<String> getKeys() {

		return Collections.emptyList();
	}
	
	default int getSendMsgTimeout() {
		
		return SEND_MSG_TIMEOUT;
	}
}