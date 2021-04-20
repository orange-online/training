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

import cn.deepdraw.training.crawler.novel.app.application.core.impl.NovelChapterCrawlingEventAppServiceImpl;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEvent;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEventRepository;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEventService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterRepository;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEventService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;

/**
 * NovelChapterCrawlingEventAppServiceImpl Test
 * @author huangjiancheng
 * @Date 2020-11-26
 */
@RunWith(MockitoJUnitRunner.class)
public class NovelChapterCrawlingEventAppServiceImplTest {

	@InjectMocks
	private NovelChapterCrawlingEventAppServiceImpl serviceImpl;
	
	@Mock
	private NovelChapterCrawlingEventRepository eventRepo;
	
	@Mock
	private NovelRepository novelRepo;
	
	@Mock
	private NovelChapterRepository chapterRepo;
	
	@Mock
	private NovelChapterCrawlingEventService eventService;
	
	@Mock
	private NovelPackagingEventService packagingEventService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void should_return_a_not_null_instance_when_create_method_called() {
		
		Site site = Site.BIQUGE;
		Long novelId = 123L, chapterId = 234L;
		String link = "link";
		NovelChapterCrawlingEvent eventMocked = Mockito.mock(NovelChapterCrawlingEvent.class);
		Mockito.when(eventRepo.create(Mockito.any())).thenReturn(eventMocked);
		
		NovelChapterCrawlingEvent event = serviceImpl.create(novelId, site, chapterId, link);
		
		Assert.assertNotNull(event);
		Mockito.verify(eventRepo).create(Mockito.any());
	}
	
	@Test
	public void should_return_a_not_null_instance_when_publish_method_called() {
		
		Long eventId = 123L;
		NovelChapterCrawlingEvent eventMocked = Mockito.mock(NovelChapterCrawlingEvent.class);
		Mockito.when(eventRepo.findByEntityId(eventId)).thenReturn(eventMocked);
		Mockito.when(eventService.publish(eventMocked)).thenReturn(eventMocked);
		
		NovelChapterCrawlingEvent event = serviceImpl.publish(eventId);

		Assert.assertNotNull(event);
		Assert.assertSame(eventMocked, event);
		Mockito.verify(eventRepo).findByEntityId(eventId);
		Mockito.verify(eventService).publish(eventMocked);
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
		NovelChapterCrawlingEvent eventMocked = Mockito.mock(NovelChapterCrawlingEvent.class);
		Mockito.when(eventMocked.novelId()).thenReturn(135L);
		Mockito.when(eventMocked.site()).thenReturn(Site.BIQUGE);
		Mockito.when(eventRepo.findByEntityId(eventId)).thenReturn(eventMocked);
		Mockito.when(eventService.complete(eventMocked, path)).thenReturn(eventMocked);
		Mockito.when(eventRepo.countByNovelAndCompleted(eventMocked.novelId(), Site.BIQUGE, false)).thenReturn(1L);
		
		NovelChapterCrawlingEvent event = serviceImpl.complete(eventId, path);

		Assert.assertNotNull(event);
		Mockito.verify(eventRepo).findByEntityId(eventId);
		Mockito.verify(eventService).complete(eventMocked, path);
		Mockito.verify(eventRepo).countByNovelAndCompleted(eventMocked.novelId(), Site.BIQUGE, false);
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