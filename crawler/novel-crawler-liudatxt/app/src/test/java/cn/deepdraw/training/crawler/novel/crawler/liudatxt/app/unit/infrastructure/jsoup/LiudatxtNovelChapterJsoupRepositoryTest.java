package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.unit.infrastructure.jsoup;

import org.junit.Assert;
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
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtConstants;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.infrastructure.repository.jsoup.LiudatxtNovelChapterJsoupRepository;

/**
 * LiudatxtNovelChapterJsoupRepository Test
 * @author huangjiancheng
 * 2020-06-08
 */
@RunWith(MockitoJUnitRunner.class)
public class LiudatxtNovelChapterJsoupRepositoryTest {

	@Spy
	@InjectMocks
	private LiudatxtNovelChapterJsoupRepository chapterRepo;
	
	@Mock
	private ChannelApi channelApi;
	
	private ChannelDTO channel;
	
	@Before
	public void before() {
		
		channel = new ChannelDTO();
		channel.setLink("http://www.txtshuku.org");
		channel.setTimeout(60000);
	}

	@Test
	public void should_return_a_not_empty_chapter_list_when_url_is_available() {

		String url = "http://www.liudatxt.com/so/30254/";
		Mockito.when(channelApi.findByChannelCode(LiudatxtConstants.SITE)).thenReturn(channel);
		Assert.assertNotNull(chapterRepo.findChapters(url));
		chapterRepo.findChapters(url).forEach(chapter -> System.out.println(chapter.toString()));
	}
}