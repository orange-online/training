package cn.deepdraw.training.crawler.novel.app.domain.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.app.infrastructure.dubbo.proxy.crawler.NovelCrawlerApiProxy;

/**
 * Novel Service
 * @author huangjiancheng
 * @Date 2020-11-27
 */
@Component
public class NovelService {

	@Autowired
	private NovelRepository novelRepo;
	
	@Autowired
	private NovelCrawlerApiProxy crawler;

	public Novel crawl(String site, Long version, String link) {
		
		Novel novelCrawled = crawler.crawl(site, version, link);
		Novel persistent = novelRepo.findByUnique(novelCrawled.name(), novelCrawled.author());
		if (persistent != null) {
			
			persistent = novelRepo.update(persistent.updateAddr(novelCrawled.addrOf(site, version)));
		} else {
			
			persistent = novelRepo.create(novelCrawled);
		}
		return persistent;
	}
}