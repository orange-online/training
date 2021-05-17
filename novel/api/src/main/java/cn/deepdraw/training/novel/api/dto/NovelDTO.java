package cn.deepdraw.training.novel.api.dto;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import cn.deepdraw.training.framework.api.dto.IdEntityDTO;

/**
 * 
 * @author huangjiancheng
 * 2020-06-09
 */
public class NovelDTO extends IdEntityDTO {

	private static final long serialVersionUID = 20200609L;

	private String name;

	private String author;

	private List<LinkAddress> addresses;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<LinkAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<LinkAddress> addresses) {
		this.addresses = addresses;
	}

	public LinkAddress addressOf(String site) {

		if (CollectionUtils.isEmpty(addresses)) {

			return null;
		}
		return addresses.stream().filter(address -> address.getSite().equals(site)).findFirst().orElse(null);
	}
}