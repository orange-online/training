package cn.deepdraw.training.storage.app.domain.core;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import cn.deepdraw.training.storage.app.domain.shared.ValueObject;

/**
 * 文件
 * @author huangjiancheng
 * 2020-07-16
 */
public class File implements ValueObject<File> {

	private static final long serialVersionUID = 20200716L;

	private String name;

	private String path;

	private byte[] data;

	private File() {}

	private File(String name, String path, byte[] data) {

		Validate.isTrue(ArrayUtils.isNotEmpty(data), "data_cannot_be_empty");
		this.name = Validate.notBlank(name, "name_cannot_be_blank");
		this.path = StringUtils.defaultString(path);
		this.data = data;
	}

	public static File of(String name, String path, byte[] data) {

		return new File(name, path, data);
	}

	public String name() {

		return name;
	}

	public String path() {

		return path;
	}

	public byte[] data() {

		return data;
	}

	public String fullPath() {

		return (path.endsWith("/") ? path : path + "/") + name;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null) {

			return false;
		} else if (this == obj) {

			return true;
		} else if (obj instanceof File) {

			return equalsFile((File) obj);
		} else {

			return false;
		}
	}
	
	private boolean equalsFile(File file) {

		return StringUtils.equals(this.toString(), file.toString());
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}

	@Override
	public String toString() {

		return fullPath();
	}

	@Override
	public boolean equalTo(File file) {

		return equals(file);
	}
}