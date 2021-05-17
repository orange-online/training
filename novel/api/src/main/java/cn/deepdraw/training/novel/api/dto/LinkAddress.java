package cn.deepdraw.training.novel.api.dto;

import java.io.Serializable;

/**
 * 链接地址
 * @author huangjiancheng
 * 2020-06-09
 */
public class LinkAddress implements Serializable {

	private static final long serialVersionUID = 20200609L;

	private String site; // 站点编码
	
	private Long version; // 版本

	private String link; // 站点链接

	private String path; // 服务器地址

	public LinkAddress() {}

	private LinkAddress(String site, Long version, String link) {

		this.setSite(site);
		this.setVersion(version);
		this.setLink(link);
	}

	private LinkAddress(String site, Long version, String link, String path) {

		this.setSite(site);
		this.setVersion(version);
		this.setLink(link);
		this.setPath(path);
	}

	public static LinkAddress of(String site, Long version, String link) {

		return new LinkAddress(site, version, link);
	}

	public static LinkAddress of(String site, Long version, String link, String path) {

		return new LinkAddress(site, version, link, path);
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
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