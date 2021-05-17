package cn.deepdraw.training.novel.crawler.bus.app.domain;

import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.rocketmq.core.consumer.RocketmqConsumerSetting;

/**
 * 小说章节爬取事件消息消费方配置
 * @author huangjiancheng
 * 2020-07-27
 */
@Component
public class NovelChapterCrawlingEventMessageConsumerSetting implements RocketmqConsumerSetting {

	@Value("${rocketmq.consumer.novel-chapter-crawling-event-message.group}")
	private String group;

	@Value("${rocketmq.consumer.novel-chapter-crawling-event-message.namesrvAddr}")
	private String namesrvAddr;

	@Value("${rocketmq.consumer.novel-chapter-crawling-event-message.topic}")
	private String topic;

	@Value("${rocketmq.consumer.novel-chapter-crawling-event-message.expression}")
	private String expression;

	@Autowired
	private NovelChapterCrawlingEventMessageListenerConcurrently listener;

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