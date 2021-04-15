package cn.deepdraw.training.crawler.novel.app.interfaces.core;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.framework.api.adapter.EntityBasePageAdapter;

/**
 * Novel Adapter
 * @author huangjiancheng
 * 2020-06-19
 */
@Component
public class NovelAdapter extends EntityBasePageAdapter<Novel, NovelDTO> {

	@Override
	protected NovelDTO doAdapt(Novel novel) {

		NovelDTO dto = new NovelDTO();
		dto.setNovelId(novel.novelId());
		dto.setName(novel.name());
		dto.setAuthor(novel.author());
		dto.setAddresses(adaptLinks(novel.addrs()));
		return dto;
	}

	private List<LinkAddress> adaptLinks(List<LinkAddr> links) {

		return links.stream().map(link -> LinkAddress.of(link.site().toString(), link.link(), link.path())).collect(Collectors.toList());
	}
}