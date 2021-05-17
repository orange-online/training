package cn.deepdraw.training.novel.crawler.xuanshuwang.app.unit.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import cn.deepdraw.training.novel.crawler.xuanshuwang.app.domain.XuanshuwangNovel;

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
		assertEquals("url", novel.link());
	}

	@Test
	public void equals_happyPath() {

		XuanshuwangNovel novel = XuanshuwangNovel.of("name", "author", "url");
		assertEquals(XuanshuwangNovel.of("name", "author", "url"), novel);
	}

	@Test
	public void hashCode_happyPath() {

		assertEquals(JsonNodeFactory.instance.objectNode().put("name", "name").put("author", "author").put("link", "url").toString().hashCode(), XuanshuwangNovel.of("name", "author", "url").hashCode());
	}

	@Test
	public void toString_happyPath() {

		assertEquals(JsonNodeFactory.instance.objectNode().put("name", "name").put("author", "author").put("link", "url").toString(), XuanshuwangNovel.of("name", "author", "url").toString());
	}
}
