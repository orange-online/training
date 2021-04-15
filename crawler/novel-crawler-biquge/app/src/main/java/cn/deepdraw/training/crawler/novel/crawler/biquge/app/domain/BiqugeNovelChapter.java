package cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain;

/**
 * @Description 小说基本信息
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
public class BiqugeNovelChapter {

    private String subTitle;

    private String name;

    private String url;

    private Integer sequence;

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

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