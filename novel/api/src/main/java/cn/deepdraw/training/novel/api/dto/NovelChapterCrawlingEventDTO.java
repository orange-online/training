package cn.deepdraw.training.novel.api.dto;

import cn.deepdraw.training.framework.api.dto.IdEntityDTO;

/**
 * NovelChapterCrawlingEventDTO
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public class NovelChapterCrawlingEventDTO extends IdEntityDTO {

	private static final long serialVersionUID = 20201126L;

	private Long novelId;

	private String site;
	
	private Long version;

	private Long chapterId;

	private String link;
	
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

	public Long getChapterId() {
		return chapterId;
	}

	public void setChapterId(Long chapterId) {
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