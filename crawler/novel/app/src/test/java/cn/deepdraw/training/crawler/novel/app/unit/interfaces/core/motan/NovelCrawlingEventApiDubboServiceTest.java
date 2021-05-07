package cn.deepdraw.training.crawler.novel.app.unit.interfaces.core.motan;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.novel.api.dto.NovelCrawlingEventDTO;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelCrawlingEventAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEvent;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelCrawlingEventConv;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.dubbo.NovelCrawlingEventApiDubboService;

/**
 * NovelCrawlingEventApiMotanService Test
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@RunWith(MockitoJUnitRunner.class)
public class NovelCrawlingEventApiDubboServiceTest {

	@InjectMocks
	private NovelCrawlingEventApiDubboService apiMotanService;
	
	@Mock
	private NovelCrawlingEventAppService eventAppService;
	
	@Mock
	private NovelCrawlingEventConv eventAdapter;
	
	@Test
	public void should_return_a_not_null_instance_when_method_create_called() {
		
		Long novelId = 123L, version = 234L;
		String site = "site", link = "link";
		NovelCrawlingEvent eventMocked = Mockito.mock(NovelCrawlingEvent.class);
		Mockito.when(eventAppService.create(novelId, site, version, link)).thenReturn(eventMocked);
		NovelCrawlingEventDTO eventDTOMocked = Mockito.mock(NovelCrawlingEventDTO.class);
		Mockito.when(eventAdapter.done(eventMocked)).thenReturn(eventDTOMocked);
		
		NovelCrawlingEventDTO eventDTO = apiMotanService.create(novelId, site, version, link);
		
		Assert.assertSame(eventDTOMocked, eventDTO);
		Mockito.verify(eventAppService).create(novelId, site, version, link);
		Mockito.verify(eventAdapter).done(eventMocked);
	}
	
	@Test
	public void should_return_a_not_null_instance_when_method_publish_called() {
		
		Long eventId = 123L;
		NovelCrawlingEvent eventMocked = Mockito.mock(NovelCrawlingEvent.class);
		Mockito.when(eventAppService.publish(eventId)).thenReturn(eventMocked);
		NovelCrawlingEventDTO eventDTOMocked = Mockito.mock(NovelCrawlingEventDTO.class);
		Mockito.when(eventAdapter.done(eventMocked)).thenReturn(eventDTOMocked);
		
		NovelCrawlingEventDTO eventDTO = apiMotanService.publish(eventId);
		
		Assert.assertSame(eventDTOMocked, eventDTO);
		Mockito.verify(eventAppService).publish(eventId);
		Mockito.verify(eventAdapter).done(eventMocked);
	}
	
	@Test
	public void should_return_a_not_null_instance_when_method_complete_called() {

		Long eventId = 123L;
		String path = "path";
		NovelCrawlingEvent eventMocked = Mockito.mock(NovelCrawlingEvent.class);
		Mockito.when(eventAppService.complete(eventId, path)).thenReturn(eventMocked);
		NovelCrawlingEventDTO eventDTOMocked = Mockito.mock(NovelCrawlingEventDTO.class);
		Mockito.when(eventAdapter.done(eventMocked)).thenReturn(eventDTOMocked);
		
		NovelCrawlingEventDTO eventDTO = apiMotanService.complete(eventId, path);
		
		Assert.assertSame(eventDTOMocked, eventDTO);
		Mockito.verify(eventAppService).complete(eventId, path);
		Mockito.verify(eventAdapter).done(eventMocked);
	}
}