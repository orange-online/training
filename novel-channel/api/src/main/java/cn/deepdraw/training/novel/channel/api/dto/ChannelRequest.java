package cn.deepdraw.training.novel.channel.api.dto;

import java.io.Serializable;

/**
 * Channel Create Request
 * @author huangjiancheng
 * @Date 2021-04-29
 */
public class ChannelRequest implements Serializable {

	private static final long serialVersionUID = 20210429L;

	private String name;

	private String code;
	
	private String link;
	
	private int timeout;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
}