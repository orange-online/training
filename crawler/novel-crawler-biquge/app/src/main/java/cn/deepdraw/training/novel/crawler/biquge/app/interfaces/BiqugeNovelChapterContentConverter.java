package cn.deepdraw.training.novel.crawler.biquge.app.interfaces;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.novel.crawler.api.dto.ChapterContent;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapterContent;

/**
 * @Description BiqugeNovelChapterContentConverter
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
@Component
public class BiqugeNovelChapterContentConverter {

    public ChapterContent toChapterContent(BiqugeNovelChapterContent content) {

        if (content == null) {

            return null;
        }
        ChapterContent dto = new ChapterContent();
        dto.setContent(content.getContent());
        return dto;
    }
}