package cn.deepdraw.training.crawler.novel.app.application.core;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEvent;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelPackagingEvent AppService
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelPackagingEventAppService {

	NovelPackagingEvent create(String novelId, Site site) throws WebAppRuntimeException;

	NovelPackagingEvent publish(String eventId) throws WebAppRuntimeException;

	NovelPackagingEvent complete(String eventId, String path) throws WebAppRuntimeException;

}