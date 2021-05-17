package cn.deepdraw.training.novel.app.interfaces.core;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.api.conv.EntityBaseConv;
import cn.deepdraw.training.novel.api.dto.NovelChapterCrawlingEventDTO;
import cn.deepdraw.training.novel.app.domain.core.NovelChapterCrawlingEvent;

/**
 * NovelChapterCrawlingEvent Adapter
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@Component
public class NovelChapterCrawlingEventConv extends EntityBaseConv<NovelChapterCrawlingEvent, NovelChapterCrawlingEventDTO> {

	@Override
	protected NovelChapterCrawlingEventDTO doing(NovelChapterCrawlingEvent event) {

		NovelChapterCrawlingEventDTO dto = new NovelChapterCrawlingEventDTO();
		dto.setNovelId(event.novelId());
		dto.setSite(event.site());
		dto.setChapterId(event.chapterId());
		dto.setLink(event.link());
		dto.setPublished(event.published());
		dto.setCompleted(event.completed());
		return dto;
	}
}