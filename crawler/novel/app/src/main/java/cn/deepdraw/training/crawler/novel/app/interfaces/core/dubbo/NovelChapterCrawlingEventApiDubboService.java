package cn.deepdraw.training.crawler.novel.app.interfaces.core.dubbo;

import org.apache.commons.lang3.EnumUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.api.NovelChapterCrawlingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterCrawlingEventDTO;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelChapterCrawlingEventAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
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

		return eventConv.done(eventAppService.create(novelId, EnumUtils.getEnum(Site.class, site), chapterId, link));
	}

	@Override
	public NovelChapterCrawlingEventDTO publish(Long novelId, String name, LinkAddress address) throws WebAppRuntimeException {

		LinkAddr addr = LinkAddr.of(EnumUtils.getEnum(Site.class, address.getSite()), address.getLink(), address.getPath());
		return eventConv.done(eventAppService.publish(novelId, name, addr));
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