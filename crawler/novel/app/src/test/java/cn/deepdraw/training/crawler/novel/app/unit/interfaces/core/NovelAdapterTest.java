package cn.deepdraw.training.crawler.novel.app.unit.interfaces.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import cn.deepdraw.training.crawler.novel.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelAdapter;

/**
 * @author xjn
 * @description NovelAdapterTest
 * @date 2020/11/20
 */
public class NovelAdapterTest {

	private NovelAdapter adapter = new NovelAdapter();

	@Test
	public void doAdapt_happyPath() {

		LinkAddr addr = LinkAddr.of(LinkAddr.Site.BIQUGE, "link", null);
		Novel novel = Novel.of("id", "name", "author", addr);

		NovelDTO dto = adapter.adapt(novel);

		assertEquals("id", dto.getNovelId());
		assertEquals("name", dto.getName());
		assertEquals("author", dto.getAuthor());
		assertNotNull(dto.addressOf("BIQUGE"));
	}
}
