package cn.deepdraw.training.crawler.novel.app.application.core.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.app.application.core.NovelPackagingEventAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEvent;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEventRepository;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEventService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelPackagingEventAppService Impl
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@Service
@Transactional
public class NovelPackagingEventAppServiceImpl implements NovelPackagingEventAppService {
	
	@Autowired
	private NovelRepository novelRepo;
	
	@Autowired
	private NovelPackagingEventRepository eventRepo;
	
	@Autowired
	private NovelPackagingEventService eventService;

	@Override
	public NovelPackagingEvent create(Long novelId, String site, Long version) throws WebAppRuntimeException {

		return eventRepo.create(NovelPackagingEvent.of(novelId, site, version));
	}

	@Override
	public NovelPackagingEvent publish(Long eventId) throws WebAppRuntimeException {

		NovelPackagingEvent event = Validate.notNull(eventRepo.findByEntityId(eventId), "event_id_not_found");
		return eventService.publish(novelRepo.findByEntityId(event.novelId()), event.site(), event.version());
	}

	@Override
	public NovelPackagingEvent complete(Long eventId, String path) throws WebAppRuntimeException {

		return eventService.complete(Validate.notNull(eventRepo.findByEntityId(eventId), "event_id_not_found"), path);
	}
}