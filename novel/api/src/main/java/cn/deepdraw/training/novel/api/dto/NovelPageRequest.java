package cn.deepdraw.training.novel.api.dto;

import cn.deepdraw.training.framework.api.dto.query.IdEntityQueryDTO;

/**
 * 小说搜索请求
 * @author huangjiancheng
 * 2020-06-09
 */
public class NovelPageRequest extends IdEntityQueryDTO {

	private static final long serialVersionUID = 20200609L;

	private String name;

	private String author;

	private String site;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
}