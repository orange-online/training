package cn.deepdraw.training.crawler.novel.app.domain.core;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.shared.IdEntityRepository;

/**
 * NovelChapterCrawlingEvent Repository
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelChapterCrawlingEventRepository extends IdEntityRepository<NovelChapterCrawlingEvent> {

	NovelChapterCrawlingEvent findByEventId(String eventId);

	long countByNovelAndCompleted(String novelId, Site site, boolean completed);
}