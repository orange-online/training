package cn.deepdraw.training.novel.api.dto;

import cn.deepdraw.training.framework.api.dto.IdEntityDTO;

/**
 * 小说章节DTO
 * @author huangjiancheng
 * 2020-07-22
 */
public class NovelChapterDTO extends IdEntityDTO {

	private static final long serialVersionUID = 20200722L;

	private Long novelId;

	private String name;

	private LinkAddress address;
	
	private Integer index;

	public Long getNovelId() {
		return novelId;
	}

	public void setNovelId(Long novelId) {
		this.novelId = novelId;
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

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
}