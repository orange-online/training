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
import cn.deepdraw.training.crawler.novel.crawler.channel.api.ChannelApi;
import cn.deepdraw.training.crawler.novel.crawler.channel.api.dto.ChannelDTO;

/**
 * NovelCrawlerApiGateway MotanService
 * @author huangjiancheng
 * @Date 2020-12-11
 */
@DubboService
public class NovelCrawlerApiGatewayDubboService implements NovelCrawlerApiGateway {
	
	private static final String UNSUPPORTED_SITE = "unsupported_site";
	
	@Autowired
	private ChannelApi channelApi;

	@Autowired
	private List<NovelCrawlerApi> crawlers;

	@Override
	public List<Novel> find(String keywords) {

		List<String> channelCodes = channelApi.findAvailable().parallelStream().map(channel -> channel.getCode()).collect(Collectors.toList());
		return crawlers.parallelStream().filter(crawler -> channelCodes.contains(crawler.site())).flatMap(crawler -> crawler.find(keywords).stream()).collect(Collectors.toList());
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

		ChannelDTO channel = channelApi.findByChannelCode(site);
		Validate.isTrue(channel != null && channel.isAvailable(), UNSUPPORTED_SITE);
		return Validate.notNull(crawlers.stream().filter(crawler -> crawler.site().equals(site)).findFirst().orElse(null), UNSUPPORTED_SITE);
	}
}