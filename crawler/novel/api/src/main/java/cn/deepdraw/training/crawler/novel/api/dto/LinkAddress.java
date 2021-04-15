package cn.deepdraw.training.crawler.novel.api.dto;

import java.io.Serializable;

/**
 * 链接地址
 * @author huangjiancheng
 * 2020-06-09
 */
public class LinkAddress implements Serializable {

	private static final long serialVersionUID = 20200609L;

	private String site; // 站点编码

	private String link; // 站点链接

	private String path; // 服务器地址

	public LinkAddress() {}

	private LinkAddress(String site, String link) {

		this.setSite(site);
		this.setLink(link);
	}

	private LinkAddress(String site, String link, String path) {

		this.setSite(site);
		this.setLink(link);
		this.setPath(path);
	}

	public static LinkAddress of(String site, String link) {

		return new LinkAddress(site, link);
	}

	public static LinkAddress of(String site, String link, String path) {

		return new LinkAddress(site, link, path);
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}