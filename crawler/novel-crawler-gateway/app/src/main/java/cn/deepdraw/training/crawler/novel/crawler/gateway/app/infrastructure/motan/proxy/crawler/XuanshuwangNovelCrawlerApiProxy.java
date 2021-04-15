package cn.deepdraw.training.crawler.novel.crawler.gateway.app.infrastructure.motan.proxy.crawler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;

import cn.deepdraw.training.crawler.novel.crawler.api.NovelCrawlerApi;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.api.XuanshuwangNovelCrawlerApi;

/**
 * Novel Controller
 * @author huangjiancheng
 * 2020-05-21
 */
@Component
public class XuanshuwangNovelCrawlerApiProxy implements NovelCrawlerApi {

	@MotanReferer(basicReferer = "xuanshuwangCrawlerBaseReferer")
	private XuanshuwangNovelCrawlerApi crawlerApi;

	@Override
	public List<NovelDTO> find(String keywords) {

		return crawlerApi.find(keywords);
	}

	@Override
	public NovelDTO findNovel(String url) {

		return crawlerApi.findNovel(url);
	}

	@Override
	public ChapterContentDTO findChapterContent(String url) {

		return crawlerApi.findChapterContent(url);
	}

	@Override
	public List<ChapterDTO> findChapters(String url) {

		return crawlerApi.findChapters(url);
	}

	@Override
	public String site() {

		return crawlerApi.site();
	}
}