package cn.deepdraw.training.crawler.novel.app.domain.core;

import cn.deepdraw.training.crawler.novel.app.domain.shared.IdEntityRepository;

/**
 * NovelChapterCrawlingEvent Repository
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelChapterCrawlingEventRepository extends IdEntityRepository<NovelChapterCrawlingEvent> {

	long countByNovelAndCompleted(Long novelId, String site, boolean completed);
}