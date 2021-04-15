package cn.deepdraw.training.crawler.novel.crawler.api.gateway;

import java.util.List;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;

/**
 * NovelCrawlerApi Gateway
 * @author huangjiancheng
 * @Date 2020-12-11
 */
public interface NovelCrawlerApiGateway {

    List<NovelDTO> find(String site, String keywords);

    NovelDTO findNovel(String site, String url);

    List<ChapterDTO> findChapters(String site, String url);

    ChapterContentDTO findChapterContent(String site, String url);
}