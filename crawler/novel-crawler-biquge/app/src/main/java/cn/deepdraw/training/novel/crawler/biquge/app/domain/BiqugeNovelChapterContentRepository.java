package cn.deepdraw.training.novel.crawler.biquge.app.domain;

/**
 * @Description
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
public interface BiqugeNovelChapterContentRepository {

    BiqugeNovelChapterContent findChapterContent(String url);
}