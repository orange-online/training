package cn.deepdraw.training.crawler.novel.api;

import cn.deepdraw.training.crawler.novel.api.dto.NovelCrawlingEventDTO;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * Novel Crawling Event Api
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelCrawlingEventApi {

	NovelCrawlingEventDTO create(Long novelId, String site, Long version, String link) throws WebAppRuntimeException;

	NovelCrawlingEventDTO publish(Long eventId) throws WebAppRuntimeException;

	NovelCrawlingEventDTO complete(Long eventId, String path) throws WebAppRuntimeException;
}