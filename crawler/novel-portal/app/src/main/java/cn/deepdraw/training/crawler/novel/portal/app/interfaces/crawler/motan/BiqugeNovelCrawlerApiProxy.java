package cn.deepdraw.training.crawler.novel.portal.app.interfaces.crawler.motan;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.biquge.api.BiqugeNovelCrawlerApi;

/**
 * Novel Controller
 * @author huangjiancheng
 * 2020-05-21
 */
@Component
public class BiqugeNovelCrawlerApiProxy {

	@DubboReference
	private BiqugeNovelCrawlerApi crawlerApi;
}