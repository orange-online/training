package cn.deepdraw.training.crawler.novel.crawler.biquge.app.infrastructure.repository.jsoup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeConstants;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovel;
import cn.deepdraw.training.crawler.novel.crawler.channel.api.ChannelApi;
import cn.deepdraw.training.crawler.novel.crawler.channel.api.dto.ChannelDTO;

/**
 * @Description BiqugeNovelJsoupRepositoryTest
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
@RunWith(MockitoJUnitRunner.class)
public class BiqugeNovelJsoupRepositoryIntegrationTest {

	@Spy
	@InjectMocks
    private BiqugeNovelJsoupRepository repo;
	
	@Mock
	private ChannelApi channelApi;
	
	private ChannelDTO channel;
	
	@Before
	public void before() {
		
		channel = new ChannelDTO();
		channel.setLink("http://www.biquge.com");
		channel.setTimeout(60000);
	}

    @Test
    public void findByKeyword_happyPath() {

		Mockito.when(channelApi.findByChannelCode(BiqugeConstants.SITE)).thenReturn(channel);
        List<BiqugeNovel> biqugeNovels = repo.findByKeyword("我吃西红柿");
        assertThat(biqugeNovels, Matchers.hasSize(Matchers.greaterThanOrEqualTo(1)));
    }

    @Test
    public void findByUrl_happyPath() {

		Mockito.when(channelApi.findByChannelCode(BiqugeConstants.SITE)).thenReturn(channel);
        BiqugeNovel biqugeNovel = repo.findNovel("http://www.biquge.com/0_14/");

        assertEquals("完美世界", biqugeNovel.getName());
        assertEquals("辰东", biqugeNovel.getAuthor());
        assertEquals("http://www.biquge.com/0_14/", biqugeNovel.getLink());
    }
}