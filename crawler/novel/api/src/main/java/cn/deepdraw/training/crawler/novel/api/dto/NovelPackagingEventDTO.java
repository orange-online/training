package cn.deepdraw.training.crawler.novel.api.dto;

import cn.deepdraw.training.framework.api.dto.IdEntityDTO;

/**
 * @author xujianing
 * @date 2020/12/7
 * 小说打包事件DTO
 */
public class NovelPackagingEventDTO extends IdEntityDTO {

	private static final long serialVersionUID = 1005496065634251588L;

	private String eventId;

	private String novelId;

	private String site;

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
