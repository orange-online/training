package cn.deepdraw.training.novel.app.domain.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.novel.app.infrastructure.mq.NovelChapterRocketmqProducerSetting;
import cn.deepdraw.training.novel.app.infrastructure.mq.RocketmqMessagerProxy;

/**
 * NovelChapterCrawlingEvent Service
 * @author huangjiancheng
 * @Date 2020-11-29
 */
@Component
public class NovelChapterCrawlingEventService {
	
	@Autowired
	private NovelChapterRepository chapterRepo;

	@Autowired
	private NovelChapterCrawlingEventRepository eventRepo;

	@Autowired
	private NovelChapterRocketmqProducerSetting setting;
	
	@Autowired
	private RocketmqMessagerProxy messageProxy;
	
	public NovelChapterCrawlingEvent publish(NovelChapterCrawlingEvent event) {
		
		return messageProxy.send(setting, event) ? eventRepo.update(event.publish()) : event;
	}

	public NovelChapterCrawlingEvent complete(NovelChapterCrawlingEvent event, String path) {

		chapterRepo.update(chapterRepo.findByChapterId(event.chapterId()).updateAddrPath(path));
		return eventRepo.update(event.complete());
		
	}
}