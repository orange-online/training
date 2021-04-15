package cn.deepdraw.training.crawler.novel.app.infrastructure.motan.proxy.crawler;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;

/**
 * NovelCrawlerApi Proxy
 * @author huangjiancheng
 * @Date 2020-11-27
 */
@Component
public class NovelCrawlerApiProxy {

	@Autowired
	private NovelCrawlerApiGatewayProxy crawlerGateway;
	
	@Autowired
	private NovelRepository novelRepo;

	public Novel crawl(Site site, String link) {

		NovelDTO novelDTO = Validate.notNull(crawlerGateway.findNovel(site.toString(), link), "novel_crawled_failed");
		return Novel.of(novelRepo.generateIdString(), novelDTO.getName(), novelDTO.getAuthor(), LinkAddr.of(site, link, null));
	}
}