package cn.deepdraw.training.novel.crawler.bus.app.domain;

import java.io.Serializable;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * NovelPackagingEventMessage
 * @author huangjiancheng
 * @Date 2021-02-22
 */
public class NovelPackagingEventMessage implements Serializable {

	private static final long serialVersionUID = 20210222L;

	private Long eventId;

	private Long novelId;

	private String site;
	
	private Long version;

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

	@Override
	public String toString() {

		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode.put("eventId", eventId);
		objectNode.put("novelId", novelId);
		objectNode.put("site", site);
		objectNode.put("version", version);
		return objectNode.toString();
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}
}