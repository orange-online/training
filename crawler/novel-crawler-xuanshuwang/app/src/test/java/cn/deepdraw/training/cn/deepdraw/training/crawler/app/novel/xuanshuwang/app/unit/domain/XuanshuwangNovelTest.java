package cn.deepdraw.training.cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.unit.domain;

import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.domain.XuanshuwangNovel;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author xjn
 * @description XuanshuwangNovelTest
 * @date 2020/11/20
 */
public class XuanshuwangNovelTest {

	@Test
	public void of_happyPath() {

		XuanshuwangNovel novel = XuanshuwangNovel.of("name", "author", "url");

		Assert.assertNotNull(novel);
		assertEquals("name", novel.name());
		assertEquals("author", novel.author());
		assertEquals("url", novel.url());
	}

	@Test
	public void equals_happyPath() {

		XuanshuwangNovel novel = XuanshuwangNovel.of("name", "author", "url");
		assertEquals(XuanshuwangNovel.of("name", "author", "url"), novel);
	}

	@Test
	public void hashCode_happyPath() {

		assertEquals(1255793196, XuanshuwangNovel.of("name", "author", "url").hashCode());
	}

	@Test
	public void toString_happyPath() {

		assertEquals(JsonNodeFactory.instance.objectNode().put("name", "name").put("author", "author").put("url", "url").toString(),
				XuanshuwangNovel.of("name", "author", "url").toString());
	}
}
