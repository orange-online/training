package cn.deepdraw.training.crawler.novel.crawler.biquge.app.infrastructure.repository.jsoup;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovelChapter;

/**
 * @Description BiqugeNovelChapterJsoupRepository
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
public class BiqugeNovelChapterJsoupRepositoryIntegrationTest {

    private BiqugeNovelChapterJsoupRepository repo = new BiqugeNovelChapterJsoupRepository();

    @Test
    public void findChapters_happyPath() {

        List<BiqugeNovelChapter> chapters = repo.findChapters("http://www.biquge.com/0_395/");
        assertThat(chapters, Matchers.hasSize(Matchers.greaterThanOrEqualTo(1)));
    }
}