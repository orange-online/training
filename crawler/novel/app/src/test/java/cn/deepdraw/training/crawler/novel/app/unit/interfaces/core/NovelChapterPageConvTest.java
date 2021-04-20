package cn.deepdraw.training.crawler.novel.app.unit.interfaces.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterDTO;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapter;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelChapterPageConv;

/**
 * @author xjn
 * @description NovelChapterAdapterTest
 * @date 2020/11/20
 */
public class NovelChapterPageConvTest {

	private NovelChapterPageConv conv = new NovelChapterPageConv();

	@Test
	public void done_happyPath() {

		LinkAddr addr = LinkAddr.of(LinkAddr.Site.BIQUGE, "link", null);
		Novel novel = Novel.of("novelName", "author", addr);
		novel.entityId(123L);
		NovelChapter chapter = NovelChapter.of(novel, "chapterName", addr);
		chapter.entityId(234L);
		NovelChapterDTO dto = conv.done(chapter);

		assertEquals(123L, dto.getNovelId().longValue());
		assertEquals(234L, dto.getEntityId().longValue());
		assertEquals("chapterName", dto.getName());
		assertNotNull(dto.getAddress());
	}
}
