package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * 小说
 * @author huangjiancheng
 * 2020-06-06
 */
public class LiudatxtNovel implements ValueObject<LiudatxtNovel> {

	private static final long serialVersionUID = 20200607L;

	private String name;

	private String author;

	private String url;

	private LiudatxtNovel() {}

	private LiudatxtNovel(String name, String author, String url) {

		this.name = name;
		this.author = author;
		this.url = url;
	}

	public static LiudatxtNovel of() {

		return new LiudatxtNovel();
	}

	public static LiudatxtNovel of(String name, String author, String url) {

		if (StringUtils.isBlank(name)
				|| StringUtils.isBlank(author)
				|| StringUtils.isBlank(url)) {

			return null;
		}
		return new LiudatxtNovel(name, author, url);
	}

	public String name() {

		return name;
	}

	public String author() {

		return author;
	}

	public String url() {

		return url;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj == null) {

			return false;
		} else if (this == obj) {

			return true;
		} else if (obj instanceof LiudatxtNovel) {

			return equals((LiudatxtNovel) obj);
		} else {

			return false;
		}
	}
	
	protected <T extends LiudatxtNovel> boolean equals(T obj) {

		return obj != null && (this == obj || StringUtils.equals(this.toString(), obj.toString()));
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}

	@Override
	public String toString() {

		return JsonNodeFactory.instance.objectNode().put("name", name).put("author", author).put("url", url).toString();
	}

	@Override
	public boolean equalTo(LiudatxtNovel novel) {

		return equals(novel);
	}
}