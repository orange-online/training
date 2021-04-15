package cn.deepdraw.training.cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.unit.interfaces;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
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

import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.domain.XuanshuwangNovel;
import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.application.XuanshuwangNovelCrawlerAppService;
import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.domain.XuanshuwangChapter;
import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.domain.XuanshuwangChapterContent;
import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.interfaces.XuanshuwangChapterContentConverter;
import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.interfaces.XuanshuwangChapterConverter;
import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.interfaces.XuanshuwangNovelConverter;
import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.interfaces.XuanshuwangNovelCrawlerApiMotanService;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;

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
        
        List<NovelDTO> result = service.find(keywords);
        assertThat(result, is(empty()));
    }

    @Test
    public void find_shouldReturnEmpty_whenKeywordsIsEmptyString() {

        String keywords = "";
        when(appService.findByKeywords(keywords)).thenReturn(Collections.emptyList());
        
        List<NovelDTO> result = service.find(keywords);
        assertThat(result, is(empty()));
    }

    @Test
    public void find_shouldReturnEmpty_whenKeywordsIsWhiteSpaceOnly() {

        String keywords = " ";
        when(appService.findByKeywords(keywords)).thenReturn(Collections.emptyList());
        
        List<NovelDTO> result = service.find(keywords);
        assertThat(result, is(empty()));
    }

    @Test
    public void find_shouldReturnNovels_whenNormal() {

        String keywords = "keywords";
        NovelDTO novelDTO = mock(NovelDTO.class);
        List<NovelDTO> novelDTOs = Arrays.asList(novelDTO);
        XuanshuwangNovel novel = mock(XuanshuwangNovel.class);
        List<XuanshuwangNovel> novels = Arrays.asList(novel);
        
        when(appService.findByKeywords(keywords)).thenReturn(novels);
        when(converter.toNovelDTOs(novels)).thenReturn(novelDTOs);
        
        List<NovelDTO> result = service.find(keywords);
        assertThat(result, contains(novelDTO));
    }

    @Test
    public void findChapters_shouldReturnEmpty_whenKeywordsIsNull() {

        String url = null;
        when(appService.findChapters(url)).thenReturn(Collections.emptyList());
        
        List<ChapterDTO> result = service.findChapters(url);
        assertThat(result, is(empty()));
    }

    @Test
    public void findChapters_shouldReturnEmpty_whenKeywordsIsEmptyString() {

        String url = "";
        when(appService.findChapters(url)).thenReturn(Collections.emptyList());
        
        List<ChapterDTO> result = service.findChapters(url);
        assertThat(result, is(empty()));
    }

    @Test
    public void findChapters_shouldReturnEmpty_whenKeywordsIsWhiteSpaceOnly() {

        String url = " ";
        when(appService.findChapters(url)).thenReturn(Collections.emptyList());
        
        List<ChapterDTO> result = service.findChapters(url);
        assertThat(result, is(empty()));
    }

    @Test
    public void findChapters_shouldReturnNovels_whenNormal() {

        String url = "url";
        XuanshuwangChapter chapter = new XuanshuwangChapter("name", "url", 2046);
        List<XuanshuwangChapter> chapters = Arrays.asList(chapter);
        ChapterDTO chapterDTO = mock(ChapterDTO.class);
        List<ChapterDTO> chapterDTOs = Arrays.asList(chapterDTO);
        
        when(appService.findChapters(url)).thenReturn(chapters);
        when(chapterConverter.toChapterDTOs(chapters)).thenReturn(chapterDTOs);
        
        List<ChapterDTO> result = service.findChapters(url);
        assertThat(result, contains(chapterDTO));
    }

    @Test
    public void findChapterContent_shouldReturnChapterContent_ifNormal() {

        String chapterUrl = "chapterUrl";
        XuanshuwangChapterContent content = mock(XuanshuwangChapterContent.class);
        ChapterContentDTO contentDTO = mock(ChapterContentDTO.class);
        
        when(appService.findChapterContent(chapterUrl)).thenReturn(content);
        when(contentConverter.toChapterContentDTO(content)).thenReturn(contentDTO);
        
        ChapterContentDTO result = service.findChapterContent(chapterUrl);
        assertThat(result, is(contentDTO));
    }

    @Test
    public void findNovel_happyPath() {

        String url = "url";
        XuanshuwangNovel xuanshuwangNovel = mock(XuanshuwangNovel.class);
        NovelDTO novelDTO = mock(NovelDTO.class);

        when(appService.findNovel(url)).thenReturn(xuanshuwangNovel);
        when(converter.toNovelDTO(xuanshuwangNovel)).thenReturn(novelDTO);

        assertEquals(novelDTO, service.findNovel(url));
    }
}