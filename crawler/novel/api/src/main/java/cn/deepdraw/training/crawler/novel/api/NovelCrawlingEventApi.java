package cn.deepdraw.training.crawler.novel.api;

import cn.deepdraw.training.crawler.novel.api.dto.NovelCrawlingEventDTO;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * Novel Crawling Event Api
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelCrawlingEventApi {

	NovelCrawlingEventDTO create(String novelId, String site, String link) throws WebAppRuntimeException;

	NovelCrawlingEventDTO publish(String eventId) throws WebAppRuntimeException;

	NovelCrawlingEventDTO complete(String eventId, String path) throws WebAppRuntimeException;
}