package cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * 职责: 保存小说章节相关信息
 * @author：杨攀
 * @date：2020年7月23日 上午9:19:27
 */
public class XuanshuwangChapter implements Serializable {

    private static final long serialVersionUID = 8150202637185518285L;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 章节地址
     */
    private String link;

    /**
     * 章节排序号
     */
    private int index;

    protected XuanshuwangChapter() {}

    public XuanshuwangChapter(String name, String link, int index) {

        Validate.notBlank(name, "chapter_name_cannot_be_blank");
        Validate.notBlank(link, "chapter_link_cannot_be_blank");
        
        this.name = name;
        this.link = link;
        this.index = index;
    }

    public String name() {
        return name;
    }

    public String link() {
        return link;
    }

    public int index() {
        return index;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {

            return false;
        } else if (this == obj) {

            return true;
        } else if (obj instanceof XuanshuwangChapter) {

            return equals((XuanshuwangChapter) obj);
        } else {

            return false;
        }
    }

    protected <T extends XuanshuwangChapter> boolean equals(T obj) {

        return obj != null && (this == obj || StringUtils.equals(this.toString(), obj.toString()));
    }

    @Override
    public int hashCode() {

        return this.toString().hashCode();
    }

    @Override
    public String toString() {

        return JsonNodeFactory.instance.objectNode().put("name", name).put("index", index).put("link", link).toString();
    }
}