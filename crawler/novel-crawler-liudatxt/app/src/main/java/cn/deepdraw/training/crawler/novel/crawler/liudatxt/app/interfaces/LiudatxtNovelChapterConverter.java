package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.interfaces;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapter;

/**
 * LiudatxtNovelChapter Converter
 * @author huangjiancheng
 * 2020-06-06
 */
@Component
public class LiudatxtNovelChapterConverter {

	public List<ChapterDTO> toChapterDTOs(List<LiudatxtNovelChapter> chapters) {

		return CollectionUtils.isEmpty(chapters) ? Collections.emptyList() : chapters.stream().map(chapter -> toChapterDTO(chapter)).filter(dto -> dto != null).collect(Collectors.toList());
	}

	public ChapterDTO toChapterDTO(LiudatxtNovelChapter chapter) {

		return chapter != null ? adapt(chapter) : null;
	}

	private ChapterDTO adapt(LiudatxtNovelChapter chapter) {

		ChapterDTO dto = new ChapterDTO();
		dto.setName(chapter.name());
		dto.setSequence(chapter.sequence());
		dto.setUrl(chapter.url());
		return dto;
	}
}