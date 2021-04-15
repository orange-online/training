package cn.deepdraw.training.crawler.novel.crawler.bus.app.domain;

import java.io.Serializable;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterCrawlingEventDTO;

/**
 * NovelChapterCrawlingEventMessage
 * @author huangjiancheng
 * @Date 2020-11-29
 */
public class NovelChapterCrawlingEventMessage implements Serializable {

	private static final long serialVersionUID = 20201129L;

	private String eventId;

	private String novelId;

	private String site;

	private String chapterId;

	private String link;
	
	private NovelChapterCrawlingEventMessage() {}
	
	private NovelChapterCrawlingEventMessage(NovelChapterCrawlingEventDTO event) {
		
		this.setEventId(event.getEventId());
		this.setNovelId(event.getNovelId());
		this.setSite(event.getSite());
		this.setChapterId(event.getChapterId());
		this.setLink(event.getLink());
	}
	
	public static NovelChapterCrawlingEventMessage of(NovelChapterCrawlingEventDTO event) {
		
		return new NovelChapterCrawlingEventMessage(event);
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getNovelId() {
		return novelId;
	}

	public void setNovelId(String novelId) {
		this.novelId = novelId;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
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
		objectNode.put("chapterId", chapterId);
		objectNode.put("link", link);
		return objectNode.toString();
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}
}