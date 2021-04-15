package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.unit.interfaces;

import java.util.Arrays;
import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovel;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.interfaces.LiudatxtNovelConverter;

/**
 * LiudatxtNovelConverter Test
 * @author huangjiancheng
 * 2020-06-07
 */
public class LiudatxtNovelConverterTest {

	@Test
	public void should_return_empty_list_when_novels_is_empty() {

		Assert.assertThat(new LiudatxtNovelConverter().toNovelDTOs(Collections.emptyList()), Matchers.empty());
	}

	@Test
	public void should_return_a_list_with_same_size_when_novels_is_not_empty_and_novels_has_no_null_element() {

		Assert.assertThat(new LiudatxtNovelConverter().toNovelDTOs(Arrays.asList(LiudatxtNovel.of("novel_name", "novel_author", "url"))), Matchers.hasSize(1));
	}

	@Test
	public void should_return_a_list_with_same_size_when_novels_is_not_empty_and_novels_has_a_null_element() {

		Assert.assertThat(new LiudatxtNovelConverter().toNovelDTOs(Arrays.asList(LiudatxtNovel.of("novel_name", "novel_author", "url"), null)), Matchers.hasSize(1));
	}
}