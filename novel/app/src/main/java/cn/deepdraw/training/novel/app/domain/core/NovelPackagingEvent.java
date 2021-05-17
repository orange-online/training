package cn.deepdraw.training.novel.app.domain.core;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.deepdraw.training.framework.orm.mysql.domain.IdLongEntity;

/**
 * @author xujianing
 * @date 2020/12/23
 * Novel Packaging Event
 */
@Entity
@Table(name = "novel_packaging_event")
@Cacheable
public class NovelPackagingEvent extends IdLongEntity {

	private static final long serialVersionUID = 963574916302359182L;

	@Column(name = "novel_id")
	private Long novelId;

	@Column(name = "site")
	private String site;

	@Column(name = "version")
	private Long version;

	@Column(name = "published")
	private boolean published;

	@Column(name = "completed")
	private boolean completed;

	private NovelPackagingEvent() {
	}

	private NovelPackagingEvent(Long novelId, String site, Long version) {

		this.novelId = novelId;
		this.site = site;
		this.version = version;
		this.published = false;
		this.completed = false;
	}

	public static NovelPackagingEvent of(Long novelId, String site, Long version) {

		return new NovelPackagingEvent(novelId, site, version);
	}

	public Long novelId() {

		return novelId;
	}

	public String site() {

		return site;
	}

	public Long version() {

		return version;
	}

	public boolean published() {

		return published;
	}

	public NovelPackagingEvent publish() {

		this.published = true;
		return this;
	}

	public boolean completed() {

		return completed;
	}

	public NovelPackagingEvent complete() {

		this.completed = true;
		return this;
	}

	@Override
	public String toString() {

		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode.put("eventId", entityId());
		objectNode.put("novelId", novelId());
		objectNode.put("site", site());
		objectNode.put("version", version());
		return objectNode.toString();
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}
}
