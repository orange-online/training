package cn.deepdraw.training.novel.crawler.biquge.app.infrastructure.repository.jsoup;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Repository;

import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterContent;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterContentRepository;

/**
 * @Description BiqugeNovelChapterContentJsoupRepository
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
@Repository
public class BiqugeNovelChapterContentJsoupRepository extends BiqugeNovelBaseJsoupRepository implements BiqugeNovelChapterContentRepository {

    @Override
    public BiqugeNovelChapterContent findChapterContent(String url) {

        BiqugeNovelChapterContent chapterContent = new BiqugeNovelChapterContent();
        Document document = getDocument(url);
        chapterContent.setContent(document.getElementById("content").text());
        return chapterContent;
    }
}