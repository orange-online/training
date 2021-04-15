package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain;

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

	private String url;

	private Integer sequence;

	private LiudatxtNovelChapter() {}

	private LiudatxtNovelChapter(String name, String url, Integer sequence) {

		this.name = name;
		this.url = url;
		this.sequence = sequence;
	}

	public static LiudatxtNovelChapter of(String name, String url, Integer sequence) {

		if (StringUtils.isBlank(name)
				|| StringUtils.isBlank(url)
				|| sequence == null
				|| sequence <= 0) {

			return null;
		}
		return new LiudatxtNovelChapter(name, url, sequence);
	}

	public String name() {

		return name;
	}

	public String url() {

		return url;
	}

	public Integer sequence() {

		return sequence;
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

		return JsonNodeFactory.instance.objectNode().put("name", name).put("url", url).put("sequence", sequence).toString();
	}

	@Override
	public boolean equalTo(LiudatxtNovelChapter chapter) {

		return equals(chapter);
	}
}