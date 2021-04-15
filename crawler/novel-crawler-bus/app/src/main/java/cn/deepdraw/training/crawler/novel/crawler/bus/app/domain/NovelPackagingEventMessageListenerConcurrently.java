package cn.deepdraw.training.crawler.novel.crawler.bus.app.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.api.NovelChapterApi;
import cn.deepdraw.training.crawler.novel.api.NovelPackagingEventApi;
import cn.deepdraw.training.crawler.storage.api.ResourceStorageApi;
import cn.deepdraw.training.crawler.storage.api.dto.FileItem;
import cn.deepdraw.training.crawler.storage.api.dto.Resource;
import cn.deepdraw.training.framework.utils.JsonUtils;

/**
 * 小说打包事件消息监听器（支持并发）
 * @author huangjiancheng
 * @Date 2021-02-22
 */
@Component
public class NovelPackagingEventMessageListenerConcurrently implements MessageListenerConcurrently {

	private static final String CHAPTER_CONTENT_SEPARATOR = "\n\r";

	private Logger logger = LoggerFactory.getLogger(getClass());

	@DubboReference
	private NovelChapterApi chapterApi;

	@DubboReference
	private NovelPackagingEventApi eventApi;

	@DubboReference
	private ResourceStorageApi resourceStorageApi;

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

		return doConsumeMessage(JsonUtils.parse(new String(msgs.get(0).getBody()), NovelPackagingEventMessage.class));
	}

	private ConsumeConcurrentlyStatus doConsumeMessage(NovelPackagingEventMessage message) {

		String messageJSONString = message.toString();
		logger.info("message body: " + messageJSONString);
		if (StringUtils.isBlank(message.getEventId())) {

			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
		try {

			Resource resource = resourceStorageApi.store(FileItem.of(prepareResourceStorageName(message), prepareResourceStoragePath(message), prepareResourceStorageData(message)));
			if (StringUtils.isBlank(resource.getPath())) {

				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}
			String resourceJSONString = JsonUtils.toJson(resource);
			eventApi.complete(message.getEventId(), resource.getPath());
			logger.info("message body: " + messageJSONString + ", resource storage: " + resourceJSONString + ", completed: success!");
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		} catch (Exception e) {

			logger.error("message body: " + messageJSONString + ", completed failed. exception message: " + e.getMessage(), e);
			return ConsumeConcurrentlyStatus.RECONSUME_LATER;
		}
	}

	private List<String> findChapterContentPaths(String novelId, String site) {

		return chapterApi.findByNovelId(novelId, site).stream().map(chapter -> chapter.getAddress().getPath()).collect(Collectors.toList());
	}

	private List<String> findChapterContents(List<String> paths) {

		return resourceStorageApi.download(paths).stream().filter(resource -> resource.getData() != null).map(resource -> resource.getData().toString()).collect(Collectors.toList());
	}

	private byte[] prepareResourceStorageData(NovelPackagingEventMessage message) {

		List<String> chapterContentPaths = findChapterContentPaths(message.getNovelId(), message.getSite());
		List<String> chapterContents = findChapterContents(chapterContentPaths);
		return StringUtils.join(chapterContents, CHAPTER_CONTENT_SEPARATOR).getBytes();
	}

	private String prepareResourceStorageName(NovelPackagingEventMessage message) {

		return message.getNovelId() + "_" + message.getSite();
	}

	private String prepareResourceStoragePath(NovelPackagingEventMessage message) {

		return message.getNovelId();
	}
}