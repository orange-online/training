package cn.deepdraw.training.crawler.novel.app.interfaces.core.motan;

import java.util.List;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.weibo.api.motan.config.springsupport.annotation.MotanService;

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
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelChapterAdapter;
import cn.deepdraw.training.crawler.novel.app.interfaces.core.NovelChapterQueryBuilder;
import cn.deepdraw.training.framework.api.adapter.page.PageRequestAdapter;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelChapterApi MotanService
 * @author huangjiancheng
 * 2020-07-22
 */
@Transactional
@MotanService(basicService = "chapterBaseService")
public class NovelChapterApiMotanService implements NovelChapterApi {

	@Autowired
	private NovelChapterAppService appService;

	@Autowired
	private NovelChapterRepository chapterRepo;

	@Autowired
	private NovelChapterAdapter chapterAdapter;

	@Autowired
	private NovelChapterQueryBuilder queryBuilder;

	@Autowired
	private PageRequestAdapter pageReqAdapter;

	@Autowired
	private NovelRepository novelRepo;

	@Override
	public NovelChapterDTO create(String novelId, String name, LinkAddress address) throws WebAppRuntimeException {

		LinkAddr addr = LinkAddr.of(EnumUtils.getEnum(Site.class, address.getSite()), address.getLink(), address.getPath());
		return chapterAdapter.adapt(appService.create(NovelChapter.of(novelRepo.findByNovelId(novelId), chapterRepo.generateIdString(), name, addr)));
	}

	@Override
	public NovelChapterDTO updatePath(String novelId, String chapterId, String path) throws WebAppRuntimeException {

		return chapterAdapter.adapt(appService.updatePath(chapterRepo.findByChapterId(novelId, chapterId), path));
	}

	@Override
	public NovelChapterDTO findByChapterId(String novelId, String chapterId) {

		return chapterAdapter.adapt(chapterRepo.findByChapterId(novelId, chapterId));
	}

	@Override
	public List<NovelChapterDTO> findByNovelId(String novelId, String site) {

		return chapterAdapter.adapt(chapterRepo.findByNovelId(novelId, Site.valueOf(site)));
	}

	@Override
	public PageDTO<NovelChapterDTO> findByPage(NovelChapterQueryDTO query, PageRequest request) {

		return chapterAdapter.adapt(chapterRepo.findByPage(queryBuilder.build(query), pageReqAdapter.adapt(request)));
	}
}