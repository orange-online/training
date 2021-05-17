package cn.deepdraw.training.novel.crawler.biquge.app.application.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovel;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapter;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterContent;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterContentRepository;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterRepository;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelRepository;

/**
 * @Description BiqugeNovelCrawlerAppServiceImplTest
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
@RunWith(MockitoJUnitRunner.class)
public class BiqugeNovelCrawlerAppServiceImplTest {

    @InjectMocks
    private BiqugeNovelCrawlerAppServiceImpl appService;

    @Mock
    private BiqugeNovelRepository novelRepo;

    @Mock
    private BiqugeNovelChapterRepository chapterRepo;

    @Mock
    private BiqugeNovelChapterContentRepository contentRepo;

    @Test
    public void findByKeyword_happyPath() {

        BiqugeNovel biqugeNovel = mock(BiqugeNovel.class);
        when(novelRepo.findByKeyword("keyword")).thenReturn(Arrays.asList(biqugeNovel));

        assertThat(appService.findByKeyword("keyword"), Matchers.contains(biqugeNovel));
    }

    @Test
    public void findChapters_happyPath() {

        BiqugeNovelChapter chapter = mock(BiqugeNovelChapter.class);
        when(chapterRepo.findChapters("url")).thenReturn(Arrays.asList(chapter));

        assertThat(appService.findChapters("url"), Matchers.contains(chapter));
    }

    @Test
    public void findChapterContent_happyPath() {

        BiqugeNovelChapterContent chapterContent = mock(BiqugeNovelChapterContent.class);
        when(contentRepo.findChapterContent("url")).thenReturn(chapterContent);

        assertEquals(chapterContent, appService.findChapterContent("url"));
    }

    @Test
    public void findNovelByUrl() {

        BiqugeNovel biqugeNovel = mock(BiqugeNovel.class);
        String url = "url";

        when(novelRepo.findNovel(url)).thenReturn(biqugeNovel);

        assertEquals(biqugeNovel, appService.findNovel(url));
    }
}