package cn.deepdraw.training.crawler.novel.crawler.gateway.app.infrastructure.dubbo.proxy.crawler;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.liudatxt.api.LiudatxtNovelCrawlerApi;

/**
 * LiudatxtNovelCrawlerApi Proxy
 * @author huangjiancheng
 * 2020-05-21
 */
@Component
public class LiudatxtNovelCrawlerApiProxy {

	@DubboReference
	private LiudatxtNovelCrawlerApi crawlerApi;
}