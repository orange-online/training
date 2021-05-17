package cn.deepdraw.training.novel.crawler.biquge.app.infrastructure.repository.jsoup;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
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
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterContent;

/**
 * @Description BiqugeNovelChapterContentJsoupRepositoryTest
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
@RunWith(MockitoJUnitRunner.class)
public class BiqugeNovelChapterContentJsoupRepositoryIntegrationTest {

	@Spy
	@InjectMocks
    private BiqugeNovelChapterContentJsoupRepository repo;
	
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
    public void findChapterContent_happyPath() {

		Mockito.when(channelApi.findByChannelCode(BiqugeConstants.SITE)).thenReturn(channel);
        BiqugeNovelChapterContent chapterContent = repo.findChapterContent("http://www.biquge.com/0_395/2863746.html");
        assertTrue(StringUtils.isNotBlank(chapterContent.getContent()));
    }
}