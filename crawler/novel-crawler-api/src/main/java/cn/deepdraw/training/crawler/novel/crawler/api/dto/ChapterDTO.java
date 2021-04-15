package cn.deepdraw.training.crawler.novel.crawler.api.dto;

import java.io.Serializable;

/**
 * @Description 小说章节信息
 * @Author zhangzhucong
 * @Date 2020/6/2
 **/
public class ChapterDTO implements Serializable {

    private static final long serialVersionUID = 20200602L;

    private String name;

    private String url;

    private Integer sequence;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}