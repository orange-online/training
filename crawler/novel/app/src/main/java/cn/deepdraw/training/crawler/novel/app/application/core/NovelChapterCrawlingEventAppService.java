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

	NovelChapterCrawlingEvent create(Long novelId, Site site, Long chapterId, String link) throws WebAppRuntimeException;

	NovelChapterCrawlingEvent publish(Long novelId, String name, LinkAddr addr, Integer index) throws WebAppRuntimeException;

	NovelChapterCrawlingEvent publish(Long eventId) throws WebAppRuntimeException;

	NovelChapterCrawlingEvent complete(Long eventId, String path) throws WebAppRuntimeException;

}