package cn.deepdraw.training.crawler.novel.app.interfaces.core.dubbo;

import org.apache.commons.lang3.EnumUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.api.NovelApi;
import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.api.dto.NovelQueryDTO;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelAdapter;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelQueryBuilder;
import cn.deepdraw.training.framework.api.adapter.page.PageRequestAdapter;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelApi MotanService
 * @author huangjiancheng
 * 2020-06-09
 */
@DubboService
@Transactional
public class NovelApiDubboService implements NovelApi {

	@Autowired
	private NovelAdapter novelAdapter;

	@Autowired
	private NovelRepository novelRepo;

	@Autowired
	private PageRequestAdapter pageReqAdapter;

	@Autowired
	private NovelQueryBuilder queryBuilder;

	@Autowired
	private NovelAppService appService;

	@Override
	public NovelDTO create(String name, String author, LinkAddress link) throws WebAppRuntimeException {

		return novelAdapter.adapt(appService.create(name, author, LinkAddr.of(EnumUtils.getEnum(Site.class, link.getSite()), link.getLink(), link.getPath())));
	}

	@Override
	public NovelDTO updateAddress(String novelId, LinkAddress link) throws WebAppRuntimeException {

		return novelAdapter.adapt(appService.updateLink(novelId, LinkAddr.of(EnumUtils.getEnum(Site.class, link.getSite()), link.getLink(), link.getPath())));
	}

	@Override
	public NovelDTO updatePath(String novelId, String site, String path) throws WebAppRuntimeException {

		return novelAdapter.adapt(appService.updatePath(novelId, EnumUtils.getEnum(Site.class, site), path));
	}

	@Override
	public NovelDTO findByNovelId(String novelId) {

		return novelAdapter.adapt(novelRepo.findByNovelId(novelId));
	}

	@Override
	public NovelDTO findByNameAndAuthor(String name, String author) {

		return novelAdapter.adapt(novelRepo.findByUnique(name, author));
	}

	@Override
	public PageDTO<NovelDTO> findByPage(NovelQueryDTO query, PageRequest request) {

		return novelAdapter.adapt(novelRepo.findByPage(queryBuilder.build(query), pageReqAdapter.adapt(request)));
	}

	@Override
	public NovelDTO crawl(String site, String url) {

		return novelAdapter.adapt(appService.crawl(site, url));
	}

	@Override
	public NovelDTO packaging(String novelId, String site) {

		return novelAdapter.adapt(appService.packaging(novelId, Site.valueOf(site)));
	}
}