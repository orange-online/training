package cn.deepdraw.training.novel.crawler.liudatxt.app.interfaces;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContent;

/**
 * LiudatxtNovelChapterContent Converter
 * @author huangjiancheng
 * 2020-06-06
 */
@Component
public class LiudatxtNovelChapterContentConverter {

	public ChapterContent toChapterContent(LiudatxtNovelChapterContent chapterContent) {

		return chapterContent != null ? done(chapterContent) : null;
	}

	private ChapterContent done(LiudatxtNovelChapterContent chapterContent) {

		ChapterContent dto = new ChapterContent();
		dto.setContent(chapterContent.content());
		return dto;
	}
}