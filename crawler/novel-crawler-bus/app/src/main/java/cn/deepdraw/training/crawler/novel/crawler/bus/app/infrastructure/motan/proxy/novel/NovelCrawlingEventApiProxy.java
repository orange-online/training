package cn.deepdraw.training.crawler.novel.crawler.bus.app.infrastructure.motan.proxy.novel;

import org.springframework.stereotype.Component;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;

import cn.deepdraw.training.crawler.novel.api.NovelCrawlingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.NovelCrawlingEventDTO;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelCrawlingEventApi Proxy
 * @author huangjiancheng
 * @Date 2020-11-29
 */
@Component
public class NovelCrawlingEventApiProxy implements NovelCrawlingEventApi {
	
	@MotanReferer(basicReferer = "baseReferer")
	private NovelCrawlingEventApi eventApi;

	@Override
	public NovelCrawlingEventDTO create(String novelId, String site, String link) throws WebAppRuntimeException {

		return eventApi.create(novelId, site, link);
	}

	@Override
	public NovelCrawlingEventDTO publish(String eventId) throws WebAppRuntimeException {

		return eventApi.publish(eventId);
	}

	@Override
	public NovelCrawlingEventDTO complete(String eventId, String path) throws WebAppRuntimeException {

		return eventApi.complete(eventId, path);
	}
}