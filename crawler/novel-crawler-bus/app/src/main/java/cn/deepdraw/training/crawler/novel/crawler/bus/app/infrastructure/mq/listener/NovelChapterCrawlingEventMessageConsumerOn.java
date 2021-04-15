package cn.deepdraw.training.crawler.novel.crawler.bus.app.infrastructure.mq.listener;

import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.bus.app.domain.NovelChapterCrawlingEventMessageConsumerSetting;
import cn.deepdraw.training.crawler.novel.crawler.bus.app.domain.NovelCrawlingEventMessageConsumerSetting;
import cn.deepdraw.training.crawler.novel.crawler.bus.app.domain.NovelPackagingEventMessageConsumerSetting;
import cn.deepdraw.training.framework.rocketmq.core.consumer.RocketmqConsumer;

/**
 * 小说章节爬取事件消息消费者装载
 * @author huangjiancheng
 * 2020-05-21
 */
@Order(2)
@Component
public class NovelChapterCrawlingEventMessageConsumerOn implements ApplicationListener<ContextRefreshedEvent> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private NovelCrawlingEventMessageConsumerSetting setting;

	@Autowired
	private NovelChapterCrawlingEventMessageConsumerSetting chapterSetting;
	
	@Autowired
	private NovelPackagingEventMessageConsumerSetting packagingSetting;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		try {

			RocketmqConsumer.getInstance(setting);
			RocketmqConsumer.getInstance(chapterSetting);
			RocketmqConsumer.getInstance(packagingSetting);
			logger.info("rocketmq consumer instance initialized successful.");
		} catch (MQClientException e) {

			logger.error("rocketmq consumer instance initialized failed.", e);
		}
	}
}