package cn.deepdraw.training.novel.crawler.xuanshuwang.app.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * 职责: 保存选书网小说章节内容信息
 * @author：杨攀
 * @date：2020年7月24日 下午2:11:28
 */
public class XuanshuwangChapterContent implements Serializable {

    private static final long serialVersionUID = 202007241412L;

    /**
     * 小说内容
     */
    private String content;

    protected XuanshuwangChapterContent() {}

    public XuanshuwangChapterContent(String content) {

        this.content = content;
    }

    public String content() {
        return content;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {

            return false;
        } else if (this == obj) {

            return true;
        } else if (obj instanceof XuanshuwangChapterContent) {

            return equals((XuanshuwangChapterContent) obj);
        } else {

            return false;
        }
    }

    protected <T extends XuanshuwangChapterContent> boolean equals(T obj) {

        return obj != null && (this == obj || StringUtils.equals(this.toString(), obj.toString()));
    }

    @Override
    public int hashCode() {

        return this.toString().hashCode();
    }

    @Override
    public String toString() {

        return JsonNodeFactory.instance.objectNode().put("content", content).toString();
    }
}