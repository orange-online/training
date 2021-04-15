package cn.deepdraw.training.crawler.novel.crawler.bus.app.domain;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.api.NovelChapterCrawlingEventApi;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.gateway.NovelCrawlerApiGateway;
import cn.deepdraw.training.crawler.storage.api.ResourceStorageApi;
import cn.deepdraw.training.crawler.storage.api.dto.FileItem;
import cn.deepdraw.training.crawler.storage.api.dto.Resource;
import cn.deepdraw.training.framework.utils.JsonUtils;

/**
 * 小说章节爬取事件消息监听器（支持并发）
 * @author huangjiancheng
 * 2020-07-27
 */
@Component
public class NovelChapterCrawlingEventMessageListenerConcurrently implements MessageListenerConcurrently {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private NovelCrawlerApiGateway crawlerGateway;

	@Autowired
	private ResourceStorageApi resourceStorageApi;
	
	@Autowired
	private NovelChapterCrawlingEventApi eventApi;

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

		return doConsumeMessage(JsonUtils.parse(new String(msgs.get(0).getBody()), NovelChapterCrawlingEventMessage.class));
	}

	private ConsumeConcurrentlyStatus doConsumeMessage(NovelChapterCrawlingEventMessage message) {

		String messageJSONString = message.toString();
		logger.info("message body: " + messageJSONString);
		String chapterContent = crawlChapterContent(message.getSite(), message.getLink());
		if (StringUtils.isBlank(chapterContent)) {

			logger.info("message body: " + messageJSONString + ", chapterContent crawled failed.");
			return ConsumeConcurrentlyStatus.RECONSUME_LATER;
		}
		try {

			Resource resource = resourceStorageApi.store(FileItem.of(message.getChapterId(), prepareResourceStoragePath(message), chapterContent.getBytes()));
			String resourceJSONString = JsonUtils.toJson(resource);
			logger.info("message body: " + messageJSONString + ", resource storage: " + resourceJSONString);
			if (StringUtils.isBlank(resource.getPath())) {
				
				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}
			eventApi.complete(message.getEventId(), resource.getPath());
			logger.info("message body: " + messageJSONString + ", resource storage: " + resourceJSONString + ", completed: success!");
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		} catch (IOException e) {

			logger.error("message body: " + messageJSONString + ", resource stored failed. exception message: " + e.getMessage(), e);
			return ConsumeConcurrentlyStatus.RECONSUME_LATER;
		}
	}

	private String crawlChapterContent(String site, String link) {

		ChapterContentDTO content = crawlerGateway.findChapterContent(site, link);
		return content == null ? null : content.getContent();
	}

	private String prepareResourceStoragePath(NovelChapterCrawlingEventMessage event) {

		return event.getNovelId() + "/" + event.getSite();
	}
}