package cn.deepdraw.training.crawler.novel.app.application.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.crawler.novel.app.application.core.NovelChapterAppService;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapter;
import cn.deepdraw.training.crawler.novel.app.domain.core.NovelChapterRepository;

/**
 * NovelChapterAppService Impl
 * @author huangjiancheng
 * 2020-07-22
 */
@Service
@Transactional
public class NovelChapterAppServiceImpl implements NovelChapterAppService {

	@Autowired
	private NovelChapterRepository chapterRepo;

	@Override
	public NovelChapter create(NovelChapter chapter) {

		return chapterRepo.create(chapter);
	}

	@Override
	public NovelChapter updatePath(NovelChapter chapter, String path) {

		return chapterRepo.update(chapter.updateAddrPath(path));
	}
}