package cn.deepdraw.training.crawler.novel.crawler.bus.app.infrastructure.motan.proxy.novel;

import org.springframework.stereotype.Component;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;

import cn.deepdraw.training.crawler.novel.api.NovelPackagingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.NovelPackagingEventDTO;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelPackagingEventApi Proxy
 * @author huangjiancheng
 * @Date 2021-02-22
 */
@Component
public class NovelPackagingEventApiProxy implements NovelPackagingEventApi {
	
	@MotanReferer(basicReferer = "baseReferer")
	private NovelPackagingEventApi eventApi;

	@Override
	public NovelPackagingEventDTO complete(String eventId, String path) throws WebAppRuntimeException {

		return eventApi.complete(eventId, path);
	}

	@Override
	public NovelPackagingEventDTO create(String novelId, String site) throws WebAppRuntimeException {

		throw new UnsupportedOperationException("unsupported operation in the 'bus-app' service.");
	}

	@Override
	public NovelPackagingEventDTO publish(String eventId) throws WebAppRuntimeException {

		throw new UnsupportedOperationException("unsupported operation in the 'bus-app' service.");
	}
}