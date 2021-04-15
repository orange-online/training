package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * 溜达小说章节内容
 * @author huangjiancheng
 * 2020-06-06
 */
public class LiudatxtNovelChapterContent implements ValueObject<LiudatxtNovelChapterContent> {

	private static final long serialVersionUID = 20200607L;

	private String content;

	private LiudatxtNovelChapterContent() {}

	private LiudatxtNovelChapterContent(String content) {

		this.content = content;
	}

	public static LiudatxtNovelChapterContent of(String content) {

		if (StringUtils.isBlank(content)) {

			return null;
		}
		return new LiudatxtNovelChapterContent(content);
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
		} else if (obj instanceof LiudatxtNovelChapterContent) {

			return equals((LiudatxtNovelChapterContent) obj);
		} else {

			return false;
		}
	}
	
	protected <T extends LiudatxtNovelChapterContent> boolean equals(T obj) {

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

	@Override
	public boolean equalTo(LiudatxtNovelChapterContent chapterContent) {

		return equals(chapterContent);
	}

}