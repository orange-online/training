package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.interfaces;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContent;

/**
 * LiudatxtNovelChapterContent Converter
 * @author huangjiancheng
 * 2020-06-06
 */
@Component
public class LiudatxtNovelChapterContentConverter {

	public ChapterContentDTO toChapterContentDTO(LiudatxtNovelChapterContent chapterContent) {

		return chapterContent != null ? adapt(chapterContent) : null;
	}

	private ChapterContentDTO adapt(LiudatxtNovelChapterContent chapterContent) {

		ChapterContentDTO dto = new ChapterContentDTO();
		dto.setContent(chapterContent.content());
		return dto;
	}
}