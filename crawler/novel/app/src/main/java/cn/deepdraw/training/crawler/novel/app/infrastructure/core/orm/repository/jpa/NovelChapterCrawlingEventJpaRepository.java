package cn.deepdraw.training.crawler.novel.app.infrastructure.core.orm.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEvent;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEventRepository;
import cn.deepdraw.training.crawler.novel.app.infrastructure.shared.orm.repository.jpa.IdEntityJpaRepository;

/**
 * NovelChapterCrawlingEvent Jpa Repository
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@Repository
public interface NovelChapterCrawlingEventJpaRepository extends NovelChapterCrawlingEventRepository, IdEntityJpaRepository<NovelChapterCrawlingEvent> {

	@Override
	default long countByNovelAndCompleted(Long novelId, String site, boolean completed) {

		return countByNovelAndCompletedAndRemoved(novelId, site, completed, false);
	}

	@Query("select count(event) from NovelChapterCrawlingEvent event where event.novelId = :novelId and event.site = :site and event.completed = :completed and event.removed = :removed")
	public long countByNovelAndCompletedAndRemoved(@Param("novelId") Long novelId, @Param("site") String site, @Param("completed") boolean completed, @Param("removed") boolean removed);
}