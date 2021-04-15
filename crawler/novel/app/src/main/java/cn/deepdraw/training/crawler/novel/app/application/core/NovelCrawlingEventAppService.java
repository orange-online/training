package cn.deepdraw.training.crawler.novel.app.application.core;

import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEvent;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelCrawlingEvent AppService
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public interface NovelCrawlingEventAppService {

	NovelCrawlingEvent create(String novelId, String site, String link) throws WebAppRuntimeException;

	NovelCrawlingEvent publish(String eventId) throws WebAppRuntimeException;

	NovelCrawlingEvent complete(String eventId, String path) throws WebAppRuntimeException;

}