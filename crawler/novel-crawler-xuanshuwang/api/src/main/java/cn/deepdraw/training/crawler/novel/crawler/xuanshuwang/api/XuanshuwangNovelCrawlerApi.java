package cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.api;

import cn.deepdraw.training.crawler.novel.crawler.api.NovelCrawlerApi;

/**
 * Xuanshuwang NovelCrawlerApi
 * @author huangjiancheng
 * 2020-06-11
 */
public interface XuanshuwangNovelCrawlerApi extends NovelCrawlerApi {

	@Override
	default String site() {

		return "XUANSHUWANG";
	}
}