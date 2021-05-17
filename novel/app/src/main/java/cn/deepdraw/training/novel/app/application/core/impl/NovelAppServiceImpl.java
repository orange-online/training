package cn.deepdraw.training.novel.app.application.core.impl;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.framework.orm.mysql.domain.identifier.SnowflakeIdGenerator;
import cn.deepdraw.training.novel.app.application.core.NovelAppService;
import cn.deepdraw.training.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.novel.app.domain.core.Novel;
import cn.deepdraw.training.novel.app.domain.core.NovelCrawlingEventService;
import cn.deepdraw.training.novel.app.domain.core.NovelPackagingEventService;
import cn.deepdraw.training.novel.app.domain.core.NovelRepository;
import cn.deepdraw.training.novel.app.domain.core.NovelService;

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
	public Novel updatePath(Long novelId, String site, Long version, String path) throws WebAppRuntimeException {

		return novelRepo.update(findByNovelId(novelId).updateAddrPath(site, version, path));
	}

	@Override
	public Novel crawl(String site, String url) {

		Long version = SnowflakeIdGenerator.getInstance().nextIdentifier(); // TODO Finally, the version will be provided by the version service by huangjiancheng 2021-05-07
		Novel novel = novelService.crawl(site, version, url);
		crawlingEventService.publish(novel, site, version);
		return novel;
	}

	@Override
	public Novel packaging(Long novelId, String site, Long version) {

		Novel novel = findByNovelId(novelId);
		packagingEventService.publish(novel, site, version);
		return novel;
	}

	private Novel findByNovelId(Long novelId) {

		return Validate.notNull(novelRepo.findByEntityId(novelId), "novelId_not_found");
	}
}