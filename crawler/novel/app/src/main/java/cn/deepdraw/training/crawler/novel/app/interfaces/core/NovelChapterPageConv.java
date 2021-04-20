package cn.deepdraw.training.crawler.novel.app.interfaces.core;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterDTO;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapter;
import cn.deepdraw.training.framework.api.conv.IdLongEntityPageConv;

/**
 * NovelChapter Adapter
 * @author huangjiancheng
 * 2020-07-22
 */
@Component
public class NovelChapterPageConv extends IdLongEntityPageConv<NovelChapter, NovelChapterDTO> {

	@Override
	protected NovelChapterDTO doing(NovelChapter chapter) {

		NovelChapterDTO dto = new NovelChapterDTO();
		dto.setNovelId(chapter.novel().entityId());
		dto.setName(chapter.name());
		dto.setAddress(LinkAddress.of(chapter.site().toString(), chapter.link(), chapter.path()));
		return dto;
	}
}