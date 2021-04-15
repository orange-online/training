package cn.deepdraw.training.crawler.novel.crawler.bus.app.infrastructure.motan.proxy.novel;

import org.springframework.stereotype.Component;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;

import cn.deepdraw.training.crawler.novel.api.NovelChapterCrawlingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterCrawlingEventDTO;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelChapterCrawlingEventApi Proxy
 * @author huangjiancheng
 * @Date 2020-11-29
 */
@Component
public class NovelChapterCrawlingEventApiProxy implements NovelChapterCrawlingEventApi {
	
	@MotanReferer(basicReferer = "baseReferer")
	private NovelChapterCrawlingEventApi eventApi;

	@Override
	public NovelChapterCrawlingEventDTO create(String novelId, String site, String chapterId, String link) throws WebAppRuntimeException {

		return eventApi.create(novelId, site, chapterId, link);
	}

	@Override
	public NovelChapterCrawlingEventDTO publish(String novelId, String name, LinkAddress address) throws WebAppRuntimeException {

		return eventApi.publish(novelId, name, address);
	}

	@Override
	public NovelChapterCrawlingEventDTO publish(String eventId) throws WebAppRuntimeException {

		return eventApi.publish(eventId);
	}

	@Override
	public NovelChapterCrawlingEventDTO complete(String eventId, String path) throws WebAppRuntimeException {

		return eventApi.complete(eventId, path);
	}
}