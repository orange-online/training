package cn.deepdraw.training.crawler.novel.api;

import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterCrawlingEventDTO;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * Novel Chapter Crawling Event Api
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelChapterCrawlingEventApi {

	NovelChapterCrawlingEventDTO create(String novelId, String site, String chapterId, String link) throws WebAppRuntimeException;

	NovelChapterCrawlingEventDTO publish(String novelId, String name, LinkAddress address) throws WebAppRuntimeException;

	NovelChapterCrawlingEventDTO publish(String eventId) throws WebAppRuntimeException;

	NovelChapterCrawlingEventDTO complete(String eventId, String path) throws WebAppRuntimeException;
}