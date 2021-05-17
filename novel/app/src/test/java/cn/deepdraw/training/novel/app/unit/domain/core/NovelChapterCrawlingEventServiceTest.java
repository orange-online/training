package cn.deepdraw.training.novel.app.unit.domain.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.novel.app.domain.core.NovelChapterCrawlingEvent;
import cn.deepdraw.training.novel.app.domain.core.NovelChapterCrawlingEventRepository;
import cn.deepdraw.training.novel.app.domain.core.NovelChapterCrawlingEventService;
import cn.deepdraw.training.novel.app.infrastructure.mq.NovelChapterRocketmqProducerSetting;
import cn.deepdraw.training.novel.app.infrastructure.mq.RocketmqMessagerProxy;

/**
 * NovelChapterCrawlingEventService Test
 * @author huangjiancheng
 * @Date 2020-11-29
 */
@RunWith(MockitoJUnitRunner.class)
public class NovelChapterCrawlingEventServiceTest {

	@InjectMocks
	private NovelChapterCrawlingEventService eventService;
	
	@Mock
	private NovelChapterCrawlingEventRepository eventRepo;
	
	@Mock
	private NovelChapterRocketmqProducerSetting setting;
	
	@Mock
	private RocketmqMessagerProxy messageProxy;
	
	@Test
	public void should_return_a_not_null_instance_when_method_publish_called() {
		
		NovelChapterCrawlingEvent eventMocked = Mockito.mock(NovelChapterCrawlingEvent.class);
		Mockito.when(eventMocked.publish()).thenReturn(eventMocked);
		Mockito.when(messageProxy.send(setting, eventMocked)).thenReturn(true);
		Mockito.when(eventRepo.update(eventMocked)).thenReturn(eventMocked);
		
		NovelChapterCrawlingEvent event = eventService.publish(eventMocked);
		
		Assert.assertNotNull(event);
		Assert.assertSame(eventMocked, event);
		Mockito.verify(messageProxy).send(setting, eventMocked);
		Mockito.verify(eventRepo).update(eventMocked);
	}
}