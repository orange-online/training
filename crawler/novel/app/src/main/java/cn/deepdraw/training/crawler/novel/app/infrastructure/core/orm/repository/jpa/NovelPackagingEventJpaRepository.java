package cn.deepdraw.training.crawler.novel.app.infrastructure.core.orm.repository.jpa;

import org.springframework.stereotype.Repository;

import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEvent;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEventRepository;
import cn.deepdraw.training.crawler.novel.app.infrastructure.shared.orm.repository.jpa.IdEntityJpaRepository;

/**
 * @author xujianing
 * @date 2020/12/23
 * Novel Packaging Event JpaRepository
 */
@Repository
public interface NovelPackagingEventJpaRepository extends NovelPackagingEventRepository, IdEntityJpaRepository<NovelPackagingEvent> {

	@Override
	default NovelPackagingEvent findByEventId(String eventId) {

		return findByEventIdAndRemoved(eventId, false);
	}

	NovelPackagingEvent findByEventIdAndRemoved(String eventId, boolean removed);
}
