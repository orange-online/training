package cn.deepdraw.training.crawler.novel.app.unit.domain.core;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEvent;

/**
 * NovelCrawlingEvent Test
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public class NovelCrawlingEventTest {

	@Test
	public void should_return_a_not_null_instance_when_all_args_are_legal() {
		
		NovelCrawlingEvent event = NovelCrawlingEvent.of(123L, Site.BIQUGE, "chapter_link");
		Assert.assertNotNull(event);
		Assert.assertEquals(123L, event.novelId().longValue());
		Assert.assertSame(Site.BIQUGE, event.site());
		Assert.assertEquals(Site.BIQUGE.toString(), event.siteString());
		Assert.assertEquals("chapter_link", event.link());
		Assert.assertFalse(event.published());
		Assert.assertFalse(event.completed());
	}
	
	@Test
	public void should_work_fine_when_method_publish_called() {
		
		NovelCrawlingEvent event = NovelCrawlingEvent.of(123L, Site.BIQUGE, "chapter_link");
		Assert.assertTrue(event.publish().published());
	}
	
	@Test
	public void should_work_fine_when_method_complete_called() {
		
		NovelCrawlingEvent event = NovelCrawlingEvent.of(123L, Site.BIQUGE, "chapter_link");
		Assert.assertTrue(event.complete().completed());
	}
	
	@Test
	public void should_return_a_not_blank_string_when_method_toString_called() {
		
		NovelCrawlingEvent event = NovelCrawlingEvent.of(123L, Site.BIQUGE, "chapter_link");
		Assert.assertTrue(StringUtils.isNotBlank(event.toString()));
	}
	
	@Test
	public void should_be_same_with_the_hashCode_of_toString_value_when_method_hashCode_called() {
		
		NovelCrawlingEvent event = NovelCrawlingEvent.of(123L, Site.BIQUGE, "chapter_link");
		Assert.assertTrue(event.hashCode() == event.toString().hashCode());
	}
}