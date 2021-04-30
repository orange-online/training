package cn.deepdraw.training.crawler.novel.app.domain.core;

import cn.deepdraw.training.crawler.novel.app.domain.shared.IdEntityRepository;

/**
 * NovelCrawlingEvent Repository
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelCrawlingEventRepository extends IdEntityRepository<NovelCrawlingEvent> {

	NovelCrawlingEvent findByNovelIdAndSite(Long novelId, String site);
}