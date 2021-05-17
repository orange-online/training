package cn.deepdraw.training.novel.app.application.core;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.novel.app.domain.core.NovelChapterCrawlingEvent;

/**
 * NovelChapterCrawlingEvent AppService
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelChapterCrawlingEventAppService {

	NovelChapterCrawlingEvent create(Long novelId, String site, Long version, Long chapterId, String link) throws WebAppRuntimeException;

	NovelChapterCrawlingEvent publish(Long novelId, String name, LinkAddr addr, Integer index) throws WebAppRuntimeException;

	NovelChapterCrawlingEvent publish(Long eventId) throws WebAppRuntimeException;

	NovelChapterCrawlingEvent complete(Long eventId, String path) throws WebAppRuntimeException;

}