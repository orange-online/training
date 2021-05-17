package cn.deepdraw.training.novel.app.infrastructure.core.orm.repository.jpa;

import org.springframework.stereotype.Repository;

import cn.deepdraw.training.novel.app.domain.core.NovelPackagingEvent;
import cn.deepdraw.training.novel.app.domain.core.NovelPackagingEventRepository;
import cn.deepdraw.training.novel.app.infrastructure.shared.orm.repository.jpa.IdEntityJpaRepository;

/**
 * @author xujianing
 * @date 2020/12/23
 * Novel Packaging Event JpaRepository
 */
@Repository
public interface NovelPackagingEventJpaRepository extends NovelPackagingEventRepository, IdEntityJpaRepository<NovelPackagingEvent> {
}
