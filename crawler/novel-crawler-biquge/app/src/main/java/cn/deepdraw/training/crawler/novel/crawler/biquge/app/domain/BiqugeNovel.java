package cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description 小说基本信息
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
public class BiqugeNovel {

    private String name;

    private String author;

    private String url;

    private String totalChapterNum;

    private BiqugeNovel() {
    }

    private BiqugeNovel(String name, String author, String url) {

        this.name = name;
        this.author = author;
        this.url = url;
    }

    public static BiqugeNovel of() {

        return new BiqugeNovel();
    }

    public static BiqugeNovel of(String name, String author, String url) {

        if (StringUtils.isBlank(name) || StringUtils.isBlank(author) || StringUtils.isBlank(url)) {

            return null;
        }
        return new BiqugeNovel(name, author, url);
    }

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

    public String getTotalChapterNum() {
        return totalChapterNum;
    }

    public void setTotalChapterNum(String totalChapterNum) {
        this.totalChapterNum = totalChapterNum;
    }
}