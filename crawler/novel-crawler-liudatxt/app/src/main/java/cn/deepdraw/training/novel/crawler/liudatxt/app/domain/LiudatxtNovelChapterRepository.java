package cn.deepdraw.training.novel.crawler.liudatxt.app.domain;

import java.util.List;

/**
 * LiudatxtNovelChapter Repository
 * @author huangjiancheng
 * 2020-06-07
 */
public interface LiudatxtNovelChapterRepository {

	List<LiudatxtNovelChapter> findChapters(String url);
}