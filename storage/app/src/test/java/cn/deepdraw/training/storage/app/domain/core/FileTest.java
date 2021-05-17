package cn.deepdraw.training.storage.app.domain.core;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * File Test
 * @author huangjiancheng
 * 2020-07-17
 */
public class FileTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void should_return_a_new_instance_of_file_when_of_method_called() {

		String name = "file_name", path = "file_path";
		byte[] data = "file_data".getBytes();
		File file = File.of(name, path, data);
		Assert.assertNotNull(file);
		Assert.assertSame(name, file.name());
		Assert.assertSame(path, file.path());
		Assert.assertSame(data, file.data());
	}

	@Test
	public void should_return_a_new_instance_of_file_but_its_path_is_not_null_when_of_method_called_but_path_is_null() {

		String name = "file_name", path = null;
		byte[] data = "file_data".getBytes();
		File file = File.of(name, path, data);
		Assert.assertNotNull(file);
		Assert.assertSame(name, file.name());
		Assert.assertSame(StringUtils.EMPTY, file.path());
		Assert.assertSame(data, file.data());
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_name_is_null() {

		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("name_cannot_be_blank");

		String name = null, path = "file_path";
		byte[] data = "file_data".getBytes();
		File.of(name, path, data);
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_name_is_blank() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("name_cannot_be_blank");

		String name = "  ", path = "file_path";
		byte[] data = "file_data".getBytes();
		File.of(name, path, data);
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_data_is_null() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("data_cannot_be_empty");

		String name = "file_name", path = "file_path";
		byte[] data = null;
		File.of(name, path, data);
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_data_is_empty() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("data_cannot_be_empty");

		String name = "file_name", path = "file_path";
		byte[] data = new byte[] {};
		File.of(name, path, data);
	}

	@Test
	public void should_be_same_with_the_fullPath_when_toString_method_called() {

		String name = "file_name", path = "file_path";
		byte[] data = "file_data".getBytes();
		File file = File.of(name, path, data);
		Assert.assertEquals(file.fullPath(), file.toString());
	}

	@Test
	public void should_be_same_with_the_hashCode_of_the_fullPath_when_hashCode_method_called() {

		String name = "file_name", path = "file_path";
		byte[] data = "file_data".getBytes();
		File file = File.of(name, path, data);
		Assert.assertEquals(file.toString().hashCode(), file.hashCode());
	}

	@Test
	public void should_be_false_when_equals_method_called_but_fullPath_are_diffrent() {

		String name = "file_name", path = "file_path";
		byte[] data = "file_data".getBytes();
		File file = File.of(name, path, data);
		Assert.assertFalse(file.equals(null));
		Assert.assertFalse(file.equals(new Object()));
		Assert.assertFalse(file.equals(File.of("name", path, data)));
	}

	@Test
	public void should_be_true_when_equals_method_called_and_fullPath_are_same() {

		String name = "file_name", path = "file_path";
		byte[] data = "file_data".getBytes();
		File file = File.of(name, path, data);
		Assert.assertTrue(file.equals(file));
		Assert.assertTrue(file.equals(File.of(name, path, "data".getBytes())));
	}

	@Test
	public void should_be_false_when_equalTo_method_called_but_fullPath_are_diffrent() {

		String name = "file_name", path = "file_path";
		byte[] data = "file_data".getBytes();
		File file = File.of(name, path, data);
		Assert.assertFalse(file.equalTo(null));
		Assert.assertFalse(file.equalTo(File.of("name", path, data)));
	}

	@Test
	public void should_be_true_when_equalTo_method_called_and_fullPath_are_same() {

		String name = "file_name", path = "file_path";
		byte[] data = "file_data".getBytes();
		File file = File.of(name, path, data);
		Assert.assertTrue(file.equalTo(file));
		Assert.assertTrue(file.equalTo(File.of(name, path, "data".getBytes())));
	}

	@Test
	public void should_be_legal_path_when_fullPath_method_called_and_the_last_character_is_not_slash() {

		String name = "file_name", path = "file_path";
		byte[] data = "file_data".getBytes();
		File file = File.of(name, path, data);
		Assert.assertEquals("file_path/file_name", file.fullPath());
	}

	@Test
	public void should_be_legal_path_when_fullPath_method_called_and_the_last_character_is_slash() {

		String name = "file_name", path = "file_path/";
		byte[] data = "file_data".getBytes();
		File file = File.of(name, path, data);
		Assert.assertEquals("file_path/file_name", file.fullPath());
	}
}