package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.unit.domain;

import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContent;

/**
 * LiudatxtNovelChapterContent Test
 * @author huangjiancheng
 * 2020-06-07
 */
public class LiudatxtNovelChapterContentTest {

	@Test
	public void should_return_a_not_null_instance_when_of_factory_method_called_if_args_are_legal() {

		LiudatxtNovelChapterContent instance = LiudatxtNovelChapterContent.of("content");
		Assert.assertNotNull(instance);
		Assert.assertEquals("content", instance.content());
	}

	@Test
	public void should_return_null_when_of_factory_method_called_if_args_are_illegal() {

		Assert.assertNull(LiudatxtNovelChapterContent.of(null));
	}

	@Test
	public void should_return_true_when_equals_or_equalTo_method_called_if_all_properties_are_equals() {

		LiudatxtNovelChapterContent instance1 = LiudatxtNovelChapterContent.of("content");
		LiudatxtNovelChapterContent instance2 = LiudatxtNovelChapterContent.of("content");
		Assert.assertTrue(instance1.equals(instance2));
		Assert.assertTrue(instance1.equalTo(instance2));

		Assert.assertTrue(instance1.equals(instance1));
		Assert.assertTrue(instance1.equalTo(instance1));
	}

	@Test
	public void should_return_false_when_equals_or_equalTo_method_called_if_not_all_properties_are_equals() {

		LiudatxtNovelChapterContent instance1 = LiudatxtNovelChapterContent.of("content");
		LiudatxtNovelChapterContent instance2 = LiudatxtNovelChapterContent.of("content1");
		Assert.assertFalse(instance1.equals(instance2));
		Assert.assertFalse(instance1.equalTo(instance2));

		Assert.assertFalse(instance1.equals(null));
		Assert.assertFalse(instance1.equalTo(null));

		Assert.assertFalse(instance1.equals(new Object()));
	}

	@Test
	public void should_return_a_json_string_who_contains_all_property_values_when_toString_method_called() {

		LiudatxtNovelChapterContent instance = LiudatxtNovelChapterContent.of("content");
		Assert.assertEquals("{\"content\":\"content\"}", instance.toString());
	}

	@Test
	public void should_return_the_same_hashCode_of_toString_when_hashCode_method_called() {

		LiudatxtNovelChapterContent instance = LiudatxtNovelChapterContent.of("content");
		Assert.assertEquals("{\"content\":\"content\"}".hashCode(), instance.hashCode());
	}
}