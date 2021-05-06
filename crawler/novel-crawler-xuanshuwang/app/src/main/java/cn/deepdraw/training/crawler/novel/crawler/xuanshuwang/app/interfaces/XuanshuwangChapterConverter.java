package cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.interfaces;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.Chapter;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapter;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangConstants;

/**
 * 职责: XuanshuwangChapter与Chapter互相转换
 * @author：杨攀
 * @date：2020年7月23日 上午9:52:40
 */
@Component
public class XuanshuwangChapterConverter {

    public List<Chapter> toChapters(List<XuanshuwangChapter> chapters) {

        if (CollectionUtils.isEmpty(chapters)) {

            return Collections.emptyList();
        }
        return chapters.stream().map(chapter -> toChapter(chapter)).filter(dto -> dto != null).collect(Collectors.toList());
    }

    private Chapter toChapter(XuanshuwangChapter chapter) {

        if (chapter == null) {

            return null;
        }
        Chapter dto = new Chapter();
        dto.setName(chapter.name());
        dto.setSite(XuanshuwangConstants.SITE);
        dto.setLink(chapter.link());
        dto.setIndex(chapter.index());
        return dto;
    }
}