package cn.deepdraw.training.crawler.novel.app.domain.core;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.app.infrastructure.mq.NovelRocketmqProducerSetting;
import cn.deepdraw.training.crawler.novel.app.infrastructure.mq.RocketmqMessagerProxy;

/**
 * NovelCrawlingEvent Service
 * @author huangjiancheng
 * @Date 2020-11-27
 */
@Component
public class NovelCrawlingEventService {

	@Autowired
	private NovelRepository novelRepo;

	@Autowired
	private NovelCrawlingEventRepository eventRepo;

	@Autowired
	private NovelRocketmqProducerSetting setting;

	@Autowired
	private RocketmqMessagerProxy messageProxy;

	public NovelCrawlingEvent publish(Novel novel, String site, Long version) {

		LinkAddr addrOf = Validate.notNull(novel.addrOf(site, version), "unsupported_site");
		NovelCrawlingEvent event = eventRepo.create(NovelCrawlingEvent.of(novel.entityId(), site, version, addrOf.link()));
		return messageProxy.send(setting, event) ? eventRepo.update(event.publish()) : event;
	}

	public NovelCrawlingEvent complete(NovelCrawlingEvent event, String path) {

		novelRepo.update(novelRepo.findByEntityId(event.novelId()).updateAddrPath(event.site(), event.version(), path));
		return eventRepo.update(event.complete());
	}
}