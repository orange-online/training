package cn.deepdraw.training.crawler.novel.app.application.core.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.app.application.core.NovelCrawlingEventAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEvent;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEventRepository;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEventService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelCrawlingEventAppService Impl
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@Service
@Transactional
public class NovelCrawlingEventAppServiceImpl implements NovelCrawlingEventAppService {
	
	@Autowired
	private NovelRepository novelRepo;
	
	@Autowired
	private NovelCrawlingEventRepository eventRepo;
	
	@Autowired
	private NovelCrawlingEventService eventService;

	@Override
	public NovelCrawlingEvent create(Long novelId, String site, String link) throws WebAppRuntimeException {

		return eventRepo.create(NovelCrawlingEvent.of(novelId, site, link));
	}

	@Override
	public NovelCrawlingEvent publish(Long eventId) throws WebAppRuntimeException {

		NovelCrawlingEvent event = Validate.notNull(eventRepo.findByEntityId(eventId), "event_id_not_found");
		return eventService.publish(novelRepo.findByEntityId(event.novelId()), event.site());
	}

	@Override
	public NovelCrawlingEvent complete(Long eventId, String path) throws WebAppRuntimeException {

		return eventService.complete(Validate.notNull(eventRepo.findByEntityId(eventId), "event_id_not_found"), path);
	}
}