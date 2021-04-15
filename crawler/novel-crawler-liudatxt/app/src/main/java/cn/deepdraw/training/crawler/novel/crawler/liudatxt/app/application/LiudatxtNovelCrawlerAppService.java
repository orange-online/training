package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.application;

import java.util.List;

import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovel;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapter;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContent;

/**
 * LiudatxtNovel Crawler AppService
 * @author huangjiancheng
 * 2020-06-06
 */
public interface LiudatxtNovelCrawlerAppService {

	List<LiudatxtNovel> find(String keywords);

	List<LiudatxtNovelChapter> findChapters(String url);

	LiudatxtNovelChapterContent findChapterContent(String url);

	LiudatxtNovel findNovel(String url);
}