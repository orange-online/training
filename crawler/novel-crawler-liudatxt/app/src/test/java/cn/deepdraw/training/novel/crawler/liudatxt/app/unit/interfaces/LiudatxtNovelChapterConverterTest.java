package cn.deepdraw.training.novel.crawler.liudatxt.app.unit.interfaces;

import java.util.Arrays;
import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapter;
import cn.deepdraw.training.novel.crawler.liudatxt.app.interfaces.LiudatxtNovelChapterConverter;

/**
 * LiudatxtNovelChapterConverter Test
 * @author huangjiancheng
 * 2020-06-07
 */
public class LiudatxtNovelChapterConverterTest {

	@Test
	public void should_return_empty_list_when_chapters_is_empty() {

		Assert.assertThat(new LiudatxtNovelChapterConverter().toChapters(Collections.emptyList()), Matchers.empty());
	}

	@Test
	public void should_return_a_list_with_same_size_when_chapters_is_not_empty_and_chapters_has_no_null_element() {

		Assert.assertThat(new LiudatxtNovelChapterConverter().toChapters(Arrays.asList(LiudatxtNovelChapter.of("chapter_name", "url", 300))), Matchers.hasSize(1));
	}

	@Test
	public void should_return_a_list_with_same_size_when_chapters_is_not_empty_and_chapters_has_a_null_element() {

		Assert.assertThat(new LiudatxtNovelChapterConverter().toChapters(Arrays.asList(LiudatxtNovelChapter.of("chapter_name", "url", 300), null)), Matchers.hasSize(1));
	}
}