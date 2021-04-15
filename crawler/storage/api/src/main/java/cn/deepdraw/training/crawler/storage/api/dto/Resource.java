package cn.deepdraw.training.crawler.storage.api.dto;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * 存储信息
 * @author huangjiancheng
 * 2020-07-16
 */
public class Resource implements Serializable {

	private static final long serialVersionUID = 20200716L;

	/**
	 * 存储相对路径
	 */
	private String path;

	/**
	 * （URL）前缀
	 */
	private String prefix;

	protected Resource() {}

	protected Resource(String prefix, String path) {

		this.setPrefix(prefix);
		this.setPath(path);
	}

	public static Resource of(String prefix, String path) {

		return new Resource(prefix, path);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String name() {

		int lastIndexOf = this.path.lastIndexOf("/");
		return lastIndexOf + 1 < this.path.length() ? this.path.substring(lastIndexOf + 1) : StringUtils.EMPTY;
	}

	public String url() {

		return (this.prefix.endsWith("/") ? this.prefix : this.prefix + "/") + this.path;
	}
}