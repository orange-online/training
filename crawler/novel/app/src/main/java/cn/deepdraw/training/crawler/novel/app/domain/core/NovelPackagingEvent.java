package cn.deepdraw.training.crawler.novel.app.domain.core;

import cn.deepdraw.training.framework.orm.mysql.constants.ColumnDefinitionConstants;
import cn.deepdraw.training.framework.orm.mysql.domain.IdLongEntity;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.persistence.*;

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

	@Column(name = "event_id", columnDefinition = ColumnDefinitionConstants.VARCHAR_45, nullable = false, unique = true)
	private String eventId;

	@Column(name = "novel_id", columnDefinition = ColumnDefinitionConstants.VARCHAR_45, nullable = false)
	private String novelId;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "site", columnDefinition = ColumnDefinitionConstants.TINYINT_1, nullable = false)
	private LinkAddr.Site site;

	@Column(name = "published", columnDefinition = ColumnDefinitionConstants.TINYINT_1, nullable = false)
	private boolean published;

	@Column(name = "completed", columnDefinition = ColumnDefinitionConstants.TINYINT_1, nullable = false)
	private boolean completed;

	private NovelPackagingEvent() {
	}

	private NovelPackagingEvent(String eventId, String novelId, LinkAddr.Site site) {

		this.eventId = eventId;
		this.novelId = novelId;
		this.site = site;
		this.published = false;
		this.completed = false;
	}

	public static NovelPackagingEvent of(String eventId, String novelId, LinkAddr.Site site) {

		return new NovelPackagingEvent(eventId, novelId, site);
	}

	public String eventId() {

		return eventId;
	}

	public String novelId() {

		return novelId;
	}

	public LinkAddr.Site site() {

		return site;
	}

	public String siteString() {

		return site.toString();
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
		objectNode.put("eventId", eventId);
		objectNode.put("novelId", novelId);
		objectNode.put("site", siteString());
		return objectNode.toString();
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}
}
