package cn.deepdraw.training.novel.crawler.liudatxt.app.application;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovel;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapter;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContent;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterContentRepository;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelChapterRepository;
import cn.deepdraw.training.novel.crawler.liudatxt.app.domain.LiudatxtNovelRepository;

/**
 * LiudatxtNovelCrawlerAppService Impl
 * @author huangjiancheng
 * 2020-06-07
 */
@Component
public class LiudatxtNovelCrawlerAppServiceImpl implements LiudatxtNovelCrawlerAppService {

	@Autowired
	private LiudatxtNovelRepository novelRepo;

	@Autowired
	private LiudatxtNovelChapterRepository chapterRepo;

	@Autowired
	private LiudatxtNovelChapterContentRepository chapterContentRepo;

	@Override
	public List<LiudatxtNovel> find(String keywords) {

		return StringUtils.isNotBlank(keywords) ? novelRepo.find(keywords) : Collections.emptyList();
	}

	@Override
	public List<LiudatxtNovelChapter> findChapters(String url) {

		return StringUtils.isNotBlank(url) ? chapterRepo.findChapters(url) : Collections.emptyList();
	}

	@Override
	public LiudatxtNovelChapterContent findChapterContent(String url) {

		return StringUtils.isNotBlank(url) ? chapterContentRepo.findChapterContent(url) : null;
	}

	@Override
	public LiudatxtNovel findNovel(String url) {

		return StringUtils.isNotBlank(url) ? novelRepo.findNovel(url) : null;
	}
}