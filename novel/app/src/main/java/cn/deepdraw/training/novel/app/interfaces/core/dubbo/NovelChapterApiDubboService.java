package cn.deepdraw.training.novel.app.interfaces.core.dubbo;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.framework.api.conv.page.PageRequestConv;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.novel.api.NovelChapterApi;
import cn.deepdraw.training.novel.api.dto.LinkAddress;
import cn.deepdraw.training.novel.api.dto.NovelChapterDTO;
import cn.deepdraw.training.novel.api.dto.NovelChapterPageRequest;
import cn.deepdraw.training.novel.app.application.core.NovelChapterAppService;
import cn.deepdraw.training.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.novel.app.domain.core.NovelChapter;
import cn.deepdraw.training.novel.app.domain.core.NovelChapterRepository;
import cn.deepdraw.training.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.novel.app.interfaces.core.NovelChapterPageConv;
import cn.deepdraw.training.novel.app.interfaces.core.NovelChapterPageRequestBuilder;

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
	private NovelChapterPageRequestBuilder reqBuilder;

	@Autowired
	private PageRequestConv pageReqConv;

	@Autowired
	private NovelRepository novelRepo;

	@Override
	public NovelChapterDTO create(Long novelId, String name, LinkAddress address, Integer index) throws WebAppRuntimeException {

		LinkAddr addr = LinkAddr.of(address.getSite(), address.getVersion(), address.getLink(), address.getPath());
		return chapterConv.done(appService.create(NovelChapter.of(novelRepo.findByEntityId(novelId), name, addr, index)));
	}

	@Override
	public NovelChapterDTO updateAddressPath(Long chapterId, String path) throws WebAppRuntimeException {

		return chapterConv.done(appService.updatePath(chapterRepo.findByChapterId(chapterId), path));
	}

	@Override
	public NovelChapterDTO findByChapterId(Long chapterId) {

		return chapterConv.done(chapterRepo.findByChapterId(chapterId));
	}

	@Override
	public List<NovelChapterDTO> findByNovelId(Long novelId, String site, Long version) {

		return chapterConv.done(chapterRepo.findByNovelId(novelId, site, version));
	}

	@Override
	public PageDTO<NovelChapterDTO> findByPage(NovelChapterPageRequest chapterReq, PageRequest pageReq) {

		return chapterConv.done(chapterRepo.findByPage(reqBuilder.build(chapterReq), pageReqConv.done(pageReq)));
	}
}