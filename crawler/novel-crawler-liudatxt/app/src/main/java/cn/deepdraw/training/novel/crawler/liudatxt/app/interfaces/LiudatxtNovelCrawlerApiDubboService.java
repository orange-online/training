package cn.deepdraw.training.novel.crawler.liudatxt.app.interfaces;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.novel.crawler.liudatxt.api.LiudatxtNovelCrawlerApi;
import cn.deepdraw.training.novel.crawler.liudatxt.app.application.LiudatxtNovelCrawlerAppService;

/**
 * 溜达小说爬虫接口Motan服务
 * @author huangjiancheng
 * 2020-06-06
 */
@DubboService
public class LiudatxtNovelCrawlerApiDubboService implements LiudatxtNovelCrawlerApi {

	@Autowired
	private LiudatxtNovelCrawlerAppService appService;

	@Autowired
	private LiudatxtNovelConverter novelConv;

	@Autowired
	private LiudatxtNovelChapterConverter chapterConv;

	@Autowired
	private LiudatxtNovelChapterContentConverter chapterContentConv;

	@Override
	public List<Novel> find(String keywords) {

		return novelConv.toNovels(appService.find(keywords));
	}

	@Override
	public Novel findNovel(String url) {

		return novelConv.toNovel(appService.findNovel(url));
	}

	@Override
	public List<Chapter> findChapters(String url) {

		return chapterConv.toChapters(appService.findChapters(url));
	}

	@Override
	public ChapterContent findChapterContent(String url) {

		return chapterContentConv.toChapterContent(appService.findChapterContent(url));
	}
}