package cn.deepdraw.training.crawler.novel.app.unit.domain.core;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelService;
import cn.deepdraw.training.crawler.novel.app.infrastructure.dubbo.proxy.crawler.NovelCrawlerApiProxy;

/**
 * NovelService Test
 * @author huangjiancheng
 * @Date 2020-11-27
 */
@RunWith(MockitoJUnitRunner.class)
public class NovelServiceTest {

	@InjectMocks
	private NovelService service;

	@Mock
	private NovelRepository novelRepo;
	
	@Mock
	private NovelCrawlerApiProxy crawler;
	
	@Test
	public void should_return_a_not_null_instance_when_method_crawl_called_if_novel_donot_exist() {
		
		String site = "BIQUGE";
		String link = "link";
		Novel novelMocked = Mockito.mock(Novel.class);
		Mockito.when(novelMocked.name()).thenReturn("name");
		Mockito.when(novelMocked.author()).thenReturn("author");
		Mockito.when(crawler.crawl(site, 234L, link)).thenReturn(novelMocked);
		Mockito.when(novelRepo.findByUnique(novelMocked.name(), novelMocked.author())).thenReturn(null);
		Mockito.when(novelRepo.create(novelMocked)).thenReturn(novelMocked);
		
		Novel novel = service.crawl(site, 234L, link);
		
		Assert.assertNotNull(novel);
		Mockito.verify(crawler).crawl(site, 234L, link);
		Mockito.verify(novelRepo).findByUnique(novelMocked.name(), novelMocked.author());
		Mockito.verify(novelRepo).create(novelMocked);
	}
	
	@Test
	public void should_return_a_not_null_instance_when_method_crawl_called_if_novel_already_exist() {
		
		String site = "BIQUGE";
		String link = "link";
		Novel novelMocked = Mockito.mock(Novel.class);
		Mockito.when(novelMocked.name()).thenReturn("name");
		Mockito.when(novelMocked.author()).thenReturn("author");
		LinkAddr addrMocked = Mockito.mock(LinkAddr.class);
		Mockito.when(novelMocked.addrOf(site, 234L)).thenReturn(addrMocked);
		Mockito.when(crawler.crawl(site, 234L, link)).thenReturn(novelMocked);

		Novel persistentMocked = Mockito.mock(Novel.class);
		Mockito.when(persistentMocked.updateAddr(addrMocked)).thenReturn(persistentMocked);
		Mockito.when(novelRepo.findByUnique(novelMocked.name(), novelMocked.author())).thenReturn(persistentMocked);
		Mockito.when(novelRepo.update(persistentMocked)).thenReturn(persistentMocked);
		
		Novel novel = service.crawl(site, 234L, link);
		
		Assert.assertNotNull(novel);
		Mockito.verify(crawler).crawl(site, 234L, link);
		Mockito.verify(novelRepo).findByUnique(novelMocked.name(), novelMocked.author());
		Mockito.verify(novelRepo).update(persistentMocked);
	}
}