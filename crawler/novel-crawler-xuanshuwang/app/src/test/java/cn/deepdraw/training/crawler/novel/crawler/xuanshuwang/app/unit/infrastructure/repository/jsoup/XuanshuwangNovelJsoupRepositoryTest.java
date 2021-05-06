package cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.unit.infrastructure.repository.jsoup;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.novel.crawler.channel.api.ChannelApi;
import cn.deepdraw.training.crawler.novel.crawler.channel.api.dto.ChannelDTO;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapter;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangConstants;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangNovel;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.infrastructure.repository.jsoup.XuanshuwangNovelJsoupRepository;

/**
 * @author：杨攀
 * @date：2020年7月22日 上午9:58:18
 */
@RunWith(MockitoJUnitRunner.class)
public class XuanshuwangNovelJsoupRepositoryTest {

	@Spy
	@InjectMocks
    private XuanshuwangNovelJsoupRepository repo;
	
	@Mock
	private ChannelApi channelApi;
	
	private ChannelDTO channel;
	
	@Before
	public void before() {
		
		channel = new ChannelDTO();
		channel.setLink("http://www.xuanshu.com");
		channel.setTimeout(60000);
	}

    @Test
    public void findByKeywords_returnThreeBooks_ifKeywordsIsLiuCiXin() {

        String keywords = "刘慈欣";
		Mockito.when(channelApi.findByChannelCode(XuanshuwangConstants.SITE)).thenReturn(channel);
        List<XuanshuwangNovel> novels = repo.findByKeywords(keywords);
        assertThat(novels.size(), is(3));
        assertEquals(XuanshuwangNovel.of("微纪元", "刘慈欣", "http://www.xuanshu.com/book/49590/"), novels.get(0));
        assertEquals(XuanshuwangNovel.of("流浪地球", "刘慈欣", "http://www.xuanshu.com/book/35363/"), novels.get(1));
        assertEquals(XuanshuwangNovel.of("西洋", "刘慈欣", "http://www.xuanshu.com/book/63673/"), novels.get(2));
    }

    @Test
    public void findChapters_shouldReturnSixChapters_ifUrlIsNormal() {

        String url = "http://www.xuanshu.com/book/49590/";
		Mockito.when(channelApi.findByChannelCode(XuanshuwangConstants.SITE)).thenReturn(channel);
        List<XuanshuwangChapter> chapters = repo.findChapters(url);
        assertThat(chapters.size(), is(6));
        assertEquals(new XuanshuwangChapter("第一节 回归", "http://www.xuanshu.com/book/49590/18327674.html", 1), chapters.get(0));
    }

    @Test
    public void findChapterContent_shouldReturnChapterContent_ifUrlIsNormal() {

        String chapterUrl = "http://www.xuanshu.com/book/49590/18327674.html";
		Mockito.when(channelApi.findByChannelCode(XuanshuwangConstants.SITE)).thenReturn(channel);
        XuanshuwangChapterContent result = repo.findChapterContent(chapterUrl);
        assertThat(result.content().length(), greaterThanOrEqualTo(1));
    }

    @Test
    public void findNovel_happyPath() {

        String url = "http://www.xuanshu.com/book/10063/";
		Mockito.when(channelApi.findByChannelCode(XuanshuwangConstants.SITE)).thenReturn(channel);
        XuanshuwangNovel novel = repo.findNovel(url);
        assertEquals("诡秘之主", novel.name());
        assertEquals("爱潜水的乌贼", novel.author());
    }
}