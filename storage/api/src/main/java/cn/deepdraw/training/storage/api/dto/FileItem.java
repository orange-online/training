package cn.deepdraw.training.storage.api.dto;

import java.io.Serializable;

/**
 * 文件项目
 * @author huangjiancheng
 * 2020-07-16
 */
public class FileItem implements Serializable {

	private static final long serialVersionUID = 20200716L;

	/**
	 * 存储数据内容
	 */
	private byte[] data;

	/**
	 * 定制的相对路径
	 */
	private String path;

	/**
	 * 定制的存储名称
	 */
	private String name;

	private FileItem() {}

	private FileItem(String name, String path, byte[] data) {

		this.setName(name);
		this.setPath(path);
		this.setData(data);
	}

	public static FileItem of(String name, String path, byte[] data) {

		return new FileItem(name, path, data);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}