package cn.deepdraw.training.crawler.novel.crawler.liudatxt.api;

import cn.deepdraw.training.crawler.novel.crawler.api.NovelCrawlerApi;

/**
 * Liudatxt NovelCrawlerApi
 * @author huangjiancheng
 * 2020-06-11
 */
public interface LiudatxtNovelCrawlerApi extends NovelCrawlerApi {

	@Override
	default String site() {

		return "LIUDATXT";
	}
}