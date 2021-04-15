package cn.deepdraw.training.crawler.novel.app.unit.interfaces.core.motan;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterCrawlingEventDTO;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelChapterCrawlingEventAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEvent;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelChapterCrawlingEventAdapter;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.motan.NovelChapterCrawlingEventApiMotanService;

/**
 * NovelChapterCrawlingEventApiMotanService Test
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@RunWith(MockitoJUnitRunner.class)
public class NovelChapterCrawlingEventApiMotanServiceTest {

	@InjectMocks
	private NovelChapterCrawlingEventApiMotanService apiMotanService;
	
	@Mock
	private NovelChapterCrawlingEventAppService eventAppService;
	
	@Mock
	private NovelChapterCrawlingEventAdapter eventAdapter;
	
	@Test
	public void should_return_a_not_null_instance_when_method_create_called() {
		
		String novelId = "novel_id", site = "site", chapterId = "chapter_id", link = "link";
		NovelChapterCrawlingEvent eventMocked = Mockito.mock(NovelChapterCrawlingEvent.class);
		Mockito.when(eventAppService.create(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any())).thenReturn(eventMocked);
		NovelChapterCrawlingEventDTO eventDTOMocked = Mockito.mock(NovelChapterCrawlingEventDTO.class);
		Mockito.when(eventAdapter.adapt(eventMocked)).thenReturn(eventDTOMocked);
		
		NovelChapterCrawlingEventDTO eventDTO = apiMotanService.create(novelId, site, chapterId, link);
		
		Assert.assertSame(eventDTOMocked, eventDTO);
		Mockito.verify(eventAppService).create(Mockito.anyString(), Mockito.any(), Mockito.any(), Mockito.any());
		Mockito.verify(eventAdapter).adapt(eventMocked);
	}
	
	@Test
	public void should_return_a_not_null_instance_when_method_publish_called() {
		
		String eventId = "novel_id";
		NovelChapterCrawlingEvent eventMocked = Mockito.mock(NovelChapterCrawlingEvent.class);
		Mockito.when(eventAppService.publish(eventId)).thenReturn(eventMocked);
		NovelChapterCrawlingEventDTO eventDTOMocked = Mockito.mock(NovelChapterCrawlingEventDTO.class);
		Mockito.when(eventAdapter.adapt(eventMocked)).thenReturn(eventDTOMocked);
		
		NovelChapterCrawlingEventDTO eventDTO = apiMotanService.publish(eventId);
		
		Assert.assertSame(eventDTOMocked, eventDTO);
		Mockito.verify(eventAppService).publish(eventId);
		Mockito.verify(eventAdapter).adapt(eventMocked);
	}
	
	@Test
	public void should_return_a_not_null_instance_when_method_complete_called() {
		
		String eventId = "novel_id", path = "path";
		NovelChapterCrawlingEvent eventMocked = Mockito.mock(NovelChapterCrawlingEvent.class);
		Mockito.when(eventAppService.complete(eventId, path)).thenReturn(eventMocked);
		NovelChapterCrawlingEventDTO eventDTOMocked = Mockito.mock(NovelChapterCrawlingEventDTO.class);
		Mockito.when(eventAdapter.adapt(eventMocked)).thenReturn(eventDTOMocked);
		
		NovelChapterCrawlingEventDTO eventDTO = apiMotanService.complete(eventId, path);
		
		Assert.assertSame(eventDTOMocked, eventDTO);
		Mockito.verify(eventAppService).complete(eventId, path);
		Mockito.verify(eventAdapter).adapt(eventMocked);
	}
}