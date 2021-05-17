package cn.deepdraw.training.novel.crawler.liudatxt.app.unit.interfaces;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.novel.crawler.liudatxt.app.application.LiudatxtNovelCrawlerAppService;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovel;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapter;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContent;
import cn.deepdraw.training.novel.crawler.liudatxt.app.interfaces.LiudatxtNovelChapterContentConverter;
import cn.deepdraw.training.novel.crawler.liudatxt.app.interfaces.LiudatxtNovelChapterConverter;
import cn.deepdraw.training.novel.crawler.liudatxt.app.interfaces.LiudatxtNovelConverter;
import cn.deepdraw.training.novel.crawler.liudatxt.app.interfaces.LiudatxtNovelCrawlerApiDubboService;

/**
 * 溜达小说爬虫接口Motan服务
 * @author huangjiancheng
 * 2020-06-06
 */
@RunWith(MockitoJUnitRunner.class)
public class LiudatxtNovelCrawlerApiMotanServiceTest {

	@InjectMocks
	private LiudatxtNovelCrawlerApiDubboService motanService;

	@Mock
	private LiudatxtNovelCrawlerAppService appService;

	@Mock
	private LiudatxtNovelConverter novelConv;

	@Mock
	private LiudatxtNovelChapterConverter chapterConv;

	@Mock
	private LiudatxtNovelChapterContentConverter chapterContentConv;

	@Test
	public void should_return_LIUDATXT_when_site_interface_called() {

		assertEquals("LIUDATXT", motanService.site());
	}

	@Test
	public void should_call_find_interface_in_app_service_once_when_find_interface_called() {

		String keywords = "keywords";
		List<LiudatxtNovel> novelsMocked = Arrays.asList(Mockito.mock(LiudatxtNovel.class));
		when(appService.find(keywords)).thenReturn(novelsMocked);

		List<Novel> novelDTOsMocked = Arrays.asList(Mockito.mock(Novel.class));
		when(novelConv.toNovels(novelsMocked)).thenReturn(novelDTOsMocked);

		List<Novel> novelDTOs = motanService.find(keywords);

		Assert.assertSame(novelDTOsMocked, novelDTOs);
		Mockito.verify(appService).find(keywords);
		Mockito.verify(novelConv).toNovels(novelsMocked);
	}

	@Test
	public void should_call_findChapters_interface_in_appService_once_when_findChapters_interface_called() {

		String url = "keywords";
		List<LiudatxtNovelChapter> chaptersMocked = Arrays.asList(Mockito.mock(LiudatxtNovelChapter.class));
		when(appService.findChapters(url)).thenReturn(chaptersMocked);

		List<Chapter> chapterDTOsMocked = Arrays.asList(Mockito.mock(Chapter.class));
		when(chapterConv.toChapters(chaptersMocked)).thenReturn(chapterDTOsMocked);

		List<Chapter> chapterDTOs = motanService.findChapters(url);

		Assert.assertSame(chapterDTOsMocked, chapterDTOs);
		Mockito.verify(appService).findChapters(url);
		Mockito.verify(chapterConv).toChapters(chaptersMocked);
	}

	@Test
	public void should_call_findChapterContent_interface_in_appService_once_when_findChapterContent_interface_called() {

		String url = "keywords";
		LiudatxtNovelChapterContent chapterContentMocked = Mockito.mock(LiudatxtNovelChapterContent.class);
		when(appService.findChapterContent(url)).thenReturn(chapterContentMocked);

		ChapterContent chapterContentDTOMocked = Mockito.mock(ChapterContent.class);
		when(chapterContentConv.toChapterContent(chapterContentMocked)).thenReturn(chapterContentDTOMocked);

		ChapterContent chapterContentDTO = motanService.findChapterContent(url);

		Assert.assertSame(chapterContentDTOMocked, chapterContentDTO);
		Mockito.verify(appService).findChapterContent(url);
		Mockito.verify(chapterContentConv).toChapterContent(chapterContentMocked);
	}

	@Test
	public void findNovel_happyPath() {

		String url = "url";
		LiudatxtNovel liudatxtNovel = mock(LiudatxtNovel.class);

		when(appService.findNovel(url)).thenReturn(liudatxtNovel);

		assertEquals(liudatxtNovel, appService.findNovel(url));
	}
}