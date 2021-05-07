package cn.deepdraw.training.crawler.novel.app.unit.interfaces.core.motan;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.api.dto.NovelPageRequest;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelPageConv;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelPageRequestBuilder;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.dubbo.NovelApiDubboService;
import cn.deepdraw.training.framework.api.conv.page.PageRequestConv;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * @author xjn
 * @description NovelApiMotanServiceTest
 * @date 2020/11/12
 */
@RunWith(MockitoJUnitRunner.class)
public class NovelApiDubboServiceTest {

	private Long id;

	private String name;

	private String author;

	private LinkAddr link;

	private LinkAddress address;

	private Novel novel;

	private NovelDTO dto;

	@Mock
	private NovelPageConv novelConv;

	@Mock
	private NovelRepository novelRepo;

	@Mock
	private PageRequestConv pageReqConv;

	@Mock
	private NovelPageRequestBuilder queryBuilder;

	@Mock
	private NovelAppService appService;

	@InjectMocks
	private NovelApiDubboService service;

	@Before
	public void init() {

		id = 123L;
		name = "name";
		author = "author";
		link = LinkAddr.of("BIQUGE", 234L, "link", null);
		address = LinkAddress.of("BIQUGE", 234L, "link");
		novel = Novel.of(name, author, link);
		novel.entityId(id);
		dto = new NovelDTO();
		dto.setEntityId(id);
		dto.setName(name);
		dto.setAuthor(author);
		dto.setAddresses(Arrays.asList(address));
	}

	@Test
	public void create_happyPath() {

		when(appService.create(name, author, link)).thenReturn(novel);
		when(novelConv.done(novel)).thenReturn(dto);

		NovelDTO expected = service.create(name, author, address);

		assertEquals(id, expected.getEntityId());
		assertEquals(name, expected.getName());
		assertEquals(author, expected.getAuthor());
		assertNotNull(expected.addressOf("BIQUGE"));
	}

	@Test
	public void updateAddress_happyPath() throws WebAppRuntimeException {

		when(appService.updateLink(id, link)).thenReturn(novel);
		when(novelConv.done(novel)).thenReturn(dto);

		NovelDTO expected = service.updateAddress(id, address);

		assertNotNull(expected.addressOf("BIQUGE"));
	}

	@Test
	public void updatePath_happyPath() throws WebAppRuntimeException {

		Novel novel = Novel.of(name, author, LinkAddr.of("BIQUGE", 234L, "link", "path"));
		novel.entityId(id);
		NovelDTO dto = new NovelDTO();
		dto.setEntityId(id);
		dto.setName(name);
		dto.setAuthor(author);
		dto.setAddresses(Arrays.asList(LinkAddress.of("BIQUGE", 234L, "link", "path")));

		when(appService.updatePath(id, "BIQUGE", 234L, "path")).thenReturn(novel);
		when(novelConv.done(novel)).thenReturn(dto);

		NovelDTO expected = service.updateAddressPath(id, "BIQUGE", 234L, "path");

		assertEquals("path", expected.addressOf("BIQUGE").getPath());
	}

	@Test
	public void findByNovelId_happyPath() {

		when(novelRepo.findByEntityId(id)).thenReturn(novel);
		when(novelConv.done(novel)).thenReturn(dto);

		NovelDTO expected = service.findByNovelId(id);

		assertEquals(dto, expected);
	}

	@Test
	public void findByNameAndAuthor_happyPath() {

		when(novelRepo.findByUnique(name, author)).thenReturn(novel);
		when(novelConv.done(novel)).thenReturn(dto);

		NovelDTO expected = service.findByNameAndAuthor(name, author);

		assertEquals(dto, expected);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void findByPage_happyPath() {

		NovelPageRequest query = mock(NovelPageRequest.class);
		PageRequest request = mock(PageRequest.class);
		Specification specification = mock(Specification.class);
		org.springframework.data.domain.PageRequest pageRequest = mock(org.springframework.data.domain.PageRequest.class);
		PageDTO<NovelDTO> dtos = new PageDTO<>();
		dtos.setData(Arrays.asList(dto));
		Page<Novel> novels = Page.empty();

		when(queryBuilder.build(query)).thenReturn(specification);
		when(pageReqConv.done(request)).thenReturn(pageRequest);
		when(novelRepo.findByPage(specification, pageRequest)).thenReturn(novels);
		when(novelConv.done(novels)).thenReturn(dtos);

		assertThat(service.findByPage(query, request).getData(), hasItem(dto));
	}

	@Test
	public void crawl_happyPath() {

		String site = "BIQUGE";
		String url = "url";
		when(appService.crawl(site, url)).thenReturn(novel);
		when(novelConv.done(novel)).thenReturn(dto);

		NovelDTO expected = service.crawl(site, url);

		assertEquals(dto, expected);
	}
}
