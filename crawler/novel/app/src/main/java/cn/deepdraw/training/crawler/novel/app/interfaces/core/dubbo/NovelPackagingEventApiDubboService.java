package cn.deepdraw.training.crawler.novel.app.interfaces.core.dubbo;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.api.NovelPackagingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.NovelPackagingEventDTO;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelPackagingEventAppService;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelPackagingEventConv;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelPackagingEventApi MotanService
 * @author huangjiancheng
 * @Date 2021-02-23
 */
@DubboService
@Transactional
public class NovelPackagingEventApiDubboService implements NovelPackagingEventApi {
	
	@Autowired
	private NovelPackagingEventAppService eventAppService;
	
	@Autowired
	private NovelPackagingEventConv eventConv;

	@Override
	public NovelPackagingEventDTO create(Long novelId, String site) throws WebAppRuntimeException {

		return eventConv.done(eventAppService.create(novelId, site));
	}

	@Override
	public NovelPackagingEventDTO publish(Long eventId) throws WebAppRuntimeException {

		return eventConv.done(eventAppService.publish(eventId));
	}

	@Override
	public NovelPackagingEventDTO complete(Long eventId, String path) throws WebAppRuntimeException {

		return eventConv.done(eventAppService.complete(eventId, path));
	}
}