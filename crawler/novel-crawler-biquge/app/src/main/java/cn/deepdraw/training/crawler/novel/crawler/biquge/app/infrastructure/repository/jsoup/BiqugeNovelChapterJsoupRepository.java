package cn.deepdraw.training.crawler.novel.crawler.biquge.app.infrastructure.repository.jsoup;

import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovelChapter;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovelChapterRepository;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description BiqugeNovelChapterJsoupRepository
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
@Repository
public class BiqugeNovelChapterJsoupRepository extends BiqugeNovelBaseJsoupRepository implements BiqugeNovelChapterRepository {

    @Override
    public List<BiqugeNovelChapter> findChapters(String url) {

        List<BiqugeNovelChapter> chapters = new ArrayList<>();
        Document document = getDocument(url);
        Element list = document.getElementById("list");
        Element dl = list.selectFirst("dl");
        parseNovelChapter(dl, chapters);
        return chapters;
    }

    private void parseNovelChapter(Element element, List<BiqugeNovelChapter> chapters) {

        Elements dts = element.getElementsByTag("dt");
        // 笔趣阁小说章节页面里面的第一个dt标签下面的的章节为重复章节，故不加载
        boolean isValidChapter = false;
        Elements elements = element.children();
        String subtitle = "";
        for (Element ele : elements) {

            if (ele.text().equals(dts.get(1).text())) {

                isValidChapter = true;
            }
            if (!isValidChapter) {

                continue;
            }
            BiqugeNovelChapter chapter = new BiqugeNovelChapter();
            if ("dt".equals(ele.tagName())) {

                subtitle = ele.text();
            } else {

                chapter.setUrl(BIQUGE_URL + ele.selectFirst("a").attr("href"));
                chapter.setName(ele.selectFirst("a").text());
                chapter.setSubTitle(subtitle);
                chapters.add(chapter);
            }
        }
    }
}