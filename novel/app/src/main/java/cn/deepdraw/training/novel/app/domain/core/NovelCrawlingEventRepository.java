package cn.deepdraw.training.novel.app.domain.core;

import cn.deepdraw.training.novel.app.domain.shared.IdEntityRepository;

/**
 * NovelCrawlingEvent Repository
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelCrawlingEventRepository extends IdEntityRepository<NovelCrawlingEvent> {

	NovelCrawlingEvent findByNovelId(Long novelId, String site, Long version);
}