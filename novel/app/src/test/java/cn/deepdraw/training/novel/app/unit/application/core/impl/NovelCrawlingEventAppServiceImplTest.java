package cn.deepdraw.training.novel.app.unit.application.core.impl;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.novel.app.application.core.impl.NovelCrawlingEventAppServiceImpl;
import cn.deepdraw.training.novel.app.domain.core.Novel;
import cn.deepdraw.training.novel.app.domain.core.NovelCrawlingEvent;
import cn.deepdraw.training.novel.app.domain.core.NovelCrawlingEventRepository;
import cn.deepdraw.training.novel.app.domain.core.NovelCrawlingEventService;
import cn.deepdraw.training.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.novel.app.infrastructure.mq.NovelRocketmqProducerSetting;
import cn.deepdraw.training.novel.app.infrastructure.mq.RocketmqMessagerProxy;

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
		
		Long novelId = 123L, version = 234L;
		String site = "BIQUGE", link = "link";
		NovelCrawlingEvent eventMocked = Mockito.mock(NovelCrawlingEvent.class);
		Mockito.when(eventRepo.create(Mockito.any())).thenReturn(eventMocked);
		
		NovelCrawlingEvent event = serviceImpl.create(novelId, site, version, link);
		
		Assert.assertNotNull(event);
		Mockito.verify(eventRepo).create(Mockito.any());
	}
	
	@Test
	public void should_return_a_not_null_instance_when_publish_method_called() {
		
		Long eventId = 123L, novelId = 234L, version = 345L;
		NovelCrawlingEvent eventMocked = Mockito.mock(NovelCrawlingEvent.class);
		Mockito.when(eventMocked.novelId()).thenReturn(novelId);
		Mockito.when(eventMocked.site()).thenReturn("BIQUGE");
		Mockito.when(eventMocked.version()).thenReturn(version);
		Mockito.when(eventRepo.findByEntityId(eventId)).thenReturn(eventMocked);
		
		Novel novelMocked = Mockito.mock(Novel.class);
		Mockito.when(novelRepo.findByEntityId(novelId)).thenReturn(novelMocked);
		Mockito.when(eventService.publish(novelMocked, "BIQUGE", version)).thenReturn(eventMocked);
		
		NovelCrawlingEvent event = serviceImpl.publish(eventId);

		Assert.assertNotNull(event);
		Mockito.verify(eventRepo).findByEntityId(eventId);
		Mockito.verify(novelRepo).findByEntityId(Mockito.anyLong());
		Mockito.verify(eventService).publish(Mockito.any(), Mockito.any(), Mockito.any());
	}
	
	@Test
	public void should_throw_exception_when_publish_method_called_but_event_id_not_found() {
		
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("event_id_not_found");
		
		Long eventId = 123L;
		Mockito.when(eventRepo.findByEntityId(eventId)).thenReturn(null);
		
		serviceImpl.publish(eventId);
	}
	
	@Test
	public void should_return_a_not_null_instance_when_complete_method_called() {
		
		Long eventId = 123L;
		String path = "path";
		NovelCrawlingEvent eventMocked = Mockito.mock(NovelCrawlingEvent.class);
		Mockito.when(eventRepo.findByEntityId(eventId)).thenReturn(eventMocked);
		Mockito.when(eventService.complete(eventMocked, path)).thenReturn(eventMocked);
		
		NovelCrawlingEvent event = serviceImpl.complete(eventId, path);

		Assert.assertNotNull(event);
		Mockito.verify(eventRepo).findByEntityId(eventId);
		Mockito.verify(eventService).complete(eventMocked, path);
	}
	
	@Test
	public void should_throw_exception_when_complete_method_called_but_event_id_not_found() {
		
		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("event_id_not_found");
		
		Long eventId = 123L;
		String path = "path";
		Mockito.when(eventRepo.findByEntityId(eventId)).thenReturn(null);
		
		serviceImpl.complete(eventId, path);
	}
}