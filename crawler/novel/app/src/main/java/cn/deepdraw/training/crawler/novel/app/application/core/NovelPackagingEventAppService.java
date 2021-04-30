package cn.deepdraw.training.crawler.novel.app.application.core;

import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEvent;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelPackagingEvent AppService
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelPackagingEventAppService {

	NovelPackagingEvent create(Long novelId, String site) throws WebAppRuntimeException;

	NovelPackagingEvent publish(Long eventId) throws WebAppRuntimeException;

	NovelPackagingEvent complete(Long eventId, String path) throws WebAppRuntimeException;

}