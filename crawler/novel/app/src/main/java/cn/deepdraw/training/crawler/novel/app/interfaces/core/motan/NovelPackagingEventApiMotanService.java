package cn.deepdraw.training.crawler.novel.app.interfaces.core.motan;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import cn.deepdraw.training.crawler.novel.api.NovelPackagingEventApi;
import cn.deepdraw.training.crawler.novel.api.dto.NovelPackagingEventDTO;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelPackagingEventAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelPackagingEventAdapter;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelPackagingEventApi MotanService
 * @author huangjiancheng
 * @Date 2021-02-23
 */
@Transactional
@MotanService(basicService = "novelPackagingEventBaseService")
public class NovelPackagingEventApiMotanService implements NovelPackagingEventApi {
	
	@Autowired
	private NovelPackagingEventAppService eventAppService;
	
	@Autowired
	private NovelPackagingEventAdapter eventAdapter;

	@Override
	public NovelPackagingEventDTO create(String novelId, String site) throws WebAppRuntimeException {

		return eventAdapter.adapt(eventAppService.create(novelId, EnumUtils.getEnum(Site.class, site)));
	}

	@Override
	public NovelPackagingEventDTO publish(String eventId) throws WebAppRuntimeException {

		return eventAdapter.adapt(eventAppService.publish(eventId));
	}

	@Override
	public NovelPackagingEventDTO complete(String eventId, String path) throws WebAppRuntimeException {

		return eventAdapter.adapt(eventAppService.complete(eventId, path));
	}
}