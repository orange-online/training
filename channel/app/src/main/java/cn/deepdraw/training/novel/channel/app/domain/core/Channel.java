package cn.deepdraw.training.novel.channel.app.domain.core;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import cn.deepdraw.training.framework.orm.mysql.domain.IdLongEntity;

/**
 * 小说
 * @author huangjiancheng
 * 2020-06-09
 */
@Entity
@Table(name = "channel")
@Cacheable
public class Channel extends IdLongEntity {

	private static final long serialVersionUID = 20210430L;

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "link")
	private String link;

	@Column(name = "timeout")
	private int timeout;

	@Column(name = "available")
	private boolean available;

	private Channel() {}

	private Channel(String name, String code, String link, int timeout) {

		this.name = Validate.notBlank(name, "name_cannot_be_blank");
		this.code = Validate.notBlank(code, "code_cannot_be_blank");
		this.link = Validate.notBlank(link, "link_cannot_be_blank");
		Validate.inclusiveBetween(0, 60000, timeout, "timeout_should_between_0_and_6000");
		this.timeout = timeout;
		this.available = false;
	}

	public static Channel of(String name, String code, String link, int timeout) {

		return new Channel(name, code, link, timeout);
	}

	public String name() {

		return name;
	}

	public String code() {

		return code;
	}

	public String link() {

		return link;
	}

	public int timeout() {

		return timeout;
	}

	public boolean available() {

		return available;
	}
	
	public Channel updateAvailable(boolean available) {
		
		this.available = available;
		return this;
	}

	public Channel update(Channel channel) {
		
		this.name = channel.name();
		this.code = channel.code();
		this.link = channel.link();
		this.timeout = channel.timeout();
		return this;
	}
}