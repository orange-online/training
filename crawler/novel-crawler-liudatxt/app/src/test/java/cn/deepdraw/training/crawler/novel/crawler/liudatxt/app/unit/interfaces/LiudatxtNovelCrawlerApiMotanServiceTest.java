package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.unit.interfaces;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.application.LiudatxtNovelCrawlerAppService;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovel;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapter;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.interfaces.LiudatxtNovelChapterContentConverter;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.interfaces.LiudatxtNovelChapterConverter;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.interfaces.LiudatxtNovelConverter;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.interfaces.LiudatxtNovelCrawlerApiDubboService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

		List<NovelDTO> novelDTOsMocked = Arrays.asList(Mockito.mock(NovelDTO.class));
		when(novelConv.toNovelDTOs(novelsMocked)).thenReturn(novelDTOsMocked);

		List<NovelDTO> novelDTOs = motanService.find(keywords);

		Assert.assertSame(novelDTOsMocked, novelDTOs);
		Mockito.verify(appService).find(keywords);
		Mockito.verify(novelConv).toNovelDTOs(novelsMocked);
	}

	@Test
	public void should_call_findChapters_interface_in_appService_once_when_findChapters_interface_called() {

		String url = "keywords";
		List<LiudatxtNovelChapter> chaptersMocked = Arrays.asList(Mockito.mock(LiudatxtNovelChapter.class));
		when(appService.findChapters(url)).thenReturn(chaptersMocked);

		List<ChapterDTO> chapterDTOsMocked = Arrays.asList(Mockito.mock(ChapterDTO.class));
		when(chapterConv.toChapterDTOs(chaptersMocked)).thenReturn(chapterDTOsMocked);

		List<ChapterDTO> chapterDTOs = motanService.findChapters(url);

		Assert.assertSame(chapterDTOsMocked, chapterDTOs);
		Mockito.verify(appService).findChapters(url);
		Mockito.verify(chapterConv).toChapterDTOs(chaptersMocked);
	}

	@Test
	public void should_call_findChapterContent_interface_in_appService_once_when_findChapterContent_interface_called() {

		String url = "keywords";
		LiudatxtNovelChapterContent chapterContentMocked = Mockito.mock(LiudatxtNovelChapterContent.class);
		when(appService.findChapterContent(url)).thenReturn(chapterContentMocked);

		ChapterContentDTO chapterContentDTOMocked = Mockito.mock(ChapterContentDTO.class);
		when(chapterContentConv.toChapterContentDTO(chapterContentMocked)).thenReturn(chapterContentDTOMocked);

		ChapterContentDTO chapterContentDTO = motanService.findChapterContent(url);

		Assert.assertSame(chapterContentDTOMocked, chapterContentDTO);
		Mockito.verify(appService).findChapterContent(url);
		Mockito.verify(chapterContentConv).toChapterContentDTO(chapterContentMocked);
	}

	@Test
	public void findNovel_happyPath() {

		String url = "url";
		LiudatxtNovel liudatxtNovel = mock(LiudatxtNovel.class);

		when(appService.findNovel(url)).thenReturn(liudatxtNovel);

		assertEquals(liudatxtNovel, appService.findNovel(url));
	}
}