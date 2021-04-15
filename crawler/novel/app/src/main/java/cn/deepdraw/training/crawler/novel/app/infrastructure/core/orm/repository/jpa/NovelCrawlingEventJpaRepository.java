package cn.deepdraw.training.crawler.novel.app.infrastructure.core.orm.repository.jpa;

import org.springframework.stereotype.Repository;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEvent;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEventRepository;
import cn.deepdraw.training.crawler.novel.app.infrastructure.shared.orm.repository.jpa.IdEntityJpaRepository;

/**
 * NovelCrawlingEvent Jpa Repository
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@Repository
public interface NovelCrawlingEventJpaRepository extends NovelCrawlingEventRepository, IdEntityJpaRepository<NovelCrawlingEvent> {

	@Override
	default NovelCrawlingEvent findByEventId(String eventId) {

		return findByEventIdAndRemoved(eventId, false);
	}

	public NovelCrawlingEvent findByEventIdAndRemoved(String eventId, boolean removed);

	@Override
	default NovelCrawlingEvent findByNovelIdAndSite(String novelId, Site site) {

		return findByNovelIdAndSiteAndRemoved(novelId, site, false);
	}

	public NovelCrawlingEvent findByNovelIdAndSiteAndRemoved(String novelId, Site site, boolean removed);
}