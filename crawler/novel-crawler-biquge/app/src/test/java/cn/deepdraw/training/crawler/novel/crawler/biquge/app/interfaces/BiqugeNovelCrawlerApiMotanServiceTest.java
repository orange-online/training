package cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.application.BiqugeNovelCrawlerAppService;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovel;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovelChapter;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovelChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces.converter.BiqugeNovelChapterContentConverter;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces.converter.BiqugeNovelChapterConverter;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces.converter.BiqugeNovelConverter;

/**
 * @Description BiqugeNovelCrawlerApiMotanServiceTest
 * @Author zhangzhucong
 * @Date 2020/6/11
 **/
@RunWith(MockitoJUnitRunner.class)
public class BiqugeNovelCrawlerApiMotanServiceTest {
    
    @InjectMocks
    private BiqugeNovelCrawlerApiMotanService motanService;

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
        NovelDTO novelDTO = mock(NovelDTO.class);
        when(appService.findByKeyword("keywords")).thenReturn(Arrays.asList(biqugeNovel));
        when(novelConverter.toNovelDTOs(Arrays.asList(biqugeNovel))).thenReturn(Arrays.asList(novelDTO));

        assertEquals(Arrays.asList(novelDTO), motanService.find("keywords"));
    }

    @Test
    public void findNovel_happyPath() {

        String url = "url";
        NovelDTO novelDTO = mock(NovelDTO.class);
        BiqugeNovel biqugeNovel = mock(BiqugeNovel.class);

        when(appService.findNovel(url)).thenReturn(biqugeNovel);
        when(novelConverter.toNovelDTO(biqugeNovel)).thenReturn(novelDTO);

        assertEquals(novelDTO, novelConverter.toNovelDTO(appService.findNovel(url)));
    }

    @Test
    public void findChapters_happyPath() {

        BiqugeNovelChapter chapter = mock(BiqugeNovelChapter.class);
        ChapterDTO chapterDTO = mock(ChapterDTO.class);
        when(appService.findChapters("url")).thenReturn(Arrays.asList(chapter));
        when(chapterConverter.toChapterDTOs(Arrays.asList(chapter))).thenReturn(Arrays.asList(chapterDTO));

        assertEquals(Arrays.asList(chapterDTO), motanService.findChapters("url"));
    }

    @Test
    public void findChapterContent_happyPath() {

        BiqugeNovelChapterContent content = mock(BiqugeNovelChapterContent.class);
        ChapterContentDTO contentDTO = mock(ChapterContentDTO.class);

        when(appService.findChapterContent("url")).thenReturn(content);
        when(contentConverter.toChapterContentDTO(content)).thenReturn(contentDTO);

        assertEquals(contentDTO, motanService.findChapterContent("url"));
    }

    @Test
    public void site_happyPath() {

        assertEquals("BIQUGE", motanService.site());
    }
}