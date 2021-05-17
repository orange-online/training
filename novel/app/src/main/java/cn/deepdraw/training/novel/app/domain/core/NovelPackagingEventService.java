package cn.deepdraw.training.novel.app.domain.core;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.novel.app.infrastructure.mq.NovelPackagingRocketmqProducerSetting;
import cn.deepdraw.training.novel.app.infrastructure.mq.RocketmqMessagerProxy;

/**
 * @author xujianing
 * @date 2020/12/23
 * Novel Packaging Event Service
 */
@Component
public class NovelPackagingEventService {

	@Autowired
	private NovelCrawlingEventRepository eventRepo;

	@Autowired
	private NovelCrawlingEventService eventService;

	@Autowired
	private NovelPackagingEventRepository packagingEventRepo;

	@Autowired
	private NovelPackagingRocketmqProducerSetting setting;

	@Autowired
	private RocketmqMessagerProxy messageProxy;

	public NovelPackagingEvent publish(Novel novel, String site, Long version) {

		Validate.notNull(novel.addrOf(site, version), "unsupported_site");
		NovelPackagingEvent event = packagingEventRepo.create(NovelPackagingEvent.of(novel.entityId(), site, version));
		return messageProxy.send(setting, event) ? packagingEventRepo.update(event.publish()) : event;
	}

	public NovelPackagingEvent complete(NovelPackagingEvent event, String path) {

		eventService.complete(eventRepo.findByNovelId(event.novelId(), event.site(), event.version()), path);
		return packagingEventRepo.update(event.complete());
	}
}