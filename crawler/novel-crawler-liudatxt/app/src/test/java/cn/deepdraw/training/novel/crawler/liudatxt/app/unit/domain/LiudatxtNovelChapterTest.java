package cn.deepdraw.training.novel.crawler.liudatxt.app.unit.domain;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapter;

/**
 * LiudatxtNovelChapter Test
 * @author huangjiancheng
 * 2020-06-07
 */
public class LiudatxtNovelChapterTest {

	@Test
	public void should_return_a_not_null_instance_when_of_factory_method_called_if_args_are_legal() {

		LiudatxtNovelChapter instance = LiudatxtNovelChapter.of("chapter_name", "url", 300);
		Assert.assertNotNull(instance);
		Assert.assertEquals("chapter_name", instance.name());
		Assert.assertEquals("url", instance.link());
		Assert.assertThat(instance.index(), Matchers.is(300));
	}

	@Test
	public void should_return_null_when_of_factory_method_called_if_args_are_illegal() {

		Assert.assertNull(LiudatxtNovelChapter.of(null, "url", 300));
		Assert.assertNull(LiudatxtNovelChapter.of("chapter_name", null, 300));
		Assert.assertNull(LiudatxtNovelChapter.of("chapter_name", "url", null));
		Assert.assertNull(LiudatxtNovelChapter.of("chapter_name", "url", 0));
		Assert.assertNull(LiudatxtNovelChapter.of("chapter_name", "url", -1));
	}

	@Test
	public void should_return_true_when_equals_or_equalTo_method_called_if_all_properties_are_equals() {

		LiudatxtNovelChapter instance1 = LiudatxtNovelChapter.of("chapter_name", "url", 300);
		LiudatxtNovelChapter instance2 = LiudatxtNovelChapter.of("chapter_name", "url", 300);
		Assert.assertTrue(instance1.equals(instance2));
		Assert.assertTrue(instance1.equalTo(instance2));

		Assert.assertTrue(instance1.equals(instance1));
		Assert.assertTrue(instance1.equalTo(instance1));
	}

	@Test
	public void should_return_false_when_equals_or_equalTo_method_called_if_not_all_properties_are_equals() {

		LiudatxtNovelChapter instance1 = LiudatxtNovelChapter.of("chapter_name", "url", 300);
		LiudatxtNovelChapter instance2 = LiudatxtNovelChapter.of("chapter_name", "url1", 300);
		Assert.assertFalse(instance1.equals(instance2));
		Assert.assertFalse(instance1.equalTo(instance2));

		Assert.assertFalse(instance1.equals(null));
		Assert.assertFalse(instance1.equalTo(null));

		Assert.assertFalse(instance1.equals(new Object()));
	}

	@Test
	public void should_return_a_json_string_who_contains_all_property_values_when_toString_method_called() {

		LiudatxtNovelChapter instance = LiudatxtNovelChapter.of("chapter_name", "url", 300);
		Assert.assertEquals("{\"name\":\"chapter_name\",\"link\":\"url\",\"index\":300}", instance.toString());
	}

	@Test
	public void should_return_the_same_hashCode_of_toString_when_hashCode_method_called() {

		LiudatxtNovelChapter instance = LiudatxtNovelChapter.of("chapter_name", "url", 300);
		Assert.assertEquals("{\"name\":\"chapter_name\",\"link\":\"url\",\"index\":300}".hashCode(), instance.hashCode());
	}
}