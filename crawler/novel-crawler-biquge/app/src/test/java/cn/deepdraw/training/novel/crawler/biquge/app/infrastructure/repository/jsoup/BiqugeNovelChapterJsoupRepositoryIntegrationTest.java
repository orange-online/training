package cn.deepdraw.training.novel.crawler.biquge.app.infrastructure.repository.jsoup;

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

import cn.deepdraw.training.novel.channel.api.ChannelApi;
import cn.deepdraw.training.novel.channel.api.dto.ChannelDTO;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeConstants;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapter;

/**
 * @Description BiqugeNovelChapterJsoupRepository
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
@RunWith(MockitoJUnitRunner.class)
public class BiqugeNovelChapterJsoupRepositoryIntegrationTest {

	@Spy
	@InjectMocks
    private BiqugeNovelChapterJsoupRepository repo;
	
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
    public void findChapters_happyPath() {

		Mockito.when(channelApi.findByChannelCode(BiqugeConstants.SITE)).thenReturn(channel);
        List<BiqugeNovelChapter> chapters = repo.findChapters("http://www.biquge.com/0_395/");
        assertThat(chapters, Matchers.hasSize(Matchers.greaterThanOrEqualTo(1)));
    }
}