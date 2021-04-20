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

	NovelChapterCrawlingEventDTO create(Long novelId, String site, Long chapterId, String link) throws WebAppRuntimeException;

	NovelChapterCrawlingEventDTO publish(Long novelId, String name, LinkAddress address) throws WebAppRuntimeException;

	NovelChapterCrawlingEventDTO publish(Long eventId) throws WebAppRuntimeException;

	NovelChapterCrawlingEventDTO complete(Long eventId, String path) throws WebAppRuntimeException;
}