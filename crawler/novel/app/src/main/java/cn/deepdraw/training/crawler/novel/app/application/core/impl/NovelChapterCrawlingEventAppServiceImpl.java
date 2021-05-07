package cn.deepdraw.training.crawler.novel.app.application.core.impl;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.app.application.core.NovelChapterCrawlingEventAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
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
	public NovelChapterCrawlingEvent create(Long novelId, String site, Long version, Long chapterId, String link) throws WebAppRuntimeException {

		return eventRepo.create(NovelChapterCrawlingEvent.of(novelId, site, version, chapterId, link));
	}

	@Override
	public NovelChapterCrawlingEvent publish(Long novelId, String name, LinkAddr addr, Integer index) throws WebAppRuntimeException {

		NovelChapter chapter = chapterRepo.findByChapterLink(novelId, addr.site(), addr.version(), addr.link());
		if (chapter == null) {

			chapter = chapterRepo.create(NovelChapter.of(novelRepo.findByEntityId(novelId), name, addr, index));
		}
		return eventService.publish(create(novelId, chapter.site(), addr.version(), chapter.entityId(), chapter.link()));
	}

	@Override
	public NovelChapterCrawlingEvent publish(Long eventId) throws WebAppRuntimeException {

		return eventService.publish(Validate.notNull(eventRepo.findByEntityId(eventId), "event_id_not_found"));
	}

	@Override
	public NovelChapterCrawlingEvent complete(Long eventId, String path) throws WebAppRuntimeException {

		NovelChapterCrawlingEvent event = eventService.complete(Validate.notNull(eventRepo.findByEntityId(eventId), "event_id_not_found"), path);
		if (shouldPublishNovelPackagingEvent(event.novelId(), event.site(), event.version())) {

			packagingEventService.publish(novelRepo.findByEntityId(event.novelId()), event.site(), event.version());
		}
		return event;
	}

	private boolean shouldPublishNovelPackagingEvent(Long novelId, String site, Long version) {

		return eventRepo.countByNovelAndCompleted(novelId, site, version, false) == NumberUtils.LONG_ZERO;
	}
}