package cn.deepdraw.training.crawler.novel.crawler.api.gateway;

import java.util.List;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.Novel;

/**
 * NovelCrawlerApi Gateway
 * @author huangjiancheng
 * @Date 2020-12-11
 */
public interface NovelCrawlerApiGateway {

    List<Novel> find(String keywords);

    List<Novel> find(String site, String keywords);

    Novel findNovel(String site, String url);

    List<Chapter> findChapters(String site, String url);

    ChapterContent findChapterContent(String site, String url);
}