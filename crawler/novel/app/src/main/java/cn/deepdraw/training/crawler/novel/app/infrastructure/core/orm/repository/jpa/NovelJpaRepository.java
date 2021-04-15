package cn.deepdraw.training.crawler.novel.app.infrastructure.core.orm.repository.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.crawler.novel.app.infrastructure.shared.orm.repository.jpa.IdEntityJpaRepository;

/**
 * Novel Jpa Repository
 * @author huangjiancheng
 * 2020-06-19
 */
@Repository
public interface NovelJpaRepository extends NovelRepository, IdEntityJpaRepository<Novel> {

	@Override
	default Novel findByNovelId(String novelId) {

		return findByNovelIdAndRemoved(novelId, false);
	}

	Novel findByNovelIdAndRemoved(String novelId, boolean removed);

	@Override
	default Novel findByUnique(String name, String author) {

		return findByUnique(name, author, false);
	}

	@Query("select novel from Novel novel where novel.name = :name and novel.author = :author and novel.removed = :removed")
	Novel findByUnique(@Param("name") String name, @Param("author") String author, @Param("removed") boolean removed);
}