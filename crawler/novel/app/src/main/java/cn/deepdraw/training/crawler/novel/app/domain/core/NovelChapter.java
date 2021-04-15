package cn.deepdraw.training.crawler.novel.app.domain.core;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.framework.orm.mysql.constants.ColumnDefinitionConstants;
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
	@JoinColumn(name = "novel_entity_id", columnDefinition = ColumnDefinitionConstants.BIGINT20, nullable = false)
	private Novel novel;

	@Column(name = "chapter_id", columnDefinition = ColumnDefinitionConstants.VARCHAR_45, nullable = false, unique = true)
	private String chapterId;

	@Column(name = "name", columnDefinition = ColumnDefinitionConstants.VARCHAR_45, nullable = false)
	private String name;

	@Embedded
	private LinkAddr addr;

	private NovelChapter() {}

	private NovelChapter(Novel novel, String chapterId, String name, LinkAddr addr) {

		this.novel = Validate.notNull(novel, "novel_id_cannot_be_null");
		this.chapterId = Validate.notBlank(chapterId, "chapter_id_cannot_be_blank");
		this.name = Validate.notBlank(name, "name_cannot_be_blank");
		this.addr = Validate.notNull(addr, "addr_cannot_be_null");
	}

	public static NovelChapter of(Novel novel, String chapterId, String name, LinkAddr addr) {

		return new NovelChapter(novel, chapterId, name, addr);
	}

	public Novel novel() {

		return novel;
	}

	public String chapterId() {

		return chapterId;
	}

	public String name() {

		return name;
	}

	public LinkAddr addr() {

		return addr;
	}

	public Site site() {

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