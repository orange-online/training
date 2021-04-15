package cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces.converter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovelChapter;

/**
 * @Description BiqugeNovelChapterConverter
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
@Component
public class BiqugeNovelChapterConverter {

    public ChapterDTO toChapterDTO(BiqugeNovelChapter chapter) {

        if (chapter == null) {

            return null;
        }
        ChapterDTO dto = new ChapterDTO();
        dto.setName(chapter.getSubTitle() + " " + chapter.getName());
        dto.setUrl(chapter.getUrl());
        return dto;
    }

    public List<ChapterDTO> toChapterDTOs(List<BiqugeNovelChapter> chapters) {

        return chapters.stream().filter(Objects::nonNull).map(this::toChapterDTO).collect(Collectors.toList());
    }
}