package cn.deepdraw.training.crawler.novel.app.domain.core;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import cn.deepdraw.training.crawler.novel.app.domain.shared.ValueObject;
import cn.deepdraw.training.framework.orm.mysql.constants.ColumnDefinitionConstants;

/**
 * 链接
 * @author huangjiancheng
 * 2020-06-09
 */
@Embeddable
public class LinkAddr implements ValueObject<LinkAddr> {

	private static final long serialVersionUID = 20200609L;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "site", columnDefinition = ColumnDefinitionConstants.TINYINT_1, nullable = false)
	private Site site; // 站点

	@Column(name = "link", columnDefinition = ColumnDefinitionConstants.VARCHAR_255, nullable = false)
	private String link; // 站点链接

	@Column(name = "path", columnDefinition = ColumnDefinitionConstants.VARCHAR_255, nullable = true)
	private String path; // 服务器地址

	private LinkAddr() {}

	private LinkAddr(Site site, String link, String path) {

		this.site = Validate.notNull(site, "site_cannot_be_null");
		this.link = Validate.notBlank(link, "link_cannot_be_blank");
		this.path = path;
	}

	public static LinkAddr of(Site site, String link, String path) {

		return new LinkAddr(site, link, path);
	}

	public Site site() {

		return site;
	}

	public String link() {

		return link;
	}

	public String path() {

		return path;
	}

	public LinkAddr update(LinkAddr link) {

		Validate.isTrue(this.site() == link.site(), "site_illegal");
		this.link = link.link();
		this.path = link.path();
		return this;
	}

	public LinkAddr updatePath(String path) {

		this.path = Validate.notBlank(path, "path_cannot_be_blank");
		return this;
	}
	
	@Override
	public boolean equals(Object obj) {

		if (obj == null) {

			return false;
		} else if (this == obj) {

			return true;
		} else if (obj instanceof LinkAddr) {

			return equals((LinkAddr) obj);
		} else {

			return false;
		}
	}
	
	protected <T extends LinkAddr> boolean equals(T obj) {

		return obj != null && (this == obj || StringUtils.equals(this.toString(), obj.toString()));
	}

	@Override
	public int hashCode() {

		return this.toString().hashCode();
	}

	@Override
	public String toString() {

		return JsonNodeFactory.instance.objectNode().put("site", site.toString()).put("link", link).put("path", path).toString();
	}

	@Override
	public boolean equalTo(LinkAddr link) {

		return equals(link);
	}

	/**
	 * 小说站点
	 * @author huangjiancheng
	 * 2020-06-09
	 */
	public enum Site {

		BIQUGE, XUANSHUWANG, LIUDATXT
	}
}