package cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.unit.infrastructure.jsoup;

import static org.junit.Assert.assertEquals;

import org.hamcrest.Matchers;
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
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.domain.LiudatxtNovel;
import cn.deepdraw.training.crawler.novel.crawler.liudatxt.app.infrastructure.repository.jsoup.LiudatxtNovelJsoupRepository;

/**
 * LiudatxtNovelJsoupRepository Test
 * @author huangjiancheng
 * 2020-06-08
 */
@RunWith(MockitoJUnitRunner.class)
public class LiudatxtNovelJsoupRepositoryTest {

	@Spy
	@InjectMocks
	private LiudatxtNovelJsoupRepository jsoupRepo;
	
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
	public void should_return_a_not_empty_novel_list_when_url_is_available() {

		String keywords = "我吃西红柿";
		Mockito.when(channelApi.findByChannelCode(LiudatxtConstants.SITE)).thenReturn(channel);
		Assert.assertThat(jsoupRepo.find(keywords), Matchers.hasSize(Matchers.greaterThanOrEqualTo(1)));
	}

	@Test
	public void findNovel_happyPath() {

		String url = "http://www.txtshuku.org/so/2352/";
		Mockito.when(channelApi.findByChannelCode(LiudatxtConstants.SITE)).thenReturn(channel);
		LiudatxtNovel liudatxtNovel = jsoupRepo.findNovel(url);

		assertEquals("三寸人间", liudatxtNovel.name());
		assertEquals("耳根", liudatxtNovel.author());
	}
}