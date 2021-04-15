package cn.deepdraw.training.crawler.novel.app.interfaces.core.dubbo;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.api.NovelCrawlingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.NovelCrawlingEventDTO;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelCrawlingEventAppService;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelCrawlingEventAdapter;
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
	private NovelCrawlingEventAdapter eventAdapter;

	@Override
	public NovelCrawlingEventDTO create(String novelId, String site, String link) throws WebAppRuntimeException {

		return eventAdapter.adapt(eventAppService.create(novelId, site, link));
	}

	@Override
	public NovelCrawlingEventDTO publish(String eventId) throws WebAppRuntimeException {

		return eventAdapter.adapt(eventAppService.publish(eventId));
	}

	@Override
	public NovelCrawlingEventDTO complete(String eventId, String path) throws WebAppRuntimeException {

		return eventAdapter.adapt(eventAppService.complete(eventId, path));
	}
}