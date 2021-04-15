package cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.interfaces;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.domain.XuanshuwangChapter;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterDTO;

/**
 * 职责: XuanshuwangChapter与ChapterDTO互相转换
 * @author：杨攀
 * @date：2020年7月23日 上午9:52:40
 */
@Component
public class XuanshuwangChapterConverter {

    public List<ChapterDTO> toChapterDTOs(List<XuanshuwangChapter> chapters) {

        if (CollectionUtils.isEmpty(chapters)) {

            return Collections.emptyList();
        }
        return chapters.stream().map(chapter -> toChapterDTO(chapter)).filter(dto -> dto != null).collect(Collectors.toList());
    }

    private ChapterDTO toChapterDTO(XuanshuwangChapter chapter) {

        if (chapter == null) {

            return null;
        }
        ChapterDTO chapterDTO = new ChapterDTO();
        chapterDTO.setName(chapter.name());
        chapterDTO.setUrl(chapter.url());
        chapterDTO.setSequence(chapter.sequence());
        return chapterDTO;
    }
}