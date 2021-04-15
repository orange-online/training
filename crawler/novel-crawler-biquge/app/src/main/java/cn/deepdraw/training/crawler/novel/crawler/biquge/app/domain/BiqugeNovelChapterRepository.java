package cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain;

import java.util.List;

/**
 * @Description BiqugeNovelChapterRepository
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
public interface BiqugeNovelChapterRepository {

    List<BiqugeNovelChapter> findChapters(String url);
}