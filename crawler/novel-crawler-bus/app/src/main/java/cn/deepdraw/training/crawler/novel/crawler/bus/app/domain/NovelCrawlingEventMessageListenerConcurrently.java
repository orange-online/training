package cn.deepdraw.training.crawler.novel.crawler.bus.app.domain;

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

	private ConsumeConcurrentlyStatus doConsumeMessage(NovelCrawlingEventMessage message) {

		String messageJSONString = message.toString();
		logger.info("message body: " + messageJSONString);
		if (message.getEventId() == null) {
			
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		}
		crawlChapters(message.getSite(), message.getLink()).parallelStream().forEach(chapterOnline -> {
			
			String chapterJSONString = JsonUtils.toJson(chapterOnline);
			logger.info("message body: " + messageJSONString + ", online chapter: " + chapterJSONString);
			try {
				
				NovelChapterCrawlingEventDTO event = eventApi.publish(message.getNovelId(), chapterOnline.getName(), LinkAddress.of(message.getSite(), chapterOnline.getUrl()));
				logger.info("message body: " + messageJSONString + ", online chapter: " + chapterJSONString + ", event content: " + JsonUtils.toJson(event));
			} catch (Exception e) {
				
				logger.error("message body: " + messageJSONString + ", online chapter: " + chapterJSONString + ", published failed. exception message: " + e.getMessage(), e);
				// TODO need to send an exception queue message here. by huangjiancheng 20201129
			}
		});
		return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
	}
	
	public List<ChapterDTO> crawlChapters(String site, String link) {
		
		return crawlerGateway.findChapters(site, link);
	}
}