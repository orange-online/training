package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.unit.application;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.application.LiudatxtNovelCrawlerAppServiceImpl;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovel;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapter;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContentRepository;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterRepository;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * LiudatxtNovelCrawlerAppServiceImpl Test
 * @author huangjiancheng
 * 2020-06-08
 */
@RunWith(MockitoJUnitRunner.class)
public class LiudatxtNovelCrawlerAppServiceImplTest {

	@InjectMocks
	private LiudatxtNovelCrawlerAppServiceImpl appServiceImpl;

	@Mock
	private LiudatxtNovelRepository novelRepo;

	@Mock
	private LiudatxtNovelChapterRepository chapterRepo;

	@Mock
	private LiudatxtNovelChapterContentRepository chapterContentRepo;

	@Test
	public void should_return_a_empty_list_and_find_interface_in_repository_never_called_when_find_method_called_but_keywords_is_blank() {

		Assert.assertThat(appServiceImpl.find("  "), Matchers.empty());
		Mockito.verify(novelRepo, Mockito.never()).find(Mockito.anyString());
	}

	@Test
	public void should_return_the_same_response_of_find_interface_in_repository_when_find_method_called_and_keywords_is_not_blank() {

		String keywords = "keywords";
		List<LiudatxtNovel> novelsMocked = Arrays.asList(mock(LiudatxtNovel.class));
		Mockito.when(novelRepo.find(keywords)).thenReturn(novelsMocked);

		List<LiudatxtNovel> novels = appServiceImpl.find(keywords);

		Assert.assertSame(novelsMocked, novels);
		Mockito.verify(novelRepo).find(keywords);
	}

	@Test
	public void should_return_a_empty_list_and_findChapters_interface_in_repository_never_called_when_findChapters_method_called_but_keywords_is_blank() {

		Assert.assertThat(appServiceImpl.findChapters("  "), Matchers.empty());
		Mockito.verify(chapterRepo, Mockito.never()).findChapters(Mockito.anyString());
	}

	@Test
	public void should_return_the_same_response_of_findChapters_interface_in_repository_when_findChapters_method_called_and_url_is_not_blank() {

		String url = "url";
		List<LiudatxtNovelChapter> novelsMocked = Arrays.asList(mock(LiudatxtNovelChapter.class));
		Mockito.when(chapterRepo.findChapters(url)).thenReturn(novelsMocked);

		List<LiudatxtNovelChapter> novels = appServiceImpl.findChapters(url);

		Assert.assertSame(novelsMocked, novels);
		Mockito.verify(chapterRepo).findChapters(url);
	}

	@Test
	public void should_return_a_null_object_and_findChapterContent_interface_in_repository_never_called_when_findChapterContent_method_called_but_keywords_is_blank() {

		Assert.assertThat(appServiceImpl.findChapterContent("  "), Matchers.nullValue());
		Mockito.verify(chapterContentRepo, Mockito.never()).findChapterContent(Mockito.anyString());
	}

	@Test
	public void should_return_the_same_response_of_findChapterContent_interface_in_repository_when_findChapterContent_method_called_and_url_is_not_blank() {

		String url = "url";
		LiudatxtNovelChapterContent chapterContentMocked = mock(LiudatxtNovelChapterContent.class);
		Mockito.when(chapterContentRepo.findChapterContent(url)).thenReturn(chapterContentMocked);

		LiudatxtNovelChapterContent chapterContent = appServiceImpl.findChapterContent(url);

		Assert.assertSame(chapterContentMocked, chapterContent);
		Mockito.verify(chapterContentRepo).findChapterContent(url);
	}

	@Test
	public void findNovel_happyPath() {

		String url = "url";
		LiudatxtNovel liudatxtNovel = mock(LiudatxtNovel.class);

		when(novelRepo.findNovel(url)).thenReturn(liudatxtNovel);

		assertEquals(liudatxtNovel, appServiceImpl.findNovel(url));
	}
}