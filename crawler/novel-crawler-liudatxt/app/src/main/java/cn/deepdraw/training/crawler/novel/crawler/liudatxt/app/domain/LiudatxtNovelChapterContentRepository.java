package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain;

/**
 * LiudatxtNovelChapterContent Repository
 * @author huangjiancheng
 * 2020-06-07
 */
public interface LiudatxtNovelChapterContentRepository {

	LiudatxtNovelChapterContent findChapterContent(String url);
}