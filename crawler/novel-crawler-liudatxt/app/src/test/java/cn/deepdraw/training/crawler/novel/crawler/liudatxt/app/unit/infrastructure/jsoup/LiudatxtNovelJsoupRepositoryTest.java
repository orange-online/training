package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.unit.infrastructure.jsoup;

import static org.junit.Assert.assertEquals;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovel;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.infrastructure.repository.jsoup.LiudatxtNovelJsoupRepository;

/**
 * LiudatxtNovelJsoupRepository Test
 * @author huangjiancheng
 * 2020-06-08
 */
public class LiudatxtNovelJsoupRepositoryTest {

	@Test
	public void should_return_a_not_empty_novel_list_when_url_is_available() {

		String keywords = "我吃西红柿";
		Assert.assertThat(new LiudatxtNovelJsoupRepository().find(keywords), Matchers.hasSize(Matchers.greaterThanOrEqualTo(1)));
		new LiudatxtNovelJsoupRepository().find(keywords).forEach(novel -> System.out.println(novel.toString()));
	}

	@Test
	public void findNovel_happyPath() {

		String url = "http://www.txtshuku.org/so/2352/";

		LiudatxtNovel liudatxtNovel = new LiudatxtNovelJsoupRepository().findNovel(url);

		assertEquals("三寸人间", liudatxtNovel.name());
		assertEquals("耳根", liudatxtNovel.author());
	}
}