package cn.deepdraw.training.novel.app.unit.interfaces.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import cn.deepdraw.training.novel.api.dto.NovelChapterDTO;
import cn.deepdraw.training.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.novel.app.domain.core.Novel;
import cn.deepdraw.training.novel.app.domain.core.NovelChapter;
import cn.deepdraw.training.novel.app.interfaces.core.NovelChapterPageConv;

/**
 * @author xjn
 * @description NovelChapterAdapterTest
 * @date 2020/11/20
 */
public class NovelChapterPageConvTest {

	private NovelChapterPageConv conv = new NovelChapterPageConv();

	@Test
	public void done_happyPath() {

		LinkAddr addr = LinkAddr.of("BIQUGE", 234L, "link", null);
		Novel novel = Novel.of("novelName", "author", addr);
		novel.entityId(123L);
		NovelChapter chapter = NovelChapter.of(novel, "chapterName", addr, 1);
		chapter.entityId(234L);
		NovelChapterDTO dto = conv.done(chapter);

		assertEquals(123L, dto.getNovelId().longValue());
		assertEquals(234L, dto.getEntityId().longValue());
		assertEquals("chapterName", dto.getName());
		assertNotNull(dto.getAddress());
	}
}
