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
import cn.deepdraw.training.framework.orm.mysql.domain.IdLongEntity;

/**
 * Novel Crawling Event
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@Entity
@Table(name = "novel_crawling_event")
@Cacheable
public class NovelCrawlingEvent extends IdLongEntity {

	private static final long serialVersionUID = 20201126L;

	@Column(name = "novel_id")
	private Long novelId;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "site")
	private Site site;

	@Column(name = "link")
	private String link;
	
	@Column(name = "published")
	private boolean published;
	
	@Column(name = "completed")
	private boolean completed;

	private NovelCrawlingEvent() {}

	private NovelCrawlingEvent(Long novelId, Site site, String link) {

		this.novelId = Validate.notNull(novelId, "novel_id_cannot_be_null");
		this.site = Validate.notNull(site, "site_cannot_be_blank");
		this.link = Validate.notBlank(link, "link_cannot_be_blank");
		this.published = false;
		this.completed = false;
	}

	public static NovelCrawlingEvent of(Long novelId, Site site, String link) {

		return new NovelCrawlingEvent(novelId, site, link);
	}

	public Long novelId() {
		
		return novelId;
	}

	public Site site() {
		
		return site;
	}

	public String siteString() {
		
		return site.toString();
	}

	public String link() {
		
		return link;
	}

	public boolean published() {
		
		return published;
	}

	public NovelCrawlingEvent publish() {

		this.published = true;
		return this;
	}

	public boolean completed() {
		
		return completed;
	}

	public NovelCrawlingEvent complete() {

		this.completed = true;
		return this;
	}

	@Override
	public String toString() {

		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode.put("eventId", entityId());
		objectNode.put("novelId", novelId);
		objectNode.put("site", siteString());
		objectNode.put("link", link);
		return objectNode.toString();
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}
}