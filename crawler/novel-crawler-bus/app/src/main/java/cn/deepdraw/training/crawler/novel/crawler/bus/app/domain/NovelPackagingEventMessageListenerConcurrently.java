package cn.deepdraw.training.crawler.novel.crawler.bus.app.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterDTO;
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

	private static final String CHAPTER_OUTER_SEPARATOR = "\r\n";

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

	private ConsumeConcurrentlyStatus doConsumeMessage(NovelPackagingEventMessage em) {

		String messageJSONString = em.toString();
		logger.info("message body: " + messageJSONString);
		if (em.getEventId() == null) {

			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
		try {

			String name = prepareResourceStorageName(em);
			String path = prepareResourceStoragePath(em);
			byte[] data = prepareResourceStorageData(chapterApi.findByNovelId(em.getNovelId(), em.getSite(), em.getVersion()));
			Resource resource = resourceStorageApi.store(FileItem.of(name, path, data));
			if (StringUtils.isBlank(resource.getPath())) {

				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}
			String resourceJSONString = JsonUtils.toJson(resource);
			eventApi.complete(em.getEventId(), resource.getPath());
			logger.info("message body: " + messageJSONString + ", resource storage: " + resourceJSONString + ", completed: success!");
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		} catch (Exception e) {

			logger.error("message body: " + messageJSONString + ", completed failed. exception message: " + e.getMessage(), e);
			return ConsumeConcurrentlyStatus.RECONSUME_LATER;
		}
	}

	private Map<String, String> findPath2DataMap(List<String> paths) {

		return resourceStorageApi
				.download(paths)
				.stream()
				.filter(resource -> resource.getData() != null)
				.collect(Collectors.toMap(resource -> resource.getPath(), resource -> resource.getData() != null ? new String(resource.getData()) : null));
	}
	
	private byte[] doPrepareResourceStorageData(List<NovelChapterContent> contents) {
		
		Collections.sort(contents, (c1, c2) -> c1.getIndex() - c2.getIndex());
		return StringUtils.join(contents, CHAPTER_OUTER_SEPARATOR).getBytes();
	}
	
	private byte[] prepareResourceStorageData(List<NovelChapterDTO> chapters) {

		Map<String, String> path2ContentMap = findPath2DataMap(chapters.parallelStream().map(chapter -> chapter.getAddress().getPath()).collect(Collectors.toList()));
		return doPrepareResourceStorageData(chapters
				.parallelStream()
				.map(chapter -> NovelChapterContent.of(chapter, path2ContentMap.get(chapter.getAddress().getPath())))
				.filter(content -> StringUtils.isNotBlank(content.getContent()))
				.collect(Collectors.toList()));
	}

	private String prepareResourceStorageName(NovelPackagingEventMessage message) {

		return message.getNovelId() + "_" + message.getSite() + "_" + message.getVersion();
	}

	private String prepareResourceStoragePath(NovelPackagingEventMessage message) {

		return message.getNovelId().toString();
	}
	
	public static class NovelChapterContent implements Serializable {

		private static final long serialVersionUID = Long.MAX_VALUE;

		private static final String CHAPTER_INNER_SEPARATOR = "\r\n";
		
		private String title;
		
		private String content;
		
		private Integer index;
		
		public NovelChapterContent() {}
		
		public NovelChapterContent(String title, String content, Integer index) {
			
			this.title = title;
			this.content = content;
			this.index = index;
		}
		
		public static NovelChapterContent of(String title, String content, Integer index) {
			
			return new NovelChapterContent(title, content, index);
		}
		
		public static NovelChapterContent of(NovelChapterDTO chapter, String content) {
			
			return of(chapter.getName(), content, chapter.getIndex());
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Integer getIndex() {
			return index;
		}

		public void setIndex(Integer index) {
			this.index = index;
		}
		
		@Override
		public String toString() {
			
			return title + CHAPTER_INNER_SEPARATOR + content;
		}
	}
}