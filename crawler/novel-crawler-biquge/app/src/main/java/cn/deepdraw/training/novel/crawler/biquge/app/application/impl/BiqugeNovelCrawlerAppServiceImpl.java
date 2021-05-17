package cn.deepdraw.training.novel.crawler.biquge.app.application.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.deepdraw.training.novel.crawler.biquge.app.application.BiqugeNovelCrawlerAppService;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovel;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapter;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterContent;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterContentRepository;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterRepository;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelRepository;

/**
 * @Description BiqugeNovelCrawlerAppServiceImpl
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/

@Service
public class BiqugeNovelCrawlerAppServiceImpl implements BiqugeNovelCrawlerAppService {

    @Autowired
    private BiqugeNovelRepository novelRepo;

    @Autowired
    private BiqugeNovelChapterRepository chapterRepo;

    @Autowired
    private BiqugeNovelChapterContentRepository contentRepo;

    @Override
    public List<BiqugeNovel> findByKeyword(String keyword) {

        return novelRepo.findByKeyword(keyword);
    }

    @Override
    public List<BiqugeNovelChapter> findChapters(String url) {

        return chapterRepo.findChapters(url);
    }

    @Override
    public BiqugeNovelChapterContent findChapterContent(String url) {

        return contentRepo.findChapterContent(url);
    }

    @Override
    public BiqugeNovel findNovel(String url) {

        return novelRepo.findNovel(url);
    }
}