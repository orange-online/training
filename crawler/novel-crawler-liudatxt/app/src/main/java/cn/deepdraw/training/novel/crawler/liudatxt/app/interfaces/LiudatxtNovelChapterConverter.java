package cn.deepdraw.training.novel.crawler.liudatxt.app.interfaces;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtConstants;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapter;

/**
 * LiudatxtNovelChapter Converter
 * @author huangjiancheng
 * 2020-06-06
 */
@Component
public class LiudatxtNovelChapterConverter {

	public List<Chapter> toChapters(List<LiudatxtNovelChapter> chapters) {

		return CollectionUtils.isEmpty(chapters) ? Collections.emptyList() : chapters.stream().map(chapter -> toChapter(chapter)).filter(dto -> dto != null).collect(Collectors.toList());
	}

	public Chapter toChapter(LiudatxtNovelChapter chapter) {

		return chapter != null ? done(chapter) : null;
	}

	private Chapter done(LiudatxtNovelChapter chapter) {

		Chapter dto = new Chapter();
		dto.setName(chapter.name());
		dto.setSite(LiudatxtConstants.SITE);
		dto.setLink(chapter.link());
		dto.setIndex(chapter.index());
		return dto;
	}
}