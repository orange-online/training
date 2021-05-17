package cn.deepdraw.training.novel.app.unit.domain.core;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cn.deepdraw.training.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.novel.app.domain.core.Novel;
import cn.deepdraw.training.novel.app.domain.core.NovelChapter;

/**
 * NovelChapter Test
 * 
 * @author huangjiancheng 2020-07-23
 */
public class NovelChapterTest {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	private Novel instanceOfNovel() {

		String name = "novel_name";
		String author = "novel_author";
		LinkAddr addr = LinkAddr.of("BIQUGE", 234L, "novel_addr", "novel_path");
		return Novel.of(name, author, addr);
	}

	@Test
	public void should_return_a_new_instance_of_NovelChapter_when_of_method_called_and_arguments_are_legal() {

		Novel novel = instanceOfNovel();
		String name = "chapter_name";
		LinkAddr addr = LinkAddr.of("BIQUGE", 234L, "chapter_addr", "chapter_path");
		NovelChapter chapter = NovelChapter.of(novel, name, addr, 1);

		Assert.assertNotNull(chapter);
		Assert.assertSame(novel, chapter.novel());
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
		String name = "chapter_name";
		LinkAddr addr = LinkAddr.of("BIQUGE", 234L, "chapter_addr", "chapter_path");
		NovelChapter.of(novel, name, addr, 1);
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_name_is_null() {

		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("name_cannot_be_blank");

		Novel novel = instanceOfNovel();
		String name = null;
		LinkAddr addr = LinkAddr.of("BIQUGE", 234L, "chapter_addr", "chapter_path");
		NovelChapter.of(novel, name, addr, 1);
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_name_is_blank() {

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("name_cannot_be_blank");

		Novel novel = instanceOfNovel();
		String name = "  ";
		LinkAddr addr = LinkAddr.of("BIQUGE", 234L, "chapter_addr", "chapter_path");
		NovelChapter.of(novel, name, addr, 1);
	}

	@Test
	public void should_throw_exception_when_of_method_called_but_addr_is_null() {

		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("addr_cannot_be_null");

		Novel novel = instanceOfNovel();
		String name = "chapter_name";
		LinkAddr addr = null;
		NovelChapter.of(novel, name, addr, 1);
	}

	@Test
	public void should_return_the_instance_whose_path_modified_when_updateAddrPath_method_called() {

		Novel novel = instanceOfNovel();
		String name = "chapter_name";
		LinkAddr addr = LinkAddr.of("BIQUGE", 234L, "chapter_addr", "chapter_path");
		NovelChapter chapter = NovelChapter.of(novel, name, addr, 1).updateAddrPath("chapter_path1");

		Assert.assertNotEquals("chapter_path", chapter.path());
		Assert.assertEquals("chapter_path1", chapter.path());
	}
}