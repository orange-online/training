package cn.deepdraw.training.crawler.novel.app.interfaces.core;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.api.dto.NovelCrawlingEventDTO;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEvent;
import cn.deepdraw.training.framework.api.conv.EntityBaseConv;

/**
 * NovelCrawlingEvent Adapter
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@Component
public class NovelCrawlingEventConv extends EntityBaseConv<NovelCrawlingEvent, NovelCrawlingEventDTO> {

	@Override
	protected NovelCrawlingEventDTO doing(NovelCrawlingEvent event) {

		NovelCrawlingEventDTO dto = new NovelCrawlingEventDTO();
		dto.setNovelId(event.novelId());
		dto.setSite(event.siteString());
		dto.setLink(event.link());
		dto.setPublished(event.published());
		dto.setCompleted(event.completed());
		return dto;
	}
}