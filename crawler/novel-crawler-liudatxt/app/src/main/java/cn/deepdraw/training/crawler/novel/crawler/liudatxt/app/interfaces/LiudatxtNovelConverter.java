package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.interfaces;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovel;

/**
 * LiudatxtNovel Converter
 * @author huangjiancheng
 * 2020-06-06
 */
@Component
public class LiudatxtNovelConverter {

	public List<NovelDTO> toNovelDTOs(List<LiudatxtNovel> novels) {

		return CollectionUtils.isEmpty(novels) ? Collections.emptyList() : novels.stream().map(novel -> toNovelDTO(novel)).filter(dto -> dto != null).collect(Collectors.toList());
	}

	public NovelDTO toNovelDTO(LiudatxtNovel novel) {

		return novel != null ? adapt(novel) : null;
	}

	private NovelDTO adapt(LiudatxtNovel novel) {

		NovelDTO dto = new NovelDTO();
		dto.setName(novel.name());
		dto.setAuthor(novel.author());
		dto.setUrl(novel.url());
		return dto;
	}
}