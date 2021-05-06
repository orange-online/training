package cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.interfaces;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.api.XuanshuwangNovelCrawlerApi;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.application.XuanshuwangNovelCrawlerAppService;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangNovel;

/**
 * 选书网爬虫 motan服务
 * @author：杨攀
 * @date：2020年6月6日 下午7:49:20
 */
@DubboService
public class XuanshuwangNovelCrawlerApiMotanService implements XuanshuwangNovelCrawlerApi {

    @Autowired
    private XuanshuwangNovelCrawlerAppService appService;

    @Autowired
    private XuanshuwangNovelConverter novelConverter;

    @Autowired
    private XuanshuwangChapterConverter chapterConverter;

    @Autowired
    private XuanshuwangChapterContentConverter contentConverter;

    @Override
    public List<Novel> find(String keywords) {

        List<XuanshuwangNovel> novels = appService.findByKeywords(keywords);
        return novelConverter.toNovels(novels);
    }

    @Override
    public Novel findNovel(String url) {

        return novelConverter.toNovel(appService.findNovel(url));
    }

    @Override
    public List<Chapter> findChapters(String url) {

        return chapterConverter.toChapters(appService.findChapters(url));
    }

    @Override
    public ChapterContent findChapterContent(String chapterUrl) {

        XuanshuwangChapterContent content = appService.findChapterContent(chapterUrl);
        return contentConverter.toChapterContent(content);
    }
}