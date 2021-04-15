package cn.deepdraw.training.crawler.novel.crawler.api.dto;

import java.io.Serializable;

/**
 * @Description 小说基本信息
 * @Author zhangzhucong
 * @Date 2020/6/2
 **/
public class NovelDTO implements Serializable {

    private static final long serialVersionUID = 20200602L;

    private String name;

    private String author;

    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}