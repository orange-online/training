package cn.deepdraw.training.novel.crawler.biquge.app.domain;

/**
 * @Description 小说基本信息
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
public class BiqugeNovelChapter {

    private String subTitle;

    private String name;

    private String link;

    private Integer index;

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