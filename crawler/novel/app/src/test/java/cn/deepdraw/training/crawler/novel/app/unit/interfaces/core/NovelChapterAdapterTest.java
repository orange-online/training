package cn.deepdraw.training.crawler.novel.app.unit.interfaces.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterDTO;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapter;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelChapterAdapter;

/**
 * @author xjn
 * @description NovelChapterAdapterTest
 * @date 2020/11/20
 */
public class NovelChapterAdapterTest {

	private NovelChapterAdapter adapter = new NovelChapterAdapter();

	@Test
	public void doAdapt_happyPath() {

		LinkAddr addr = LinkAddr.of(LinkAddr.Site.BIQUGE, "link", null);
		Novel novel = Novel.of("novelId", "novelName", "author", addr);
		NovelChapter chapter = NovelChapter.of(novel, "chapterId", "chapterName", addr);

		NovelChapterDTO dto = adapter.adapt(chapter);

		assertEquals("novelId", dto.getNovelId());
		assertEquals("chapterId", dto.getChapterId());
		assertEquals("chapterName", dto.getName());
		assertNotNull(dto.getAddress());
	}
}
