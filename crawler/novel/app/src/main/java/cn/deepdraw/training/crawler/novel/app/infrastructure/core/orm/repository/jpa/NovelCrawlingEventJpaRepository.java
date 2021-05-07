package cn.deepdraw.training.crawler.novel.app.infrastructure.core.orm.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
	default NovelCrawlingEvent findByNovelId(Long novelId, String site, Long version) {

		return findByNovelIdAndRemoved(novelId, site, version, false);
	}

	@Query("select event from NovelCrawlingEvent event where event.novelId = :novelId and event.site = :site and event.version = :version and event.removed = :removed")
	public NovelCrawlingEvent findByNovelIdAndRemoved(@Param("novelId") Long novelId, @Param("site") String site, @Param("version") Long version, @Param("removed") boolean removed);
}