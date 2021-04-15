package cn.deepdraw.training.crawler.novel.app.interfaces.core;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterCrawlingEventDTO;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEvent;
import cn.deepdraw.training.framework.api.adapter.EntityBaseAdapter;

/**
 * NovelChapterCrawlingEvent Adapter
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@Component
public class NovelChapterCrawlingEventAdapter extends EntityBaseAdapter<NovelChapterCrawlingEvent, NovelChapterCrawlingEventDTO> {

	@Override
	protected NovelChapterCrawlingEventDTO doAdapt(NovelChapterCrawlingEvent event) {

		NovelChapterCrawlingEventDTO dto = new NovelChapterCrawlingEventDTO();
		dto.setEventId(event.eventId());
		dto.setNovelId(event.novelId());
		dto.setSite(event.siteString());
		dto.setChapterId(event.chapterId());
		dto.setLink(event.link());
		dto.setPublished(event.published());
		dto.setCompleted(event.completed());
		return dto;
	}
}