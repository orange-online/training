package cn.deepdraw.training.crawler.novel.app.application.core.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.app.application.core.NovelAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.crawler.novel.app.domain.core.Novel;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelCrawlingEventService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelPackagingEventService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelService;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * Novel AppService Impl
 * @author huangjiancheng
 * 2020-06-19
 */
@Service
@Transactional
public class NovelAppServiceImpl implements NovelAppService {

	@Autowired
	private NovelRepository novelRepo;
	
	@Autowired
	private NovelService novelService;
	
	@Autowired
	private NovelCrawlingEventService crawlingEventService;

	@Autowired
	private NovelPackagingEventService packagingEventService;

	@Override
	public Novel create(String name, String author, LinkAddr link) throws WebAppRuntimeException {

		Validate.isTrue(novelRepo.findByUnique(name, author) == null, "novel_already_exists");
		return novelRepo.create(Novel.of(name, author, link));
	}

	@Override
	public Novel updateLink(Long novelId, LinkAddr link) throws WebAppRuntimeException {

		return novelRepo.update(findByNovelId(novelId).updateAddr(link));
	}

	@Override
	public Novel updatePath(Long novelId, String site, String path) throws WebAppRuntimeException {

		return novelRepo.update(findByNovelId(novelId).updateAddrPath(site, path));
	}

	@Override
	public Novel crawl(String site, String url) {
		
		Novel novel = novelService.crawl(site, url);
		crawlingEventService.publish(novel, site);
		return novel;
	}

	@Override
	public Novel packaging(Long novelId, String site) {

		Novel novel = findByNovelId(novelId);
		packagingEventService.publish(novel, site);
		return novel;
	}

	private Novel findByNovelId(Long novelId) {

		return Validate.notNull(novelRepo.findByEntityId(novelId), "novelId_not_found");
	}
}