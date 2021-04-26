package cn.deepdraw.training.crawler.novel.crawler.api;

import java.util.List;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.Novel;

/**
 * @Description NovelCrawlerApi
 * @Author zhangzhucong
 * @Date 2020/6/2
 **/
public interface NovelCrawlerApi {

    List<Novel> find(String keywords);

    Novel findNovel(String url);

    List<Chapter> findChapters(String url);

    ChapterContent findChapterContent(String url);

    String site();
}