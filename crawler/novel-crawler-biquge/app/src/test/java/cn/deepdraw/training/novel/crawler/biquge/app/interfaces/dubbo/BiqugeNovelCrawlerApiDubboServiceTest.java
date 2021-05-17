package cn.deepdraw.training.novel.crawler.biquge.app.interfaces.dubbo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.novel.crawler.biquge.app.application.BiqugeNovelCrawlerAppService;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovel;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapter;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterContent;
import cn.deepdraw.training.novel.crawler.biquge.app.interfaces.BiqugeNovelChapterContentConverter;
import cn.deepdraw.training.novel.crawler.biquge.app.interfaces.BiqugeNovelChapterConverter;
import cn.deepdraw.training.novel.crawler.biquge.app.interfaces.BiqugeNovelConverter;

/**
 * @Description BiqugeNovelCrawlerApiMotanServiceTest
 * @Author zhangzhucong
 * @Date 2020/6/11
 **/
@RunWith(MockitoJUnitRunner.class)
public class BiqugeNovelCrawlerApiDubboServiceTest {
    
    @InjectMocks
    private BiqugeNovelCrawlerApiDubboService motanService;

    @Mock
    private BiqugeNovelCrawlerAppService appService;

    @Mock
    private BiqugeNovelConverter novelConverter;

    @Mock
    private BiqugeNovelChapterConverter chapterConverter;

    @Mock
    private BiqugeNovelChapterContentConverter contentConverter;

    @Test
    public void find_happyPath() {

        BiqugeNovel biqugeNovel = mock(BiqugeNovel.class);
        Novel novelDTO = mock(Novel.class);
        when(appService.findByKeyword("keywords")).thenReturn(Arrays.asList(biqugeNovel));
        when(novelConverter.toNovels(Arrays.asList(biqugeNovel))).thenReturn(Arrays.asList(novelDTO));

        assertEquals(Arrays.asList(novelDTO), motanService.find("keywords"));
    }

    @Test
    public void findNovel_happyPath() {

        String url = "url";
        Novel novelDTO = mock(Novel.class);
        BiqugeNovel biqugeNovel = mock(BiqugeNovel.class);

        when(appService.findNovel(url)).thenReturn(biqugeNovel);
        when(novelConverter.toNovel(biqugeNovel)).thenReturn(novelDTO);

        assertEquals(novelDTO, novelConverter.toNovel(appService.findNovel(url)));
    }

    @Test
    public void findChapters_happyPath() {

        BiqugeNovelChapter chapter = mock(BiqugeNovelChapter.class);
        Chapter chapterDTO = mock(Chapter.class);
        when(appService.findChapters("url")).thenReturn(Arrays.asList(chapter));
        when(chapterConverter.toChapters(Arrays.asList(chapter))).thenReturn(Arrays.asList(chapterDTO));

        assertEquals(Arrays.asList(chapterDTO), motanService.findChapters("url"));
    }

    @Test
    public void findChapterContent_happyPath() {

        BiqugeNovelChapterContent content = mock(BiqugeNovelChapterContent.class);
        ChapterContent contentDTO = mock(ChapterContent.class);

        when(appService.findChapterContent("url")).thenReturn(content);
        when(contentConverter.toChapterContent(content)).thenReturn(contentDTO);

        assertEquals(contentDTO, motanService.findChapterContent("url"));
    }

    @Test
    public void site_happyPath() {

        assertEquals("BIQUGE", motanService.site());
    }
}