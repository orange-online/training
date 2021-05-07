package cn.deepdraw.training.crawler.novel.app.interfaces.core.dubbo;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.api.NovelCrawlingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.NovelCrawlingEventDTO;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelCrawlingEventAppService;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelCrawlingEventConv;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelCrawlingEventApi MotanService
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@DubboService
@Transactional
public class NovelCrawlingEventApiDubboService implements NovelCrawlingEventApi {
	
	@Autowired
	private NovelCrawlingEventAppService eventAppService;
	
	@Autowired
	private NovelCrawlingEventConv eventConv;

	@Override
	public NovelCrawlingEventDTO create(Long novelId, String site, Long version, String link) throws WebAppRuntimeException {

		return eventConv.done(eventAppService.create(novelId, site, version, link));
	}

	@Override
	public NovelCrawlingEventDTO publish(Long eventId) throws WebAppRuntimeException {

		return eventConv.done(eventAppService.publish(eventId));
	}

	@Override
	public NovelCrawlingEventDTO complete(Long eventId, String path) throws WebAppRuntimeException {

		return eventConv.done(eventAppService.complete(eventId, path));
	}
}