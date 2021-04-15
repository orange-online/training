package cn.deepdraw.training.crawler.novel.crawler.gateway.app.interfaces.motan.crawler;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.crawler.novel.crawler.api.NovelCrawlerApi;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.gateway.NovelCrawlerApiGateway;

/**
 * NovelCrawlerApiGateway MotanService
 * @author huangjiancheng
 * @Date 2020-12-11
 */
@DubboService
public class NovelCrawlerApiGatewayMotanService implements NovelCrawlerApiGateway {

	@Autowired
	private List<NovelCrawlerApi> crawlers;

	@Override
	public List<NovelDTO> find(String site, String keywords) {

		return crawler(site).find(keywords);
	}

	@Override
	public NovelDTO findNovel(String site, String url) {

		return crawler(site).findNovel(url);
	}

	@Override
	public ChapterContentDTO findChapterContent(String site, String url) {

		return crawler(site).findChapterContent(url);
	}

	@Override
	public List<ChapterDTO> findChapters(String site, String url) {

		return crawler(site).findChapters(url);
	}

	private NovelCrawlerApi crawler(String site) {

		return Validate.notNull(crawlers.stream().filter(api -> api.site().equals(site)).findFirst().orElse(null), "unsupported_site");
	}
}