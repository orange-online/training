package cn.deepdraw.training.crawler.novel.app.application.core.impl;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.app.application.core.NovelChapterCrawlingEventAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapter;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEvent;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEventRepository;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEventService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterRepository;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEventService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelChapterCrawlingEventAppService Impl
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@Service
@Transactional
public class NovelChapterCrawlingEventAppServiceImpl implements NovelChapterCrawlingEventAppService {

	@Autowired
	private NovelChapterCrawlingEventRepository eventRepo;
	
	@Autowired
	private NovelRepository novelRepo;
	
	@Autowired
	private NovelChapterRepository chapterRepo;
	
	@Autowired
	private NovelChapterCrawlingEventService eventService;
	
	@Autowired
	private NovelPackagingEventService packagingEventService;

	@Override
	public NovelChapterCrawlingEvent create(String novelId, Site site, String chapterId, String link) throws WebAppRuntimeException {

		return eventRepo.create(NovelChapterCrawlingEvent.of(eventRepo.generateIdString(), novelId, site, chapterId, link));
	}

	@Override
	public NovelChapterCrawlingEvent publish(String novelId, String name, LinkAddr addr) throws WebAppRuntimeException {

		NovelChapter chapter = chapterRepo.findByChapterLink(novelId, addr.site(), addr.link());
		if (chapter == null) {

			chapter = chapterRepo.create(NovelChapter.of(novelRepo.findByNovelId(novelId), chapterRepo.generateIdString(), name, addr));
		}
		return eventService.publish(create(novelId, chapter.site(), chapter.chapterId(), chapter.link()));
	}

	@Override
	public NovelChapterCrawlingEvent publish(String eventId) throws WebAppRuntimeException {

		return eventService.publish(Validate.notNull(eventRepo.findByEventId(eventId), "event_id_not_found"));
	}

	@Override
	public NovelChapterCrawlingEvent complete(String eventId, String path) throws WebAppRuntimeException {

		NovelChapterCrawlingEvent event = eventService.complete(Validate.notNull(eventRepo.findByEventId(eventId), "event_id_not_found"), path);
		if (shouldPublishNovelPackagingEvent(event.novelId(), event.site())) {

			packagingEventService.publish(novelRepo.findByNovelId(event.novelId()), event.site());
		}
		return event;
	}

	private boolean shouldPublishNovelPackagingEvent(String novelId, Site site) {

		return eventRepo.countByNovelAndCompleted(novelId, site, false) == NumberUtils.LONG_ZERO;
	}
}