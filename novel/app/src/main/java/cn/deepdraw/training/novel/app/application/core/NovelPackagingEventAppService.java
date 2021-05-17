package cn.deepdraw.training.novel.app.application.core;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.novel.app.domain.core.NovelPackagingEvent;

/**
 * NovelPackagingEvent AppService
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelPackagingEventAppService {

	NovelPackagingEvent create(Long novelId, String site, Long version) throws WebAppRuntimeException;

	NovelPackagingEvent publish(Long eventId) throws WebAppRuntimeException;

	NovelPackagingEvent complete(Long eventId, String path) throws WebAppRuntimeException;

}