package cn.deepdraw.training.novel.api.dto;

import cn.deepdraw.training.framework.api.dto.query.IdEntityQueryDTO;

/**
 * 小说章节搜索请求
 * @author huangjiancheng
 * 2020-07-22
 */
public class NovelChapterPageRequest extends IdEntityQueryDTO {

	private static final long serialVersionUID = 20200722L;

	private Long novelId;

	private String site;
	
	private Long version;

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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}