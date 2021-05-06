package cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.unit.domain;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangNovel;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.interfaces.XuanshuwangNovelConverter;

/**
 * 职责: XuanshuwangNove与XuanshuwangNoveDTO互相转换
 * @author：杨攀
 * @date：2020年7月21日 下午2:33:13
 */
@RunWith(MockitoJUnitRunner.class)
public class XuanshuwangNovelConverterTest {

    @InjectMocks
    private XuanshuwangNovelConverter converter;

    private List<XuanshuwangNovel> novels;

    private XuanshuwangNovel novel;

    @Before
    public void setUp() {

        novel = XuanshuwangNovel.of("name", "author", "url");
        novels = Arrays.asList(novel);
    }

    @Test
    public void toNovels_shouldReturnEmptyList_ifNovelsIsEmpty() {

        novels = Collections.emptyList();
        
        List<Novel> result = converter.toNovels(novels);
        assertThat(result, is(empty()));
    }

    @Test
    public void toNovels_shouldReturnNovelS_ifNovelsIsNormal() {

        List<Novel> result = converter.toNovels(novels);
        Novel dto = result.get(0);
        assertThat(dto.getName(), is("name"));
        assertThat(dto.getAuthor(), is("author"));
        assertThat(dto.getLink(), is("url"));
    }

    @Test
    public void toNovel_shouldReturnNull_ifNovelIsNull() {

        novel = null;
        Novel dto = converter.toNovel(novel);
        assertNull(dto);
    }
}