package cn.deepdraw.training.crawler.novel.app.infrastructure.motan.proxy.crawler;

import java.util.List;

import org.springframework.stereotype.Component;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.gateway.NovelCrawlerApiGateway;

/**
 * Novel Controller
 * @author huangjiancheng
 * 2020-05-21
 */
@Component
public class NovelCrawlerApiGatewayProxy implements NovelCrawlerApiGateway {

	@MotanReferer(basicReferer = "baseReferer")
	private NovelCrawlerApiGateway apiGateway;

	@Override
	public List<NovelDTO> find(String site, String keywords) {

		return apiGateway.find(site, keywords);
	}

	@Override
	public NovelDTO findNovel(String site, String url) {

		return apiGateway.findNovel(site, url);
	}

	@Override
	public ChapterContentDTO findChapterContent(String site, String url) {

		return apiGateway.findChapterContent(site, url);
	}

	@Override
	public List<ChapterDTO> findChapters(String site, String url) {

		return apiGateway.findChapters(site, url);
	}
}