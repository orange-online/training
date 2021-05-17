package cn.deepdraw.training.novel.crawler.biquge.app.infrastructure.repository.jsoup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovel;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelRepository;

/**
 * @Description BiqugeNovelCrawlerJsoup
 * @Author zhangzhucong
 * @Date 2020/6/5
 **/
@Repository
public class BiqugeNovelJsoupRepository extends BiqugeNovelBaseJsoupRepository implements BiqugeNovelRepository {

    @Override
    public List<BiqugeNovel> findByKeyword(String keyword) {

        List<BiqugeNovel> novels = new ArrayList<>();
        Document document = getDocument(getSearchURLPrefix() + keyword);
        Element pageLink = document.getElementById("pagelink");
        if (pageLink == null) {

            return Collections.emptyList();
        }
        Element next;
        while (true) {

            Elements item = document.getElementsByClass("item");
            parseNovelInfo(item, novels);
            next = document.getElementsByClass("next").first();
            if (next == null) {

                break;
            }
            document = getDocument(getURLPrefix() + next.attr("href"));
        }
        return novels;
    }

    @Override
    public BiqugeNovel findNovel(String url) {

        Document doc = getDocument(url);
        Element info = doc.getElementById("info");
        String name = info.selectFirst("h1").text();
        String author = info.selectFirst("p").text();
        author = author.substring(author.indexOf("：") + 1);
        return BiqugeNovel.of(name, author, url);
    }

    private void parseNovelInfo(Elements elements, List<BiqugeNovel> novels) {

        for (Element element : elements) {

            Element a = element.selectFirst("dt").selectFirst("a");
            BiqugeNovel biqugeNovel = BiqugeNovel.of(a.text(), element.selectFirst("span").text(), getURLPrefix() + a.attr("href"));
            novels.add(biqugeNovel);
        }
    }
}