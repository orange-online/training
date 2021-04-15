package cn.deepdraw.training.crawler.novel.app.domain.core;

import java.util.List;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.shared.IdEntityRepository;

/**
 * NovelChapter Repository
 * @author huangjiancheng
 * 2020-07-22
 */
public interface NovelChapterRepository extends IdEntityRepository<NovelChapter> {

	NovelChapter findByChapterId(String novelId, String chapterId);

	NovelChapter findByChapterLink(String novelId, Site site, String link);

	List<NovelChapter> findByNovelId(String novelId, Site site);
}