package cn.deepdraw.training.crawler.novel.app.application.core;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEvent;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelChapterCrawlingEvent AppService
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelChapterCrawlingEventAppService {

	NovelChapterCrawlingEvent create(String novelId, Site site, String chapterId, String link) throws WebAppRuntimeException;

	NovelChapterCrawlingEvent publish(String novelId, String name, LinkAddr addr) throws WebAppRuntimeException;

	NovelChapterCrawlingEvent publish(String eventId) throws WebAppRuntimeException;

	NovelChapterCrawlingEvent complete(String eventId, String path) throws WebAppRuntimeException;

}