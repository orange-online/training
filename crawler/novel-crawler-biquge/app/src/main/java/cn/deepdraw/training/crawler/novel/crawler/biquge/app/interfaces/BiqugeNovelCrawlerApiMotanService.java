package cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.biquge.api.BiqugeNovelCrawlerApi;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.application.BiqugeNovelCrawlerAppService;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces.converter.BiqugeNovelChapterContentConverter;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces.converter.BiqugeNovelChapterConverter;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces.converter.BiqugeNovelConverter;

/**
 * @Description BiqugeNovelCrawlerApiMotanService
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
@MotanService(basicService = "baseService")
public class BiqugeNovelCrawlerApiMotanService implements BiqugeNovelCrawlerApi {

    @Autowired
    private BiqugeNovelCrawlerAppService appService;

    @Autowired
    private BiqugeNovelConverter novelConverter;

    @Autowired
    private BiqugeNovelChapterConverter chapterConverter;

    @Autowired
    private BiqugeNovelChapterContentConverter contentConverter;

    @Override
    public List<NovelDTO> find(String keywords) {

        return novelConverter.toNovelDTOs(appService.findByKeyword(keywords));
    }

    @Override
    public NovelDTO findNovel(String url) {

        return novelConverter.toNovelDTO(appService.findNovel(url));
    }

    @Override
    public List<ChapterDTO> findChapters(String url) {

        return chapterConverter.toChapterDTOs(appService.findChapters(url));
    }

    @Override
    public ChapterContentDTO findChapterContent(String url) {

        return contentConverter.toChapterContentDTO(appService.findChapterContent(url));
    }
}
