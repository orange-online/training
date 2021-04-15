package cn.deepdraw.training.crawler.novel.crawler.api;

import java.util.List;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;

/**
 * @Description NovelCrawlerApi
 * @Author zhangzhucong
 * @Date 2020/6/2
 **/
public interface NovelCrawlerApi {

    List<NovelDTO> find(String keywords);

    NovelDTO findNovel(String url);

    List<ChapterDTO> findChapters(String url);

    ChapterContentDTO findChapterContent(String url);

    String site();
}