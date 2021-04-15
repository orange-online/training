package cn.deepdraw.training.crawler.novel.crawler.biquge.app.infrastructure.repository.jsoup;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovelChapterContent;

/**
 * @Description BiqugeNovelChapterContentJsoupRepositoryTest
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
public class BiqugeNovelChapterContentJsoupRepositoryIntegrationTest {

    private BiqugeNovelChapterContentJsoupRepository repo = new BiqugeNovelChapterContentJsoupRepository();

    @Test
    public void findChapterContent_happyPath() {

        BiqugeNovelChapterContent chapterContent = repo.findChapterContent("http://www.biquge.com/0_395/2863746.html");
        assertTrue(StringUtils.isNotBlank(chapterContent.getContent()));
    }
}