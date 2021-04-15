package cn.deepdraw.training.crawler.novel.app.domain.core;

import cn.deepdraw.training.crawler.novel.app.domain.shared.IdEntityRepository;

/**
 * @author xujianing
 * @date 2020/12/23
 * Novel Packaging Event Repository
 */
public interface NovelPackagingEventRepository extends IdEntityRepository<NovelPackagingEvent> {

	NovelPackagingEvent findByEventId(String eventId);
}
