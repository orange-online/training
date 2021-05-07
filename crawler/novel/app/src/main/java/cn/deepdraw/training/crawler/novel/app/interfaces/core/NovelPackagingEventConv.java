package cn.deepdraw.training.crawler.novel.app.interfaces.core;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.api.dto.NovelPackagingEventDTO;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEvent;
import cn.deepdraw.training.framework.api.conv.EntityBaseConv;

/**
 * NovelPackagingEvent Adapter
 * @author huangjiancheng
 * @Date 2021-02-23
 */
@Component
public class NovelPackagingEventConv extends EntityBaseConv<NovelPackagingEvent, NovelPackagingEventDTO> {

	@Override
	protected NovelPackagingEventDTO doing(NovelPackagingEvent event) {

		NovelPackagingEventDTO dto = new NovelPackagingEventDTO();
		dto.setNovelId(event.novelId());
		dto.setSite(event.site());
		dto.setPublished(event.published());
		dto.setCompleted(event.completed());
		return dto;
	}
}