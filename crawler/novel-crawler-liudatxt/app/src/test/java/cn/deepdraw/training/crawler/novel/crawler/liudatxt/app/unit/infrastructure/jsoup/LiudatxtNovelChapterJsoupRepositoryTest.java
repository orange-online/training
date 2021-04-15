package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.unit.infrastructure.jsoup;

import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.infrastructure.repository.jsoup.LiudatxtNovelChapterJsoupRepository;

/**
 * LiudatxtNovelChapterJsoupRepository Test
 * @author huangjiancheng
 * 2020-06-08
 */
public class LiudatxtNovelChapterJsoupRepositoryTest {

	private LiudatxtNovelChapterJsoupRepository chapterRepo = new LiudatxtNovelChapterJsoupRepository();

	@Test
	public void should_return_a_not_empty_chapter_list_when_url_is_available() {

		String url = "http://www.liudatxt.com/so/30254/";
		Assert.assertNotNull(chapterRepo.findChapters(url));
		chapterRepo.findChapters(url).forEach(chapter -> System.out.println(chapter.toString()));
	}
}