package cn.deepdraw.training.crawler.novel.crawler.bus.app.infrastructure.mq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.rocketmq.core.consumer.RocketmqConsumer;

/**
 * 小说章节爬取事件消息消费者卸载
 * @author huangjiancheng
 * 2020-05-21
 */
@Order(2)
@Component
public class NovelChapterCrawlingEventMessageConsumerOff implements ApplicationListener<ContextClosedEvent> {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {

		RocketmqConsumer.shutdownAll();
		logger.info("rocketmq consumer instances shutdown successful.");
	}
}