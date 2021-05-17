package cn.deepdraw.training.novel.api.dto;

import cn.deepdraw.training.framework.api.dto.IdEntityDTO;

/**
 * @author xujianing
 * @date 2020/12/7
 * 小说打包事件DTO
 */
public class NovelPackagingEventDTO extends IdEntityDTO {

	private static final long serialVersionUID = 1005496065634251588L;

	private Long novelId;

	private String site;
	
	private Long version;

	private boolean published;

	private boolean completed;

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