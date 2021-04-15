package cn.deepdraw.training.crawler.novel.app.unit.application.core.impl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.novel.app.application.core.impl.NovelCrawlingEventAppServiceImpl;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEvent;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEventRepository;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEventService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.crawler.novel.app.infrastructure.mq.NovelRocketmqProducerSetting;
import cn.deepdraw.training.crawler.novel.app.infrastructure.mq.RocketmqMessagerProxy;

/**
 * NovelCrawlingEventAppServiceImpl Test
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@RunWith(MockitoJUnitRunner.class)
public class NovelCrawlingEventAppServiceImplTest {

	@InjectMocks
	private NovelCrawlingEventAppServiceImpl serviceImpl;
	
	@Mock
	private NovelCrawlingEventRepository eventRepo;
	
	@Mock
	private NovelCrawlingEventService eventService;
	
	@Mock
	private NovelRepository novelRepo;
	
	@Mock
	private NovelRocketmqProducerSetting setting;
	
	@Mock
	private RocketmqMessagerProxy messageProxy;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void should_return_a_not_null_instance_when_create_method_called() {
		
		String novelId = "novel_id", site = "BIQUGE", link = "link";
		NovelCrawlingEvent eventMocked = Mockito.mock(NovelCrawlingEvent.class);
		Mockito.when(eventRepo.generateIdString()).thenReturn("event_id");
		Mockito.when(eventRepo.create(Mockito.any())).thenReturn(eventMocked);
		
		NovelCrawlingEvent event = serviceImpl.create(novelId, site, link);
		
		Assert.assertNotNull(event);
		Mockito.verify(eventRepo).generateIdString();
		Mockito.verify(eventRepo).create(Mockito.any());
	}
	
	@Test
	public void should_return_a_not_null_instance_when_publish_method_called() {
		
		String eventId = "event_id", novelId = "novel_id";
		NovelCrawlingEvent eventMocked = Mockito.mock(NovelCrawlingEvent.class);
		Mockito.when(eventMocked.novelId()).thenReturn(novelId);
		Mockito.when(eventMocked.site()).thenReturn(Site.BIQUGE);
		Mockito.when(eventRepo.findByEventId(eventId)).thenReturn(eventMocked);
		
		Novel novelMocked = Mockito.mock(Novel.class);
		Mockito.when(novelRepo.findByNovelId(novelId)).thenReturn(novelMocked);
		Mockito.when(eventService.publish(novelMocked, Site.BIQUGE)).thenReturn(eventMocked);
		
		NovelCrawlingEvent event = serviceImpl.publish(eventId);

		Assert.assertNotNull(event);
		Mockito.verify(eventRepo).findByEventId(eventId);
		Mockito.verify(novelRepo).findByNovelId(Mockito.anyString());
		Mockito.verify(eventService).publish(Mockito.any(), Mockito.any());
	}
	
	@Test
	public void should_throw_exception_when_publish_method_called_but_event_id_not_found() {
		
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("event_id_not_found");
		
		String eventId = "event_id";
		Mockito.when(eventRepo.findByEventId(eventId)).thenReturn(null);
		
		serviceImpl.publish(eventId);
	}
	
	@Test
	public void should_return_a_not_null_instance_when_complete_method_called() {
		
		String eventId = "event_id", path = "path";
		NovelCrawlingEvent eventMocked = Mockito.mock(NovelCrawlingEvent.class);
		Mockito.when(eventRepo.findByEventId(eventId)).thenReturn(eventMocked);
		Mockito.when(eventService.complete(eventMocked, path)).thenReturn(eventMocked);
		
		NovelCrawlingEvent event = serviceImpl.complete(eventId, path);

		Assert.assertNotNull(event);
		Mockito.verify(eventRepo).findByEventId(eventId);
		Mockito.verify(eventService).complete(eventMocked, path);
	}
	
	@Test
	public void should_throw_exception_when_complete_method_called_but_event_id_not_found() {
		
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("event_id_not_found");
		
		String eventId = "event_id", path = "path";
		Mockito.when(eventRepo.findByEventId(eventId)).thenReturn(null);
		
		serviceImpl.complete(eventId, path);
	}
}