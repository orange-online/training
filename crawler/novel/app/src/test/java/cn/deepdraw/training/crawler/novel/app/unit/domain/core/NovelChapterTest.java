package cn.deepdraw.training.crawler.novel.app.unit.domain.core;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapter;

/**
 * NovelChapter Test
 * 
 * @author huangjiancheng 2020-07-23
 */
public class NovelChapterTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Novel instanceOfNovel() {

		String novelId = "novel_id";
		String name = "novel_name";
		String author = "novel_author";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "novel_addr", "novel_path");
		return Novel.of(novelId, name, author, addr);
	}

	@Test
	public void should_return_a_new_instance_of_NovelChapter_when_of_method_called_and_arguments_are_legal() {

		Novel novel = instanceOfNovel();
		String chapterId  = "chapter_id", name = "chapter_name";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "chapter_addr", "chapter_path");
		NovelChapter chapter = NovelChapter.of(novel, chapterId, name, addr);

		Assert.assertNotNull(chapter);
		Assert.assertSame(novel, chapter.novel());
		Assert.assertSame(chapterId, chapter.chapterId());
		Assert.assertSame(name, chapter.name());
		Assert.assertSame(addr, chapter.addr());
		Assert.assertSame(addr.site(), chapter.site());
		Assert.assertSame(addr.link(), chapter.link());
		Assert.assertSame(addr.path(), chapter.path());
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_novel_is_null() {

		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("novel_id_cannot_be_null");

		Novel novel = null;
		String chapterId  = "chapter_id", name = "chapter_name";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "chapter_addr", "chapter_path");
		NovelChapter.of(novel, chapterId, name, addr);
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_chapterId_is_null() {

		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("chapter_id_cannot_be_blank");

		Novel novel = instanceOfNovel();
		String chapterId  = null, name = "chapter_name";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "chapter_addr", "chapter_path");
		NovelChapter.of(novel, chapterId, name, addr);
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_chapterId_is_blank() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("chapter_id_cannot_be_blank");

		Novel novel = instanceOfNovel();
		String chapterId  = "  ", name = "chapter_name";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "chapter_addr", "chapter_path");
		NovelChapter.of(novel, chapterId, name, addr);
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_name_is_null() {

		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("name_cannot_be_blank");

		Novel novel = instanceOfNovel();
		String chapterId  = "chapter_id", name = null;
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "chapter_addr", "chapter_path");
		NovelChapter.of(novel, chapterId, name, addr);
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_name_is_blank() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("name_cannot_be_blank");

		Novel novel = instanceOfNovel();
		String chapterId  = "chapter_id", name = "  ";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "chapter_addr", "chapter_path");
		NovelChapter.of(novel, chapterId, name, addr);
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_addr_is_null() {

		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("addr_cannot_be_null");

		Novel novel = instanceOfNovel();
		String chapterId  = "chapter_id", name = "chapter_name";
		LinkAddr addr = null;
		NovelChapter.of(novel, chapterId, name, addr);
	}

	@Test
	public void should_return_the_instance_whose_path_modified_when_updateAddrPath_method_called() {

		Novel novel = instanceOfNovel();
		String chapterId  = "chapter_id", name = "chapter_name";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "chapter_addr", "chapter_path");
		NovelChapter chapter = NovelChapter.of(novel, chapterId, name, addr).updateAddrPath("chapter_path1");

		Assert.assertNotEquals("chapter_path", chapter.path());
		Assert.assertEquals("chapter_path1", chapter.path());
	}
}