package cn.deepdraw.training.novel.crawler.xuanshuwang.app.unit.interfaces;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.application.XuanshuwangNovelCrawlerAppService;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapter;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapterContent;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.domain.XuanshuwangNovel;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.interfaces.XuanshuwangChapterContentConverter;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.interfaces.XuanshuwangChapterConverter;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.interfaces.XuanshuwangNovelConverter;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.interfaces.XuanshuwangNovelCrawlerApiMotanService;

/**
 * 选书网爬虫 motan服务单元测试
 * @author：杨攀
 * @date：2020年6月6日 下午7:43:18
 */
@RunWith(MockitoJUnitRunner.class)
public class XuanshuwangNovelCrawlerApiMotanServiceTest {

    @InjectMocks
    private XuanshuwangNovelCrawlerApiMotanService service;

    @Mock
    private XuanshuwangNovelCrawlerAppService appService;

    @Mock
    private XuanshuwangNovelConverter converter;

    @Mock
    private XuanshuwangChapterConverter chapterConverter;

    @Mock
    private XuanshuwangChapterContentConverter contentConverter;

    @Test
    public void site_shouldReturnXUANSHUWANG_whenCallSite() {

        assertThat(service.site(), is("XUANSHUWANG"));
    }

    @Test
    public void find_shouldReturnEmpty_whenKeywordsIsNull() {

        String keywords = null;
        when(appService.findByKeywords(keywords)).thenReturn(Collections.emptyList());
        
        List<Novel> result = service.find(keywords);
        assertThat(result, is(empty()));
    }

    @Test
    public void find_shouldReturnEmpty_whenKeywordsIsEmptyString() {

        String keywords = "";
        when(appService.findByKeywords(keywords)).thenReturn(Collections.emptyList());
        
        List<Novel> result = service.find(keywords);
        assertThat(result, is(empty()));
    }

    @Test
    public void find_shouldReturnEmpty_whenKeywordsIsWhiteSpaceOnly() {

        String keywords = " ";
        when(appService.findByKeywords(keywords)).thenReturn(Collections.emptyList());
        
        List<Novel> result = service.find(keywords);
        assertThat(result, is(empty()));
    }

    @Test
    public void find_shouldReturnNovels_whenNormal() {

        String keywords = "keywords";
        Novel novelDTO = mock(Novel.class);
        List<Novel> novelDTOs = Arrays.asList(novelDTO);
        XuanshuwangNovel novel = mock(XuanshuwangNovel.class);
        List<XuanshuwangNovel> novels = Arrays.asList(novel);
        
        when(appService.findByKeywords(keywords)).thenReturn(novels);
        when(converter.toNovels(novels)).thenReturn(novelDTOs);
        
        List<Novel> result = service.find(keywords);
        assertThat(result, contains(novelDTO));
    }

    @Test
    public void findChapters_shouldReturnEmpty_whenKeywordsIsNull() {

        String url = null;
        when(appService.findChapters(url)).thenReturn(Collections.emptyList());
        
        List<Chapter> result = service.findChapters(url);
        assertThat(result, is(empty()));
    }

    @Test
    public void findChapters_shouldReturnEmpty_whenKeywordsIsEmptyString() {

        String url = "";
        when(appService.findChapters(url)).thenReturn(Collections.emptyList());
        
        List<Chapter> result = service.findChapters(url);
        assertThat(result, is(empty()));
    }

    @Test
    public void findChapters_shouldReturnEmpty_whenKeywordsIsWhiteSpaceOnly() {

        String url = " ";
        when(appService.findChapters(url)).thenReturn(Collections.emptyList());
        
        List<Chapter> result = service.findChapters(url);
        assertThat(result, is(empty()));
    }

    @Test
    public void findChapters_shouldReturnNovels_whenNormal() {

        String url = "url";
        XuanshuwangChapter chapter = new XuanshuwangChapter("name", "url", 2046);
        List<XuanshuwangChapter> chapters = Arrays.asList(chapter);
        Chapter chapterDTO = mock(Chapter.class);
        List<Chapter> chapterDTOs = Arrays.asList(chapterDTO);
        
        when(appService.findChapters(url)).thenReturn(chapters);
        when(chapterConverter.toChapters(chapters)).thenReturn(chapterDTOs);
        
        List<Chapter> result = service.findChapters(url);
        assertThat(result, contains(chapterDTO));
    }

    @Test
    public void findChapterContent_shouldReturnChapterContent_ifNormal() {

        String chapterUrl = "chapterUrl";
        XuanshuwangChapterContent content = mock(XuanshuwangChapterContent.class);
        ChapterContent contentDTO = mock(ChapterContent.class);
        
        when(appService.findChapterContent(chapterUrl)).thenReturn(content);
        when(contentConverter.toChapterContent(content)).thenReturn(contentDTO);
        
        ChapterContent result = service.findChapterContent(chapterUrl);
        assertThat(result, is(contentDTO));
    }

    @Test
    public void findNovel_happyPath() {

        String url = "url";
        XuanshuwangNovel xuanshuwangNovel = mock(XuanshuwangNovel.class);
        Novel novelDTO = mock(Novel.class);

        when(appService.findNovel(url)).thenReturn(xuanshuwangNovel);
        when(converter.toNovel(xuanshuwangNovel)).thenReturn(novelDTO);

        assertEquals(novelDTO, service.findNovel(url));
    }
}