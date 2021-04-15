package cn.deepdraw.training.crawler.novel.app.application.core.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.app.application.core.NovelPackagingEventAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
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
	public NovelPackagingEvent create(String novelId, Site site) throws WebAppRuntimeException {

		return eventRepo.create(NovelPackagingEvent.of(eventRepo.generateIdString(), novelId, site));
	}

	@Override
	public NovelPackagingEvent publish(String eventId) throws WebAppRuntimeException {

		NovelPackagingEvent event = Validate.notNull(eventRepo.findByEventId(eventId), "event_id_not_found");
		return eventService.publish(novelRepo.findByNovelId(event.novelId()), event.site());
	}

	@Override
	public NovelPackagingEvent complete(String eventId, String path) throws WebAppRuntimeException {

		return eventService.complete(Validate.notNull(eventRepo.findByEventId(eventId), "event_id_not_found"), path);
	}
}