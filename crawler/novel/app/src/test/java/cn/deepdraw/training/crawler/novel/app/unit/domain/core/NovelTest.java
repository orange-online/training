package cn.deepdraw.training.crawler.novel.app.unit.domain.core;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;

/**
 * Novel Test
 * @author huangjiancheng
 * 2020-06-18
 */
public class NovelTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void should_return_a_not_null_instance_when_of_factory_method_called_if_args_are_legal() {

		String name = "novel_name";
		String author = "novel_author";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "novel_addr", "novel_path");
		Novel novel = Novel.of(name, author, addr);
		Assert.assertNotNull(novel);

		Assert.assertSame(novel.name(), name);
		Assert.assertSame(novel.author(), author);
		Assert.assertThat(novel.addrs(), Matchers.hasSize(1));
		Assert.assertThat(novel.addrs(), Matchers.contains(addr));
	}

	@Test
	public void should_throw_exception_when_of_factory_method_called_but_name_is_illegal() {

		String name = "  ";
		String author = "novel_author";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "novel_addr", "novel_path");

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("name_cannot_be_blank");
		Novel.of(name, author, addr);
	}

	@Test
	public void should_throw_exception_when_of_factory_method_called_but_author_is_illegal() {

		String name = "novel_name";
		String author = "  ";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "novel_addr", "novel_path");

		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("author_cannot_be_blank");
		Novel.of(name, author, addr);
	}

	@Test
	public void should_throw_exception_when_of_factory_method_called_but_addr_is_illegal() {

		String name = "novel_name";
		String author = "novel_author";
		LinkAddr addr = null;

		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("addr_cannot_be_null");
		Novel.of(name, author, addr);
	}

	@Test
	public void should_return_the_specified_addr_when_addrOf_method_called_if_site_already_exists() {

		String name = "novel_name";
		String author = "novel_author";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "novel_addr", "novel_path");
		Novel novel = Novel.of(name, author, addr);
		Assert.assertSame(addr, novel.addrOf(Site.BIQUGE));
	}

	@Test
	public void should_return_a_null_instance_when_addrOf_method_called_if_site_donot_exist() {

		String name = "novel_name";
		String author = "novel_author";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "novel_addr", "novel_path");
		Novel novel = Novel.of(name, author, addr);
		Assert.assertNull(novel.addrOf(Site.LIUDATXT));
	}

	@Test
	public void should_add_a_new_addr_when_updateLink_method_called_if_site_of_addr_donot_exist() {

		String name = "novel_name";
		String author = "novel_author";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "novel_addr", "novel_path");
		LinkAddr addrNonexistent = LinkAddr.of(Site.LIUDATXT, "novel_addr", "novel_path");
		Novel novel = Novel.of(name, author, addr).updateAddr(addrNonexistent);

		Assert.assertThat(novel.addrs(), Matchers.contains(addr, addrNonexistent));
		Assert.assertThat(novel.addrs(), Matchers.hasSize(2));
	}

	@Test
	public void should_add_a_new_addr_when_updateLink_method_called_if_site_of_addr_already_exists() {

		String name = "novel_name";
		String author = "novel_author";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "novel_addr", "novel_path");
		LinkAddr addrExistent = LinkAddr.of(Site.BIQUGE, "novel_addr_existent", "novel_path_existent");
		Novel novel = Novel.of(name, author, addr).updateAddr(addrExistent);

		Assert.assertThat(novel.addrs(), Matchers.contains(addr));
		Assert.assertThat(novel.addrs(), Matchers.hasSize(1));
	}

	@Test
	public void should_update_the_path_of_the_addr_specified_when_updateLinkPath_method_called_if_site_already_exists() {

		String name = "novel_name";
		String author = "novel_author";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "novel_addr", "novel_path");
		Novel novel = Novel.of(name, author, addr).updateAddrPath(Site.BIQUGE, "novel_path_new");
		Assert.assertSame("novel_path_new", novel.addrOf(Site.BIQUGE).path());
	}

	@Test
	public void should_throw_exception_when_updateLinkPath_method_called_but_site_donot_exist() {

		String name = "novel_name";
		String author = "novel_author";
		LinkAddr addr = LinkAddr.of(Site.BIQUGE, "novel_addr", "novel_path");

		expectedException.expect(NullPointerException.class);
		expectedException.expectMessage("site_not_found");
		Novel.of(name, author, addr).updateAddrPath(Site.LIUDATXT, "novel_path_new");
	}
}