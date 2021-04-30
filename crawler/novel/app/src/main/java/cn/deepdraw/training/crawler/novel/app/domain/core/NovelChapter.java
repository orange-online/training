package cn.deepdraw.training.crawler.novel.app.domain.core;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import cn.deepdraw.training.framework.orm.mysql.domain.IdLongEntity;

/**
 * 小说章节
 * @author huangjiancheng
 * 2020-07-22
 */
@Entity
@Table(name = "novel_chapter")
@Cacheable
public class NovelChapter extends IdLongEntity {

	private static final long serialVersionUID = 20200722L;

	@ManyToOne
	@JoinColumn(name = "novel_id")
	private Novel novel;

	@Column(name = "name")
	private String name;

	@Embedded
	private LinkAddr addr;

	@Column(name = "idx")
	private Integer index;

	private NovelChapter() {}

	private NovelChapter(Novel novel, String name, LinkAddr addr, Integer index) {

		this.novel = Validate.notNull(novel, "novel_id_cannot_be_null");
		this.name = Validate.notBlank(name, "name_cannot_be_blank");
		this.addr = Validate.notNull(addr, "addr_cannot_be_null");
		this.index = Validate.notNull(index, "index_cannot_be_null");
	}

	public static NovelChapter of(Novel novel, String name, LinkAddr addr, Integer index) {

		return new NovelChapter(novel, name, addr, index);
	}

	public Novel novel() {

		return novel;
	}

	public String name() {

		return name;
	}

	public LinkAddr addr() {

		return addr;
	}
	
	public Integer index() {

		return index;
	}

	public String site() {

		return addr.site();
	}

	public String link() {

		return addr.link();
	}

	public String path() {

		return addr.path();
	}

	public NovelChapter updateAddrPath(String path) {

		addr.updatePath(path);
		return this;
	}
}