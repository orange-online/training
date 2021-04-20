package cn.deepdraw.training.crawler.novel.api.dto;

import cn.deepdraw.training.framework.api.dto.query.IdEntityQueryDTO;

/**
 * 小说章节DTO
 * @author huangjiancheng
 * 2020-07-22
 */
public class NovelChapterQueryDTO extends IdEntityQueryDTO {

	private static final long serialVersionUID = 20200722L;

	private Long novelId;

	private String site;

	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}