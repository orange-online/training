package cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces.converter;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovelChapterContent;

/**
 * @Description BiqugeNovelChapterContentConverter
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
@Component
public class BiqugeNovelChapterContentConverter {

    public ChapterContentDTO toChapterContentDTO(BiqugeNovelChapterContent content) {

        if (content == null) {

            return null;
        }

        ChapterContentDTO dto = new ChapterContentDTO();
        dto.setContent(content.getContent());
        return dto;
    }
}