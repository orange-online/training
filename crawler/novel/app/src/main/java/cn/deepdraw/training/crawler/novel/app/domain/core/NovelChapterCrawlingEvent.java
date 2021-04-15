package cn.deepdraw.training.crawler.novel.app.domain.core;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.framework.orm.mysql.constants.ColumnDefinitionConstants;
import cn.deepdraw.training.framework.orm.mysql.domain.IdLongEntity;

/**
 * NovelChapter Crawling Event
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@Entity
@Table(name = "novel_chapter_crawling_event")
@Cacheable
public class NovelChapterCrawlingEvent extends IdLongEntity {

	private static final long serialVersionUID = 20201126L;

	@Column(name = "event_id", columnDefinition = ColumnDefinitionConstants.VARCHAR_45, nullable = false, unique = true)
	private String eventId;

	@Column(name = "novel_id", columnDefinition = ColumnDefinitionConstants.VARCHAR_45, nullable = false)
	private String novelId;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "site", columnDefinition = ColumnDefinitionConstants.TINYINT_1, nullable = false)
	private Site site;

	@Column(name = "chapter_id", columnDefinition = ColumnDefinitionConstants.VARCHAR_45, nullable = false)
	private String chapterId;

	@Column(name = "link", columnDefinition = ColumnDefinitionConstants.VARCHAR_255, nullable = false)
	private String link;
	
	@Column(name = "published", columnDefinition = ColumnDefinitionConstants.TINYINT_1, nullable = false)
	private boolean published;
	
	@Column(name = "completed", columnDefinition = ColumnDefinitionConstants.TINYINT_1, nullable = false)
	private boolean completed;

	private NovelChapterCrawlingEvent() {}

	private NovelChapterCrawlingEvent(String eventId, String novelId, Site site, String chapterId, String link) {

		this.eventId = Validate.notBlank(eventId, "event_id_cannot_be_blank");
		this.novelId = Validate.notBlank(novelId, "novel_id_cannot_be_blank");
		this.site = Validate.notNull(site, "site_cannot_be_blank");
		this.chapterId = Validate.notBlank(chapterId, "chapter_id_cannot_be_blank");
		this.link = Validate.notBlank(link, "link_cannot_be_blank");
		this.published = false;
		this.completed = false;
	}

	public static NovelChapterCrawlingEvent of(String eventId, String novelId, Site site, String chapterId, String link) {

		return new NovelChapterCrawlingEvent(eventId, novelId, site, chapterId, link);
	}

	public String eventId() {
		
		return eventId;
	}

	public String novelId() {
		
		return novelId;
	}

	public Site site() {
		
		return site;
	}

	public String siteString() {
		
		return site.toString();
	}

	public String chapterId() {
		
		return chapterId;
	}

	public String link() {
		
		return link;
	}

	public boolean published() {
		
		return published;
	}

	public NovelChapterCrawlingEvent publish() {

		this.published = true;
		return this;
	}

	public boolean completed() {
		
		return completed;
	}

	public NovelChapterCrawlingEvent complete() {

		this.completed = true;
		return this;
	}

	@Override
	public String toString() {

		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode.put("eventId", eventId);
		objectNode.put("novelId", novelId);
		objectNode.put("site", siteString());
		objectNode.put("chapterId", chapterId);
		objectNode.put("link", link);
		return objectNode.toString();
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}
}