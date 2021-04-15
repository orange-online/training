package cn.deepdraw.training.crawler.storage.api.dto;

/**
 * Resource Data
 * @author huangjiancheng
 * @Date 2021-02-22
 */
public class ResourceData extends Resource {

	private static final long serialVersionUID = 20210222L;

	private byte[] data;

	private ResourceData() {}

	private ResourceData(String prefix, String path, byte[] data) {

		super(prefix, path);
		this.data = data;
	}

	public static ResourceData of(String prefix, String path, byte[] data) {

		return new ResourceData(prefix, path, data);
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}