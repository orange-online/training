package cn.deepdraw.training.novel.app.interfaces.core;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.api.conv.IdLongEntityPageConv;
import cn.deepdraw.training.novel.api.dto.LinkAddress;
import cn.deepdraw.training.novel.api.dto.NovelChapterDTO;
import cn.deepdraw.training.novel.app.domain.core.NovelChapter;

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
		dto.setAddress(LinkAddress.of(chapter.site(), chapter.version(), chapter.link(), chapter.path()));
		dto.setIndex(chapter.index());
		return dto;
	}
}