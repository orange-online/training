package cn.deepdraw.training.crawler.novel.crawler.biquge.app.infrastructure.repository.jsoup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovel;

/**
 * @Description BiqugeNovelJsoupRepositoryTest
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
public class BiqugeNovelJsoupRepositoryIntegrationTest {

    private BiqugeNovelJsoupRepository repo = new BiqugeNovelJsoupRepository();

    @Test
    public void findByKeyword_happyPath() {

        List<BiqugeNovel> biqugeNovels = repo.findByKeyword("我吃西红柿");
        assertThat(biqugeNovels, Matchers.hasSize(Matchers.greaterThanOrEqualTo(1)));
    }

    @Test
    public void findByUrl_happyPath() {

        BiqugeNovel biqugeNovel = repo.findNovel("http://www.biquge.com/0_14/");

        assertEquals("完美世界", biqugeNovel.getName());
        assertEquals("辰东", biqugeNovel.getAuthor());
        assertEquals("http://www.biquge.com/0_14/", biqugeNovel.getUrl());
    }
}