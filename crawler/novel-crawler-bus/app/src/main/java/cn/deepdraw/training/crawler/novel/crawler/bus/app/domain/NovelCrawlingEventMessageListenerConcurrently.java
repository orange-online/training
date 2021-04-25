package cn.deepdraw.training.crawler.novel.crawler.bus.app.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.api.NovelChapterCrawlingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterCrawlingEventDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.gateway.NovelCrawlerApiGateway;
import cn.deepdraw.training.framework.utils.JsonUtils;

/**
 * 小说爬取事件消息监听器（支持并发）
 * @author huangjiancheng
 * 2020-07-27
 */
@Component
public class NovelCrawlingEventMessageListenerConcurrently implements MessageListenerConcurrently {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@DubboReference
	private NovelCrawlerApiGateway crawlerGateway;
	
	@DubboReference
	private NovelChapterCrawlingEventApi eventApi;

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

		return doConsumeMessage(JsonUtils.parse(new String(msgs.get(0).getBody()), NovelCrawlingEventMessage.class));
	}

	private ConsumeConcurrentlyStatus doConsumeMessage(NovelCrawlingEventMessage em) {

		String messageJSONString = em.toString();
		logger.info("message body: " + messageJSONString);
		if (em.getEventId() == null) {
			
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
		List<ChapterDTO> chaptersOnline = crawlChapters(em.getSite(), em.getLink());
		List<NovelChapter> chapters = new ArrayList<>(chaptersOnline.size());
		for (int index = 0, len = chaptersOnline.size(); index < len; index++) {
			
			ChapterDTO chapter = chaptersOnline.get(index);
			chapters.add(NovelChapter.of(em.getNovelId(), chapter.getName(), em.getSite(), chapter.getUrl(), index + 1));
		}
		chapters.parallelStream().forEach(chapter -> {
			
			String chapterJSONString = JsonUtils.toJson(chapter);
			logger.info("message body: " + messageJSONString + ", chapter: " + chapterJSONString);
			try {
				
				NovelChapterCrawlingEventDTO event = eventApi.publish(chapter.getNovelId(), chapter.getName(), chapter.address(), chapter.getIndex());
				logger.info("message body: " + messageJSONString + ", chapter: " + chapterJSONString + ", event content: " + JsonUtils.toJson(event));
			} catch (Exception e) {
				
				logger.error("message body: " + messageJSONString + ", chapter: " + chapterJSONString + ", published failed. exception message: " + e.getMessage(), e);
				// TODO need to send an exception queue message here. by huangjiancheng 20201129
			}
		});
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
	
	public List<ChapterDTO> crawlChapters(String site, String link) {
		
		return crawlerGateway.findChapters(site, link);
	}
	
	public static class NovelChapter implements Serializable{

		private static final long serialVersionUID = Long.MAX_VALUE;
		
		private Long novelId;
		
		private String name;
		
		private String site;
		
		private String link;
		
		private Integer index;
		
		public NovelChapter() {}
		
		public NovelChapter(Long novelId, String name, String site, String link, Integer index) {
			
			this.novelId = novelId;
			this.name = name;
			this.site = site;
			this.link = link;
			this.index = index;
		}
		
		public static NovelChapter of(Long novelId, String name, String site, String link, Integer index) {
			
			return new NovelChapter(novelId, name, site, link, index);
		}

		public Long getNovelId() {
			return novelId;
		}

		public void setNovelId(Long novelId) {
			this.novelId = novelId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSite() {
			return site;
		}

		public void setSite(String site) {
			this.site = site;
		}

		public String getLink() {
			return link;
		}

		public void setLink(String link) {
			this.link = link;
		}

		public Integer getIndex() {
			return index;
		}

		public void setIndex(Integer index) {
			this.index = index;
		}

		public LinkAddress address() {
			
			return LinkAddress.of(site, link);
		}
	}
}