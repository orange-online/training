package cn.deepdraw.training.crawler.novel.api.dto;

import cn.deepdraw.training.framework.api.dto.IdEntityDTO;

/**
 * NovelChapterCrawlingEventDTO
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public class NovelChapterCrawlingEventDTO extends IdEntityDTO {

	private static final long serialVersionUID = 20201126L;

	private String eventId;

	private String novelId;

	private String site;

	private String chapterId;

	private String link;
	
	private boolean published;
	
	private boolean completed;

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

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}