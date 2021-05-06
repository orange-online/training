package cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.application;

import java.util.List;

import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapter;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangNovel;

/**
 * 职责: 选书网爬虫
 * @author：杨攀
 * @date：2020年6月22日 下午8:40:39
 */
public interface XuanshuwangNovelCrawlerAppService {

    List<XuanshuwangNovel> findByKeywords(String keywords);

    List<XuanshuwangChapter> findChapters(String url);

    XuanshuwangChapterContent findChapterContent(String chapterUrl);

	XuanshuwangNovel findNovel(String url);
}