package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.interfaces;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.api.LiudatxtNovelCrawlerApi;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.application.LiudatxtNovelCrawlerAppService;

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
	public List<NovelDTO> find(String keywords) {

		return novelConv.toNovelDTOs(appService.find(keywords));
	}

	@Override
	public NovelDTO findNovel(String url) {

		return novelConv.toNovelDTO(appService.findNovel(url));
	}

	@Override
	public List<ChapterDTO> findChapters(String url) {

		return chapterConv.toChapterDTOs(appService.findChapters(url));
	}

	@Override
	public ChapterContentDTO findChapterContent(String url) {

		return chapterContentConv.toChapterContentDTO(appService.findChapterContent(url));
	}
}