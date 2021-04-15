package cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces.converter;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovelChapter;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;

/**
 * @author xjn
 * @description BiqugeNovelChapterConverterTest
 * @date 2020/11/20
 */
public class BiqugeNovelChapterConverterTest {

	private BiqugeNovelChapterConverter converter = new BiqugeNovelChapterConverter();

	@Test
	public void toChapterDTO_happyPath() {

		BiqugeNovelChapter chapter = composeBiqugeNovelChapter();
		ChapterDTO expected = composeChapterDTO();

		ChapterDTO dto = converter.toChapterDTO(chapter);

		assertEquals(expected.getName(), dto.getName());
		assertEquals(expected.getUrl(), dto.getUrl());
	}

	@Test
	public void toChaptersDTOs() {

		BiqugeNovelChapter chapter = composeBiqugeNovelChapter();
		ChapterDTO dto = composeChapterDTO();
		List<BiqugeNovelChapter> chapters = Arrays.asList(null, chapter);
		List<ChapterDTO> dtos = converter.toChapterDTOs(chapters);

		assertThat(dtos, hasSize(1));
		assertEquals(dtos.get(0).getName(), dto.getName());
		assertEquals(dtos.get(0).getUrl(), dto.getUrl());
	}

	private ChapterDTO composeChapterDTO() {

		ChapterDTO expected = new ChapterDTO();
		expected.setName("chapter 1 name");
		expected.setUrl("url");
		return expected;
	}

	private BiqugeNovelChapter composeBiqugeNovelChapter() {

		BiqugeNovelChapter chapter = new BiqugeNovelChapter();
		chapter.setName("name");
		chapter.setSubTitle("chapter 1");
		chapter.setUrl("url");
		return chapter;
	}

	@Test
	public void toChapterDTOS_happyPath() {


	}
}
