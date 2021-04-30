package cn.deepdraw.training.crawler.novel.app.domain.core;

import java.util.List;

import cn.deepdraw.training.crawler.novel.app.domain.shared.IdEntityRepository;

/**
 * NovelChapter Repository
 * @author huangjiancheng
 * 2020-07-22
 */
public interface NovelChapterRepository extends IdEntityRepository<NovelChapter> {

	NovelChapter findByChapterId(Long novelId, Long chapterId);

	NovelChapter findByChapterLink(Long novelId, String site, String link);

	List<NovelChapter> findByNovelId(Long novelId, String site);
}