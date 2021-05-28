package cn.deepdraw.training.novel.crawler.api.dto;

import java.io.Serializable;

/**
 * @Description 小说章节信息
 * @Author zhangzhucong
 * @Date 2020/6/2
 **/
public class Chapter implements Serializable {

    private static final long serialVersionUID = 20200602L;

    private String name;
    
    private String site;

    private String link;

    private Integer index;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
}