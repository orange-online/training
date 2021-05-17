package cn.deepdraw.training.novel.app.unit.interfaces.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import cn.deepdraw.training.novel.api.dto.NovelDTO;
import cn.deepdraw.training.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.novel.app.domain.core.Novel;
import cn.deepdraw.training.novel.app.interfaces.core.NovelPageConv;

/**
 * @author xjn
 * @description NovelAdapterTest
 * @date 2020/11/20
 */
public class NovelPageConvTest {

	private NovelPageConv conv = new NovelPageConv();

	@Test
	public void done_happyPath() {

		LinkAddr addr = LinkAddr.of("BIQUGE", 234L, "link", null);
		Novel novel = Novel.of("name", "author", addr);
		novel.entityId(123L);
		NovelDTO dto = conv.done(novel);

		assertEquals(123L, dto.getEntityId().longValue());
		assertEquals("name", dto.getName());
		assertEquals("author", dto.getAuthor());
		assertNotNull(dto.addressOf("BIQUGE"));
	}
}
