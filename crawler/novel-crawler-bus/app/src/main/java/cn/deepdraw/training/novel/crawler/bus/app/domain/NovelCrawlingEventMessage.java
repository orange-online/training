package cn.deepdraw.training.novel.crawler.bus.app.domain;

import java.io.Serializable;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * NovelCrawlingEventMessage
 * @author huangjiancheng
 * @Date 2020-11-29
 */
public class NovelCrawlingEventMessage implements Serializable {

	private static final long serialVersionUID = 20201129L;

	private Long eventId;

	private Long novelId;

	private String site;
	
	private Long version;

	private String link;

	public Long getEventId() {
		return eventId;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getNovelId() {
		return novelId;
	}

	public void setNovelId(Long novelId) {
		this.novelId = novelId;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {

		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode.put("eventId", eventId);
		objectNode.put("novelId", novelId);
		objectNode.put("site", site);
		objectNode.put("version", version);
		objectNode.put("link", link);
		return objectNode.toString();
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}
}