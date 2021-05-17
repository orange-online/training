package cn.deepdraw.training.novel.crawler.biquge.app.interfaces;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeConstants;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovelChapter;

/**
 * @Description BiqugeNovelChapterConverter
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
@Component
public class BiqugeNovelChapterConverter {

    public Chapter toChapter(BiqugeNovelChapter chapter) {

        if (chapter == null) {

            return null;
        }
        Chapter dto = new Chapter();
        dto.setName(chapter.getSubTitle() + " " + chapter.getName());
        dto.setSite(BiqugeConstants.SITE);
        dto.setLink(chapter.getLink());
        return dto;
    }

    public List<Chapter> toChapters(List<BiqugeNovelChapter> chapters) {

        return chapters.stream().filter(Objects::nonNull).map(this::toChapter).collect(Collectors.toList());
    }
}