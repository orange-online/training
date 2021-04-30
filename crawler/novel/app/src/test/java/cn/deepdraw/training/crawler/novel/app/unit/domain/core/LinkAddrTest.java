package cn.deepdraw.training.crawler.novel.app.unit.domain.core;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;

/**
 * NovelLink Test
 * @author huangjiancheng
 * 2020-06-07
 */
public class LinkAddrTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void should_return_a_not_null_instance_when_of_factory_method_called_if_args_are_legal() {

		LinkAddr instance = LinkAddr.of("BIQUGE", "link", "path");
		Assert.assertNotNull(instance);
		Assert.assertEquals("BIQUGE", instance.site());
		Assert.assertEquals("link", instance.link());
		Assert.assertEquals("path", instance.path());
		
		instance = LinkAddr.of("BIQUGE", "link", null);
		Assert.assertNotNull(instance);
		Assert.assertEquals("BIQUGE", instance.site());
		Assert.assertEquals("link", instance.link());
		Assert.assertNull(instance.path());
	}

	@Test
	public void should_throw_exception_when_of_factory_method_called_if_args_are_illegal() {

		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("site_cannot_be_blank");
		LinkAddr.of(null, "link", "path");

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("link_cannot_be_blank");
		LinkAddr.of("BIQUGE", "  ", "path");
	}

	@Test
	public void should_return_the_latest_instance_when_update_method_called_if_site_is_legal() {

		LinkAddr instance1 = LinkAddr.of("BIQUGE", "link1", "path1");
		LinkAddr instance2 = LinkAddr.of("BIQUGE", "link2", "path2");
		instance1.update(instance2);
		Assert.assertEquals("BIQUGE", instance1.site());
		Assert.assertEquals("link2", instance1.link());
		Assert.assertEquals("path2", instance1.path());
	}

	@Test
	public void should_throw_exception_when_update_method_called_but_site_is_illegal() {

		LinkAddr instance1 = LinkAddr.of("BIQUGE", "link1", "path1");
		LinkAddr instance2 = LinkAddr.of("LIUDATXT", "link2", "path2");

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("site_illegal");
		instance1.update(instance2);
	}

	@Test
	public void should_return_the_latest_instance_when_updatePath_method_called_if_path_is_not_blank() {

		LinkAddr instance = LinkAddr.of("BIQUGE", "link1", "path1");
		instance.updatePath("path2");
		Assert.assertEquals("BIQUGE", instance.site());
		Assert.assertEquals("link1", instance.link());
		Assert.assertEquals("path2", instance.path());
	}

	@Test
	public void should_throw_exception_when_updatePath_method_called_but_path_is_blank() {

		LinkAddr instance = LinkAddr.of("BIQUGE", "link1", "path1");

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("path_cannot_be_blank");
		instance.updatePath("  ");
	}

	@Test
	public void should_return_true_when_equals_or_equalTo_method_called_if_all_properties_are_equals() {

		LinkAddr instance1 = LinkAddr.of("BIQUGE", "link", "path");
		LinkAddr instance2 = LinkAddr.of("BIQUGE", "link", "path");
		Assert.assertTrue(instance1.equals(instance2));
		Assert.assertTrue(instance1.equalTo(instance2));

		Assert.assertTrue(instance1.equals(instance1));
		Assert.assertTrue(instance1.equalTo(instance1));
	}

	@Test
	public void should_return_false_when_equals_or_equalTo_method_called_if_not_all_properties_are_equals() {

		LinkAddr instance1 = LinkAddr.of("BIQUGE", "link", "path");
		LinkAddr instance2 = LinkAddr.of("BIQUGE", "link1", "path");
		Assert.assertFalse(instance1.equals(instance2));
		Assert.assertFalse(instance1.equalTo(instance2));

		Assert.assertFalse(instance1.equals(null));
		Assert.assertFalse(instance1.equalTo(null));

		Assert.assertFalse(instance1.equals(new Object()));
	}

	@Test
	public void should_return_a_json_string_who_contains_all_property_values_when_toString_method_called() {

		LinkAddr instance = LinkAddr.of("BIQUGE", "link", "path");
		Assert.assertEquals("{\"site\":\"BIQUGE\",\"link\":\"link\",\"path\":\"path\"}", instance.toString());
	}

	@Test
	public void should_return_the_same_hashCode_of_toString_when_hashCode_method_called() {

		LinkAddr instance = LinkAddr.of("BIQUGE", "link", "path");
		Assert.assertEquals("{\"site\":\"BIQUGE\",\"link\":\"link\",\"path\":\"path\"}".hashCode(), instance.hashCode());
	}
}