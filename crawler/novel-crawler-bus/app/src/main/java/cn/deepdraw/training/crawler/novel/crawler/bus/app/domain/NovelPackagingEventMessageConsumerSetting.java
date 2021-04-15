package cn.deepdraw.training.crawler.novel.crawler.bus.app.domain;

import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.rocketmq.core.consumer.RocketmqConsumerSetting;

/**
 * 小说打包事件消息消费方配置
 * @author huangjiancheng
 * @Date 2021-02-22
 */
@Component
public class NovelPackagingEventMessageConsumerSetting implements RocketmqConsumerSetting {

	@Value("${rocketmq.consumer.novel-packaging-event-message.group}")
	private String group;

	@Value("${rocketmq.consumer.novel-packaging-event-message.namesrvAddr}")
	private String namesrvAddr;

	@Value("${rocketmq.consumer.novel-packaging-event-message.topic}")
	private String topic;

	@Value("${rocketmq.consumer.novel-packaging-event-message.expression}")
	private String expression;

	@Autowired
	private NovelPackagingEventMessageListenerConcurrently listener;

	@Override
	public String getGroup() {

		return group;
	}

	@Override
	public String getNamesrvAddr() {

		return namesrvAddr;
	}

	@Override
	public String getTopic() {

		return topic;
	}

	@Override
	public String getExpression() {

		return expression;
	}

	@Override
	public MessageListener getMessageListener() {

		return listener;
	}
}