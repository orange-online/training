package cn.deepdraw.training.crawler.novel.api.dto;

import cn.deepdraw.training.framework.api.dto.IdEntityDTO;

/**
 * 小说章节DTO
 * @author huangjiancheng
 * 2020-07-22
 */
public class NovelChapterDTO extends IdEntityDTO {

	private static final long serialVersionUID = 20200722L;

	private String novelId;

	private String chapterId;

	private String name;

	private LinkAddress address;

	public String getNovelId() {
		return novelId;
	}

	public void setNovelId(String novelId) {
		this.novelId = novelId;
	}

	public String getChapterId() {
		return chapterId;
	}

	public void setChapterId(String chapterId) {
		this.chapterId = chapterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkAddress getAddress() {
		return address;
	}

	public void setAddress(LinkAddress address) {
		this.address = address;
	}
}