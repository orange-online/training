package cn.deepdraw.training.novel.crawler.biquge.app.domain;

import org.apache.commons.lang3.StringUtils;

/**
 * @Description 小说基本信息
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
public class BiqugeNovel {

    private String name;

    private String author;

    private String link;

    private String totalChapterNum;

    private BiqugeNovel() {
    }

    private BiqugeNovel(String name, String author, String link) {

        this.name = name;
        this.author = author;
        this.link = link;
    }

    public static BiqugeNovel of() {

        return new BiqugeNovel();
    }

    public static BiqugeNovel of(String name, String author, String link) {

        if (StringUtils.isBlank(name) || StringUtils.isBlank(author) || StringUtils.isBlank(link)) {

            return null;
        }
        return new BiqugeNovel(name, author, link);
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTotalChapterNum() {
        return totalChapterNum;
    }

    public void setTotalChapterNum(String totalChapterNum) {
        this.totalChapterNum = totalChapterNum;
    }
}