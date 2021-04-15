package cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovel;

/**
 * @Description BiqugeNovelConverterTest
 * @Author zhangzhucong
 * @Date 2020/6/11
 **/
@RunWith(MockitoJUnitRunner.class)
public class BiqugeNovelConverterTest {

    @InjectMocks
    private BiqugeNovelConverter converter;

    @Test
    public void toNovelDTO_happyPath() {

        BiqugeNovel biqugeNovel = createWithAuthorAndNameAndUrl("author", "name", "url");
        NovelDTO novelDTO = converter.toNovelDTO(biqugeNovel);

        assertEquals("author", novelDTO.getAuthor());
        assertEquals("name", novelDTO.getName());
        assertEquals("url", novelDTO.getUrl());
    }

    @Test
    public void toNovelDTOs_happyPath() {

        BiqugeNovel biqugeNovel = Mockito.mock(BiqugeNovel.class);

        assertThat(converter.toNovelDTOs(Arrays.asList(biqugeNovel)), Matchers.hasSize(1));
    }

    private BiqugeNovel createWithAuthorAndNameAndUrl(String author, String name, String url) {

        return BiqugeNovel.of(name, author, url);
    }
}