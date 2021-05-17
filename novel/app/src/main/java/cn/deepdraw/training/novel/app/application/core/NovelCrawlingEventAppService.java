package cn.deepdraw.training.novel.app.application.core;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.novel.app.domain.core.NovelCrawlingEvent;

/**
 * NovelCrawlingEvent AppService
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelCrawlingEventAppService {

	NovelCrawlingEvent create(Long novelId, String site, Long version, String link) throws WebAppRuntimeException;

	NovelCrawlingEvent publish(Long eventId) throws WebAppRuntimeException;

	NovelCrawlingEvent complete(Long eventId, String path) throws WebAppRuntimeException;

}