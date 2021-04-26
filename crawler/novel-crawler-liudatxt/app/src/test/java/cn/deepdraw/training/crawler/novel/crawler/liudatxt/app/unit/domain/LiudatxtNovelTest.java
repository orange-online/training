package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.unit.domain;

import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovel;

/**
 * LiudatxtNovel Test
 * @author huangjiancheng
 * 2020-06-07
 */
public class LiudatxtNovelTest {

	@Test
	public void should_return_a_not_null_instance_when_of_factory_method_called_if_args_are_legal() {

		LiudatxtNovel instance = LiudatxtNovel.of("novel_name", "novel_author", "url");
		Assert.assertNotNull(instance);
		Assert.assertEquals("novel_name", instance.name());
		Assert.assertEquals("novel_author", instance.author());
		Assert.assertEquals("url", instance.link());
	}

	@Test
	public void should_return_null_when_of_factory_method_called_if_args_are_illegal() {

		Assert.assertNull(LiudatxtNovel.of(null, "novel_author", "url"));
		Assert.assertNull(LiudatxtNovel.of("novel_name", null, "url"));
		Assert.assertNull(LiudatxtNovel.of("novel_name", "novel_author", null));
	}

	@Test
	public void should_return_true_when_equals_or_equalTo_method_called_if_all_properties_are_equals() {

		LiudatxtNovel instance1 = LiudatxtNovel.of("novel_name", "novel_author", "url");
		LiudatxtNovel instance2 = LiudatxtNovel.of("novel_name", "novel_author", "url");
		Assert.assertTrue(instance1.equals(instance2));
		Assert.assertTrue(instance1.equalTo(instance2));

		Assert.assertTrue(instance1.equals(instance1));
		Assert.assertTrue(instance1.equalTo(instance1));
	}

	@Test
	public void should_return_false_when_equals_or_equalTo_method_called_if_not_all_properties_are_equals() {

		LiudatxtNovel instance1 = LiudatxtNovel.of("novel_name", "novel_author", "url");
		LiudatxtNovel instance2 = LiudatxtNovel.of("novel_name", "novel_author1", "url");
		Assert.assertFalse(instance1.equals(instance2));
		Assert.assertFalse(instance1.equalTo(instance2));

		Assert.assertFalse(instance1.equals(null));
		Assert.assertFalse(instance1.equalTo(null));

		Assert.assertFalse(instance1.equals(new Object()));
	}

	@Test
	public void should_return_a_json_string_who_contains_all_property_values_when_toString_method_called() {

		LiudatxtNovel instance = LiudatxtNovel.of("novel_name", "novel_author", "url");
		Assert.assertEquals("{\"name\":\"novel_name\",\"author\":\"novel_author\",\"link\":\"url\"}", instance.toString());
	}

	@Test
	public void should_return_the_same_hashCode_of_toString_when_hashCode_method_called() {

		LiudatxtNovel instance = LiudatxtNovel.of("novel_name", "novel_author", "url");
		Assert.assertEquals("{\"name\":\"novel_name\",\"author\":\"novel_author\",\"link\":\"url\"}".hashCode(), instance.hashCode());
	}
}