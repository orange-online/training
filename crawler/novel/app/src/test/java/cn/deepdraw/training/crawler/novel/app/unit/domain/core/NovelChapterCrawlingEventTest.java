package cn.deepdraw.training.crawler.novel.app.unit.domain.core;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterCrawlingEvent;

/**
 * NovelChapterCrawlingEvent Test
 * @author huangjiancheng
 * @Date 2020-11-26
 */
public class NovelChapterCrawlingEventTest {

	@Test
	public void should_return_a_not_null_instance_when_all_args_are_legal() {
		
		NovelChapterCrawlingEvent event = NovelChapterCrawlingEvent.of(123L, "BIQUGE", 345L, 234L, "chapter_link");
		Assert.assertNotNull(event);
		Assert.assertEquals(123L, event.novelId().longValue());
		Assert.assertSame("BIQUGE", event.site());
		Assert.assertEquals("BIQUGE".toString(), event.site());
		Assert.assertEquals(234L, event.chapterId().longValue());
		Assert.assertEquals("chapter_link", event.link());
		Assert.assertFalse(event.published());
		Assert.assertFalse(event.completed());
	}
	
	@Test
	public void should_work_fine_when_method_publish_called() {
		
		NovelChapterCrawlingEvent event = NovelChapterCrawlingEvent.of(123L, "BIQUGE", 345L, 234L, "chapter_link");
		Assert.assertTrue(event.publish().published());
	}
	
	@Test
	public void should_work_fine_when_method_complete_called() {
		
		NovelChapterCrawlingEvent event = NovelChapterCrawlingEvent.of(123L, "BIQUGE", 345L, 234L, "chapter_link");
		Assert.assertTrue(event.complete().completed());
	}
	
	@Test
	public void should_return_a_not_blank_string_when_method_toString_called() {
		
		NovelChapterCrawlingEvent event = NovelChapterCrawlingEvent.of(123L, "BIQUGE", 345L, 234L, "chapter_link");
		Assert.assertTrue(StringUtils.isNotBlank(event.toString()));
	}
	
	@Test
	public void should_be_same_with_the_hashCode_of_toString_value_when_method_hashCode_called() {
		
		NovelChapterCrawlingEvent event = NovelChapterCrawlingEvent.of(123L, "BIQUGE", 345L, 234L, "chapter_link");
		Assert.assertTrue(event.hashCode() == event.toString().hashCode());
	}
}