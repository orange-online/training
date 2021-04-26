package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.interfaces;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtConstants;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovel;

/**
 * LiudatxtNovel Converter
 * @author huangjiancheng
 * 2020-06-06
 */
@Component
public class LiudatxtNovelConverter {

	public List<Novel> toNovels(List<LiudatxtNovel> novels) {

		return CollectionUtils.isEmpty(novels) ? Collections.emptyList() : novels.stream().map(novel -> toNovel(novel)).filter(dto -> dto != null).collect(Collectors.toList());
	}

	public Novel toNovel(LiudatxtNovel novel) {

		return novel != null ? done(novel) : null;
	}

	private Novel done(LiudatxtNovel novel) {

		Novel dto = new Novel();
		dto.setName(novel.name());
		dto.setAuthor(novel.author());
		dto.setSite(LiudatxtConstants.SITE);;
		dto.setLink(novel.link());
		return dto;
	}
}