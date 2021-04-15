package cn.deepdraw.training.crawler.novel.app.interfaces.core;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.api.dto.NovelPackagingEventDTO;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEvent;
import cn.deepdraw.training.framework.api.adapter.EntityBaseAdapter;

/**
 * NovelPackagingEvent Adapter
 * @author huangjiancheng
 * @Date 2021-02-23
 */
@Component
public class NovelPackagingEventAdapter extends EntityBaseAdapter<NovelPackagingEvent, NovelPackagingEventDTO> {

	@Override
	protected NovelPackagingEventDTO doAdapt(NovelPackagingEvent event) {

		NovelPackagingEventDTO dto = new NovelPackagingEventDTO();
		dto.setEventId(event.eventId());
		dto.setNovelId(event.novelId());
		dto.setSite(event.siteString());
		dto.setPublished(event.published());
		dto.setCompleted(event.completed());
		return dto;
	}
}