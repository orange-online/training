package cn.deepdraw.training.novel.crawler.biquge.app.interfaces;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import cn.deepdraw.training.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapter;

/**
 * @author xjn
 * @description BiqugeNovelChapterConverterTest
 * @date 2020/11/20
 */
public class BiqugeNovelChapterConverterTest {

	private BiqugeNovelChapterConverter converter = new BiqugeNovelChapterConverter();

	@Test
	public void toChapter_happyPath() {

		BiqugeNovelChapter chapter = composeBiqugeNovelChapter();
		Chapter expected = composeChapter();

		Chapter dto = converter.toChapter(chapter);

		assertEquals(expected.getName(), dto.getName());
		assertEquals(expected.getLink(), dto.getLink());
	}

	@Test
	public void toChaptersDTOs() {

		BiqugeNovelChapter chapter = composeBiqugeNovelChapter();
		Chapter dto = composeChapter();
		List<BiqugeNovelChapter> chapters = Arrays.asList(null, chapter);
		List<Chapter> dtos = converter.toChapters(chapters);

		assertThat(dtos, hasSize(1));
		assertEquals(dtos.get(0).getName(), dto.getName());
		assertEquals(dtos.get(0).getLink(), dto.getLink());
	}

	private Chapter composeChapter() {

		Chapter expected = new Chapter();
		expected.setName("chapter 1 name");
		expected.setLink("url");
		return expected;
	}

	private BiqugeNovelChapter composeBiqugeNovelChapter() {

		BiqugeNovelChapter chapter = new BiqugeNovelChapter();
		chapter.setName("name");
		chapter.setSubTitle("chapter 1");
		chapter.setLink("url");
		return chapter;
	}

	@Test
	public void toChapterS_happyPath() {


	}
}
