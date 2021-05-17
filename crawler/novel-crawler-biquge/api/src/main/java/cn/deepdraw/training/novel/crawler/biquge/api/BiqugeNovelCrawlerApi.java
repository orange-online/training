package cn.deepdraw.training.novel.crawler.biquge.api;

import cn.deepdraw.training.novel.crawler.api.NovelCrawlerApi;

/**
 * Biquge NovelCrawlerApi
 * @author huangjiancheng
 * 2020-06-11
 */
public interface BiqugeNovelCrawlerApi extends NovelCrawlerApi {

	@Override
	default String site() {

		return "BIQUGE";
	}
}