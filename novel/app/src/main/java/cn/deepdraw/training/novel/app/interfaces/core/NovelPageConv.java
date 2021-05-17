package cn.deepdraw.training.novel.app.interfaces.core;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.api.conv.IdLongEntityPageConv;
import cn.deepdraw.training.novel.api.dto.LinkAddress;
import cn.deepdraw.training.novel.api.dto.NovelDTO;
import cn.deepdraw.training.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.novel.app.domain.core.Novel;

/**
 * Novel Adapter
 * @author huangjiancheng
 * 2020-06-19
 */
@Component
public class NovelPageConv extends IdLongEntityPageConv<Novel, NovelDTO> {

	@Override
	protected NovelDTO doing(Novel novel) {

		NovelDTO dto = new NovelDTO();
		dto.setName(novel.name());
		dto.setAuthor(novel.author());
		dto.setAddresses(adaptLinks(novel.addrs()));
		return dto;
	}

	private List<LinkAddress> adaptLinks(List<LinkAddr> links) {

		return links.stream().map(link -> LinkAddress.of(link.site(), link.version(), link.link(), link.path())).collect(Collectors.toList());
	}
}