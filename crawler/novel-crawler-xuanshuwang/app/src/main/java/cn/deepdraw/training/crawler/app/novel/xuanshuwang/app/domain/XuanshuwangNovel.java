package cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * 职责: 选书网信息
 * 
 * @author：杨攀
 * @date：2020年6月22日 下午8:49:42
 */
public class XuanshuwangNovel implements Serializable {

    private static final long serialVersionUID = 202007211425L;

    private String name;

    private String author;

    private String link;

    private XuanshuwangNovel() {}

    private XuanshuwangNovel(String name, String author, String link) {

        this.name = name;
        this.author = author;
        this.link = link;
    }

    public static XuanshuwangNovel of() {

    	return new XuanshuwangNovel();
	}

    public static XuanshuwangNovel of(String name, String author, String link) {

		if (StringUtils.isBlank(name) || StringUtils.isBlank(author) || StringUtils.isBlank(link)) {

			return null;
		}
		return new XuanshuwangNovel(name, author, link);
	}

    public String name() {
        return name;
    }

    public String author() {
        return author;
    }

    public String link() {
        return link;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {

            return false;
        } else if (this == obj) {

            return true;
        } else if (obj instanceof XuanshuwangNovel) {

            return equals((XuanshuwangNovel) obj);
        } else {

            return false;
        }
    }

    protected <T extends XuanshuwangNovel> boolean equals(T obj) {

        return obj != null && (this == obj || StringUtils.equals(this.toString(), obj.toString()));
    }

    @Override
    public int hashCode() {

        return this.toString().hashCode();
    }

    @Override
    public String toString() {

        return JsonNodeFactory.instance.objectNode().put("name", name).put("author", author).put("link", link).toString();
    }
}