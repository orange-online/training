package cn.deepdraw.training.novel.crawler.liudatxt.app.domain;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * 溜达小说章节
 * @author huangjiancheng
 * 2020-06-06
 */
public class LiudatxtNovelChapter implements ValueObject<LiudatxtNovelChapter> {

	private static final long serialVersionUID = 20200607L;

	private String name;

	private String link;

	private Integer index;

	private LiudatxtNovelChapter() {}

	private LiudatxtNovelChapter(String name, String link, Integer index) {

		this.name = name;
		this.link = link;
		this.index = index;
	}

	public static LiudatxtNovelChapter of(String name, String link, Integer index) {

		if (StringUtils.isBlank(name)
				|| StringUtils.isBlank(link)
				|| index == null
				|| index <= 0) {

			return null;
		}
		return new LiudatxtNovelChapter(name, link, index);
	}

	public String name() {

		return name;
	}

	public String link() {

		return link;
	}

	public Integer index() {

		return index;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj == null) {

			return false;
		} else if (this == obj) {

			return true;
		} else if (obj instanceof LiudatxtNovelChapter) {

			return equals((LiudatxtNovelChapter) obj);
		} else {

			return false;
		}
	}
	
	protected <T extends LiudatxtNovelChapter> boolean equals(T obj) {

		return obj != null && (this == obj || StringUtils.equals(this.toString(), obj.toString()));
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}

	@Override
	public String toString() {

		return JsonNodeFactory.instance.objectNode().put("name", name).put("link", link).put("index", index).toString();
	}

	@Override
	public boolean equalTo(LiudatxtNovelChapter chapter) {

		return equals(chapter);
	}
}