package cn.deepdraw.training.crawler.novel.app.interfaces.core.motan;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import cn.deepdraw.training.crawler.novel.api.NovelChapterCrawlingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterCrawlingEventDTO;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelChapterCrawlingEventAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelChapterCrawlingEventAdapter;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelChapterCrawlingEventApi MotanService
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@Transactional
@MotanService(basicService = "chapterCrawlingEventBaseService")
public class NovelChapterCrawlingEventApiMotanService implements NovelChapterCrawlingEventApi {
	
	@Autowired
	private NovelChapterCrawlingEventAppService eventAppService;
	
	@Autowired
	private NovelChapterCrawlingEventAdapter eventAdapter;

	@Override
	public NovelChapterCrawlingEventDTO create(String novelId, String site, String chapterId, String link) throws WebAppRuntimeException {

		return eventAdapter.adapt(eventAppService.create(novelId, EnumUtils.getEnum(Site.class, site), chapterId, link));
	}

	@Override
	public NovelChapterCrawlingEventDTO publish(String novelId, String name, LinkAddress address) throws WebAppRuntimeException {

		LinkAddr addr = LinkAddr.of(EnumUtils.getEnum(Site.class, address.getSite()), address.getLink(), address.getPath());
		return eventAdapter.adapt(eventAppService.publish(novelId, name, addr));
	}

	@Override
	public NovelChapterCrawlingEventDTO publish(String eventId) throws WebAppRuntimeException {

		return eventAdapter.adapt(eventAppService.publish(eventId));
	}

	@Override
	public NovelChapterCrawlingEventDTO complete(String eventId, String path) throws WebAppRuntimeException {

		return eventAdapter.adapt(eventAppService.complete(eventId, path));
	}
}