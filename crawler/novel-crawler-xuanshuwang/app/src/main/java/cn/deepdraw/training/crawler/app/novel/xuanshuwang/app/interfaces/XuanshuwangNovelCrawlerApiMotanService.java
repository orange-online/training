package cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.application.XuanshuwangNovelCrawlerAppService;
import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.domain.XuanshuwangChapterContent;
import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.domain.XuanshuwangNovel;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.api.XuanshuwangNovelCrawlerApi;

/**
 * 选书网爬虫 motan服务
 * @author：杨攀
 * @date：2020年6月6日 下午7:49:20
 */
@MotanService(basicService = "baseService")
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
    public List<NovelDTO> find(String keywords) {

        List<XuanshuwangNovel> novels = appService.findByKeywords(keywords);
        return novelConverter.toNovelDTOs(novels);
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
    public ChapterContentDTO findChapterContent(String chapterUrl) {

        XuanshuwangChapterContent content = appService.findChapterContent(chapterUrl);
        return contentConverter.toChapterContentDTO(content);
    }
}