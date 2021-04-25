package cn.deepdraw.training.crawler.novel.app.interfaces.core.dubbo;

import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.api.NovelChapterApi;
import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterDTO;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterQueryDTO;
import cn.deepdraw.training.crawler.novel.app.application.core.NovelChapterAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr.Site;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapter;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterRepository;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelChapterPageConv;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelChapterQueryBuilder;
import cn.deepdraw.training.framework.api.conv.page.PageRequestConv;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelChapterApi MotanService
 * @author huangjiancheng
 * 2020-07-22
 */
@DubboService
@Transactional
public class NovelChapterApiDubboService implements NovelChapterApi {

	@Autowired
	private NovelChapterAppService appService;

	@Autowired
	private NovelChapterRepository chapterRepo;

	@Autowired
	private NovelChapterPageConv chapterConv;

	@Autowired
	private NovelChapterQueryBuilder queryBuilder;

	@Autowired
	private PageRequestConv pageReqConv;

	@Autowired
	private NovelRepository novelRepo;

	@Override
	public NovelChapterDTO create(Long novelId, String name, LinkAddress address, Integer index) throws WebAppRuntimeException {

		LinkAddr addr = LinkAddr.of(EnumUtils.getEnum(Site.class, address.getSite()), address.getLink(), address.getPath());
		return chapterConv.done(appService.create(NovelChapter.of(novelRepo.findByEntityId(novelId), name, addr, index)));
	}

	@Override
	public NovelChapterDTO updatePath(Long novelId, Long chapterId, String path) throws WebAppRuntimeException {

		return chapterConv.done(appService.updatePath(chapterRepo.findByChapterId(novelId, chapterId), path));
	}

	@Override
	public NovelChapterDTO findByChapterId(Long novelId, Long chapterId) {

		return chapterConv.done(chapterRepo.findByChapterId(novelId, chapterId));
	}

	@Override
	public List<NovelChapterDTO> findByNovelId(Long novelId, String site) {

		return chapterConv.done(chapterRepo.findByNovelId(novelId, Site.valueOf(site)));
	}

	@Override
	public PageDTO<NovelChapterDTO> findByPage(NovelChapterQueryDTO query, PageRequest request) {

		return chapterConv.done(chapterRepo.findByPage(queryBuilder.build(query), pageReqConv.done(request)));
	}
}