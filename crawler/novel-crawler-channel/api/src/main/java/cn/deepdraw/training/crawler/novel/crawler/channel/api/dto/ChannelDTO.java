package cn.deepdraw.training.crawler.novel.crawler.channel.api.dto;

import cn.deepdraw.training.framework.api.dto.IdEntityDTO;

/**
 * Channel DTO
 * @author huangjiancheng
 * @Date 2021-04-29
 */
public class ChannelDTO extends IdEntityDTO {

	private static final long serialVersionUID = 20210429L;

	private String name;

	private String code;
	
	private String link;
	
	private int timeout;
	
	private boolean available;

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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
}