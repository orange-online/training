package cn.deepdraw.training.novel.crawler.xuanshuwang.app.unit.application;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.novel.crawler.xuanshuwang.app.application.XuanshuwangNovelCrawlerAppServiceImpl;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapter;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapterContent;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.domain.XuanshuwangNovel;
import cn.deepdraw.training.novel.crawler.xuanshuwang.app.domain.XuanshuwangRepository;

/**
 * @author：杨攀
 * @date：2020年7月21日 下午3:37:04
 */
@RunWith(MockitoJUnitRunner.class)
public class XuanshuwangNovelCrawlerAppServiceImplTest {

    @InjectMocks
    private XuanshuwangNovelCrawlerAppServiceImpl serviceImpl;

    @Mock
    private XuanshuwangRepository repository;

    private String keywords;

    private List<XuanshuwangNovel> novels;

    private XuanshuwangNovel novel;

    private String url;

    @Before
    public void setUp() {

        keywords = "keywords";
        novel = mock(XuanshuwangNovel.class);
        novels = Arrays.asList(novel);
        url = "url";
    }

    @Test
    public void find_shouldReturnEmptyList_ifKeywordsIsNull() {

        keywords = null;
        
        List<XuanshuwangNovel> result = serviceImpl.findByKeywords(keywords);
        assertThat(result, is(empty()));
    }

    @Test
    public void findByKeywords_shouldReturnEmptyList_ifKeywordsIsEmpty() {

        keywords = "";
        
        List<XuanshuwangNovel> result = serviceImpl.findByKeywords(keywords);
        assertThat(result, is(empty()));
    }

    @Test
    public void findByKeywords_shouldReturnEmptyList_ifKeywordsIsWhiteSpaceOnly() {

        keywords = " ";
        
        List<XuanshuwangNovel> result = serviceImpl.findByKeywords(keywords);
        assertThat(result, is(empty()));
    }

    @Test
    public void findByKeywords_shouldReturnNovels_ifKeywordsIsNormal() {

        when(repository.findByKeywords(keywords)).thenReturn(novels);
        
        List<XuanshuwangNovel> result = serviceImpl.findByKeywords(keywords);
        assertThat(result, contains(novel));
    }

    @Test
    public void findChapters_shouldReturnEmptyList_ifKeywordsIsNull() {

        url = null;
        
        List<XuanshuwangChapter> result = serviceImpl.findChapters(url);
        assertThat(result, is(empty()));
    }

    @Test
    public void findChapters_shouldReturnEmptyList_ifKeywordsIsEmpty() {

        url = "";
        
        List<XuanshuwangChapter> result = serviceImpl.findChapters(url);
        assertThat(result, is(empty()));
    }

    @Test
    public void findChapters_shouldReturnEmptyList_ifKeywordsIsWhiteSpaceOnly() {

        url = " ";
        
        List<XuanshuwangChapter> result = serviceImpl.findChapters(url);
        assertThat(result, is(empty()));
    }

    @Test
    public void findChapters_shouldReturnChapters_ifUrlIsNormal() {

        XuanshuwangChapter chapter = mock(XuanshuwangChapter.class);
        List<XuanshuwangChapter> chapters = Arrays.asList(chapter);
        
        when(repository.findChapters(url)).thenReturn(chapters);
        
        List<XuanshuwangChapter> result = serviceImpl.findChapters(url);
        assertThat(result, contains(chapter));
    }

    @Test
    public void findChapterContent_shouldReturnNull_ifChapterUrlIsNull() {

        String chapterUrl = null;
        
        XuanshuwangChapterContent result = serviceImpl.findChapterContent(chapterUrl);
        assertNull(result);
    }

    @Test
    public void findChapterContent_shouldReturnNull_ifChapterUrlIsEmpty() {

        String chapterUrl = "";
        
        XuanshuwangChapterContent result = serviceImpl.findChapterContent(chapterUrl);
        assertNull(result);
    }

    @Test
    public void findChapterContent_shouldReturnNull_ifChapterUrlIsWhiteSpaceOnly() {

        String chapterUrl = " ";
        
        XuanshuwangChapterContent result = serviceImpl.findChapterContent(chapterUrl);
        assertNull(result);
    }

    @Test
    public void findChapterContent_shouldReturnChapterContent_ifChapterUrlIsNormal() {

        String chapterUrl = "chapterUrl";
        XuanshuwangChapterContent content = mock(XuanshuwangChapterContent.class);
        
        when(repository.findChapterContent(chapterUrl)).thenReturn(content);
        
        XuanshuwangChapterContent result = serviceImpl.findChapterContent(chapterUrl);
        assertThat(result, is(content));
    }

    @Test
    public void findNovel_happyPath() {

        String url = "url";
        XuanshuwangNovel xuanshuwangNovel = mock(XuanshuwangNovel.class);

        when(repository.findNovel(url)).thenReturn(xuanshuwangNovel);

        assertEquals(xuanshuwangNovel, serviceImpl.findNovel(url));
    }
}