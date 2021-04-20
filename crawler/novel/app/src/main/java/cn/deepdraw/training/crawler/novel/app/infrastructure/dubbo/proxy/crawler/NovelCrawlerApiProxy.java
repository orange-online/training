package cn.deepdraw.training.crawler.novel.app.infrastructure.dubbo.proxy.crawler;

import org.apache.commons.lang3.Validate;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.gateway.NovelCrawlerApiGateway;

/**
 * NovelCrawlerApi Proxy
 * @author huangjiancheng
 * @Date 2020-11-27
 */
@Component
public class NovelCrawlerApiProxy {

	@DubboReference
	private NovelCrawlerApiGateway crawlerGateway;
	
	public Novel crawl(Site site, String link) {

		NovelDTO novelDTO = Validate.notNull(crawlerGateway.findNovel(site.toString(), link), "novel_crawled_failed");
		return Novel.of(novelDTO.getName(), novelDTO.getAuthor(), LinkAddr.of(site, link, null));
	}
}