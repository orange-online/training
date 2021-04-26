package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.unit.infrastructure.jsoup;

import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtConstants;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.infrastructure.repository.jsoup.LiudatxtNovelChapterContentJsoupRepository;

/**
 * LiudatxtNovelChapterContentJsoupRepository Test
 * @author huangjiancheng
 * 2020-06-08
 */
public class LiudatxtNovelChapterContentJsoupRepositoryTest {

	private LiudatxtNovelChapterContentJsoupRepository chapterContentRepo = new LiudatxtNovelChapterContentJsoupRepository();

	@Test
	public void should_return_a_not_null_chapter_content_when_url_is_available() {

		String url = LiudatxtConstants.URL_BASE + "/so/2352/32775082.html";
		Assert.assertNotNull(chapterContentRepo.findChapterContent(url));
		System.out.println(chapterContentRepo.findChapterContent(url).content());
	}
}