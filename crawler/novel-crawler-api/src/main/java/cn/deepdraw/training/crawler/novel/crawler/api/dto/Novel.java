package cn.deepdraw.training.crawler.novel.crawler.api.dto;

import java.io.Serializable;

/**
 * @Description 小说基本信息
 * @Author zhangzhucong
 * @Date 2020/6/2
 **/
public class Novel implements Serializable {

    private static final long serialVersionUID = 20200602L;

    private String name;

    private String author;
    
    private String site;

    private String link;

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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}