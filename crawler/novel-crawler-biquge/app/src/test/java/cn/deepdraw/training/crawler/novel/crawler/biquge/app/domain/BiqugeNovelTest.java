package cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author xjn
 * @description BiqugeNovelTest
 * @date 2020/11/20
 */
public class BiqugeNovelTest {

	@Test
	public void of_happyPath() {

		BiqugeNovel novel = BiqugeNovel.of("name", "author", "url");

		Assert.assertNotNull(novel);
		assertEquals("name", novel.getName());
		assertEquals("author", novel.getAuthor());
		assertEquals("url", novel.getLink());
	}

	@Test
	public void setName_happyPath() {

		BiqugeNovel novel = BiqugeNovel.of();
		novel.setName("name");

		Assert.assertNotNull(novel);
		assertEquals("name", novel.getName());
	}

	@Test
	public void setAuthor_happyPath() {

		BiqugeNovel novel = BiqugeNovel.of();
		novel.setAuthor("author");

		assertEquals("author", novel.getAuthor());
	}

	@Test
	public void setUrl_happyPath() {

		BiqugeNovel novel = BiqugeNovel.of();
		novel.setLink("url");

		assertEquals("url", novel.getLink());
	}

	@Test
	public void setTotalChapterNum_happyPath() {

		BiqugeNovel novel = BiqugeNovel.of();
		novel.setTotalChapterNum("totalChapterNum");

		assertEquals("totalChapterNum", novel.getTotalChapterNum());
	}
}
