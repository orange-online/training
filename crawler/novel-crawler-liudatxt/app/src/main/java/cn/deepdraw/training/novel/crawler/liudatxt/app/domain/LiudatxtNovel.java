package cn.deepdraw.training.novel.crawler.liudatxt.app.domain;

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

	private String link;

	private LiudatxtNovel() {}

	private LiudatxtNovel(String name, String author, String link) {

		this.name = name;
		this.author = author;
		this.link = link;
	}

	public static LiudatxtNovel of() {

		return new LiudatxtNovel();
	}

	public static LiudatxtNovel of(String name, String author, String link) {

		if (StringUtils.isBlank(name)
				|| StringUtils.isBlank(author)
				|| StringUtils.isBlank(link)) {

			return null;
		}
		return new LiudatxtNovel(name, author, link);
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

		return JsonNodeFactory.instance.objectNode().put("name", name).put("author", author).put("link", link).toString();
	}

	@Override
	public boolean equalTo(LiudatxtNovel novel) {

		return equals(novel);
	}
}