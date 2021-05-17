package cn.deepdraw.training.novel.crawler.biquge.app.interfaces.dubbo;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.novel.crawler.biquge.api.BiqugeNovelCrawlerApi;
import cn.deepdraw.training.novel.crawler.biquge.app.application.BiqugeNovelCrawlerAppService;
import cn.deepdraw.training.novel.crawler.biquge.app.interfaces.BiqugeNovelChapterContentConverter;
import cn.deepdraw.training.novel.crawler.biquge.app.interfaces.BiqugeNovelChapterConverter;
import cn.deepdraw.training.novel.crawler.biquge.app.interfaces.BiqugeNovelConverter;

/**
 * @Description BiqugeNovelCrawlerApiMotanService
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
@DubboService
public class BiqugeNovelCrawlerApiDubboService implements BiqugeNovelCrawlerApi {

    @Autowired
    private BiqugeNovelCrawlerAppService appService;

    @Autowired
    private BiqugeNovelConverter novelConverter;

    @Autowired
    private BiqugeNovelChapterConverter chapterConverter;

    @Autowired
    private BiqugeNovelChapterContentConverter contentConverter;

    @Override
    public List<Novel> find(String keywords) {

        return novelConverter.toNovels(appService.findByKeyword(keywords));
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
    public ChapterContent findChapterContent(String url) {

        return contentConverter.toChapterContent(appService.findChapterContent(url));
    }
}
