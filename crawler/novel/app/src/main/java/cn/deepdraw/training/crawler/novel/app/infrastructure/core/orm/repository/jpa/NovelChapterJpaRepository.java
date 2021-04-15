package cn.deepdraw.training.crawler.novel.app.infrastructure.core.orm.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapter;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterRepository;
import cn.deepdraw.training.crawler.novel.app.infrastructure.shared.orm.repository.jpa.IdEntityJpaRepository;

/**
 * NovelChapter Jpa Repository
 * @author huangjiancheng
 * 2020-06-19
 */
@Repository
public interface NovelChapterJpaRepository extends NovelChapterRepository, IdEntityJpaRepository<NovelChapter> {

	@Override
	default NovelChapter findByChapterId(String novelId, String chapterId) {

		return findByChapterIdAndRemoved(novelId, chapterId, false);
	}

	@Query("select chapter from NovelChapter chapter where chapter.novel.novelId = :novelId and chapter.chapterId = :chapterId and chapter.removed = :removed")
	NovelChapter findByChapterIdAndRemoved(@Param("novelId") String novelId, @Param("chapterId") String chapterId, @Param("removed") boolean removed);

	@Override
	default List<NovelChapter> findByNovelId(String novelId, Site site) {

		return findByNovelIdAndRemoved(novelId, site, false);
	}

	@Query("select chapter from NovelChapter chapter where chapter.novel.novelId = :novelId and chapter.addr.site = :site and chapter.removed = :removed")
	List<NovelChapter> findByNovelIdAndRemoved(@Param("novelId") String novelId, @Param("site") Site site, @Param("removed") boolean removed);

	@Override
	default NovelChapter findByChapterLink(String novelId, Site site, String link) {

		return findByChapterLinkAndRemoved(novelId, site, link, false);
	}

	@Query("select chapter from NovelChapter chapter where chapter.novel.novelId = :novelId and chapter.addr.site = :site and chapter.addr.link = :link and chapter.removed = :removed")
	public NovelChapter findByChapterLinkAndRemoved(@Param("novelId") String novelId, @Param("site") Site site, @Param("link") String link, @Param("removed") boolean removed);
}