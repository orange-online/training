package cn.deepdraw.training.crawler.novel.app.domain.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.framework.orm.mysql.constants.ColumnDefinitionConstants;
import cn.deepdraw.training.framework.orm.mysql.domain.IdLongEntity;

/**
 * 小说
 * @author huangjiancheng
 * 2020-06-09
 */
@Entity
@Table(name = "novel")
@Cacheable
public class Novel extends IdLongEntity {

	private static final long serialVersionUID = 20200609L;

	@Column(name = "novel_id", columnDefinition = ColumnDefinitionConstants.VARCHAR_45, nullable = false, unique = true)
	private String novelId;

	@Column(name = "name", columnDefinition = ColumnDefinitionConstants.VARCHAR_45, nullable = false)
	private String name;

	@Column(name = "author", columnDefinition = ColumnDefinitionConstants.VARCHAR_45, nullable = false)
	private String author;

	@ElementCollection
	@CollectionTable(name = "novel_addrs", joinColumns = @JoinColumn(name = "entity_id"))
	private List<LinkAddr> addrs;

	private Novel() {}

	private Novel(String novelId, String name, String author, LinkAddr addr) {

		this.novelId = Validate.notBlank(novelId, "novelId_cannot_be_blank");
		this.name = Validate.notBlank(name, "name_cannot_be_blank");
		this.author = Validate.notBlank(author, "author_cannot_be_blank");
		this.addrs = new ArrayList<>(Arrays.asList(Validate.notNull(addr, "addr_cannot_be_null")));
	}

	public static Novel of(String novelId, String name, String author, LinkAddr addr) {

		return new Novel(novelId, name, author, addr);
	}

	public String novelId() {

		return novelId;
	}

	public String name() {

		return name;
	}

	public String author() {

		return author;
	}

	public List<LinkAddr> addrs() {

		return addrs;
	}

	public LinkAddr addrOf(Site site) {

		return addrs.stream().filter(addr -> addr.site() == site).findFirst().orElse(null);
	}

	public Novel updateAddr(LinkAddr addr) {

		LinkAddr addrOf = addrOf(addr.site());
		if (addrOf == null) {

			addrs.add(addr);
		} else {

			addrOf.update(addr);
		}
		return this;
	}

	public Novel updateAddrPath(Site site, String path) {

		Validate.notNull(addrOf(site), "site_not_found").updatePath(path);
		return this;
	}
}