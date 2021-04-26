package cn.deepdraw.training.crawler.novel.crawler.gateway.app.interfaces.dubbo.crawler;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.crawler.novel.crawler.api.NovelCrawlerApi;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.crawler.novel.crawler.api.gateway.NovelCrawlerApiGateway;

/**
 * NovelCrawlerApiGateway MotanService
 * @author huangjiancheng
 * @Date 2020-12-11
 */
@DubboService
public class NovelCrawlerApiGatewayDubboService implements NovelCrawlerApiGateway {

	@Autowired
	private List<NovelCrawlerApi> crawlers;

	@Override
	public List<Novel> find(String keywords) {

		return crawlers.parallelStream().flatMap(crawler -> crawler.find(keywords).stream()).collect(Collectors.toList());
	}

	@Override
	public List<Novel> find(String site, String keywords) {

		return crawler(site).find(keywords);
	}

	@Override
	public Novel findNovel(String site, String url) {

		return crawler(site).findNovel(url);
	}

	@Override
	public ChapterContent findChapterContent(String site, String url) {

		return crawler(site).findChapterContent(url);
	}

	@Override
	public List<Chapter> findChapters(String site, String url) {

		return crawler(site).findChapters(url);
	}

	private NovelCrawlerApi crawler(String site) {

		return Validate.notNull(crawlers.stream().filter(api -> api.site().equals(site)).findFirst().orElse(null), "unsupported_site");
	}
}