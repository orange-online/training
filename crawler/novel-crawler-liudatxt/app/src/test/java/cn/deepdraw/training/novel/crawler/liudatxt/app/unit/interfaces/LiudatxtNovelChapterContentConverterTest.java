package cn.deepdraw.training.novel.crawler.liudatxt.app.unit.interfaces;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContent;
import cn.deepdraw.training.novel.crawler.liudatxt.app.interfaces.LiudatxtNovelChapterContentConverter;

/**
 * LiudatxtNovelChapterContentConverter Test
 * @author huangjiancheng
 * 2020-06-07
 */
public class LiudatxtNovelChapterContentConverterTest {

	@Test
	public void should_return_null_when_chapterContent_is_null() {

		Assert.assertThat(new LiudatxtNovelChapterContentConverter().toChapterContent(null), Matchers.nullValue());
	}

	@Test
	public void should_return_object_not_null_when_chapterContent_is_not_null() {

		Assert.assertThat(new LiudatxtNovelChapterContentConverter().toChapterContent(LiudatxtNovelChapterContent.of("content")), Matchers.notNullValue());
	}
}