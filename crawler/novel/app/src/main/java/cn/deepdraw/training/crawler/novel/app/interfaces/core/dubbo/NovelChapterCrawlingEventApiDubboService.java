package cn.deepdraw.training.crawler.novel.app.interfaces.core.dubbo;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.api.NovelChapterCrawlingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterCrawlingEventDTO;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelChapterCrawlingEventAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelChapterCrawlingEventConv;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelChapterCrawlingEventApi MotanService
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@DubboService
@Transactional
public class NovelChapterCrawlingEventApiDubboService implements NovelChapterCrawlingEventApi {
	
	@Autowired
	private NovelChapterCrawlingEventAppService eventAppService;
	
	@Autowired
	private NovelChapterCrawlingEventConv eventConv;

	@Override
	public NovelChapterCrawlingEventDTO create(Long novelId, String site, Long chapterId, String link) throws WebAppRuntimeException {

		return eventConv.done(eventAppService.create(novelId, site, chapterId, link));
	}

	@Override
	public NovelChapterCrawlingEventDTO publish(Long novelId, String name, LinkAddress address, Integer index) throws WebAppRuntimeException {

		LinkAddr addr = LinkAddr.of(address.getSite(), address.getLink(), address.getPath());
		return eventConv.done(eventAppService.publish(novelId, name, addr, index));
	}

	@Override
	public NovelChapterCrawlingEventDTO publish(Long eventId) throws WebAppRuntimeException {

		return eventConv.done(eventAppService.publish(eventId));
	}

	@Override
	public NovelChapterCrawlingEventDTO complete(Long eventId, String path) throws WebAppRuntimeException {

		return eventConv.done(eventAppService.complete(eventId, path));
	}
}