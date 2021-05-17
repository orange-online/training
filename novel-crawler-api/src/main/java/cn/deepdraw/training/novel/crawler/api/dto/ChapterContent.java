package cn.deepdraw.training.novel.crawler.api.dto;

import java.io.Serializable;

/**
 * @Description 小说章节内容
 * @Author zhangzhucong
 * @Date 2020/6/2
 **/
public class ChapterContent implements Serializable {

    private static final long serialVersionUID = 20200602L;

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}