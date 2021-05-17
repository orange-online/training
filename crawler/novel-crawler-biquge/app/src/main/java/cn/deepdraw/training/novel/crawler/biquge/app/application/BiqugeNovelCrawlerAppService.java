package cn.deepdraw.training.novel.crawler.biquge.app.application;

import java.util.List;

import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovel;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapter;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterContent;

/**
 * @Description BiqugeNovelCrawlerAppService
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
public interface BiqugeNovelCrawlerAppService {

    List<BiqugeNovel> findByKeyword(String keyword);

    List<BiqugeNovelChapter> findChapters(String url);

    BiqugeNovelChapterContent findChapterContent(String url);

    BiqugeNovel findNovel(String url);
}