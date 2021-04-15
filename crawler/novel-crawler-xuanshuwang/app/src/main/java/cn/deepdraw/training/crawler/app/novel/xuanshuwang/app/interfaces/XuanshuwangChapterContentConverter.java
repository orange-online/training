package cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.interfaces;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.domain.XuanshuwangChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContentDTO;

/**
 * 职责: XuanshuwangChapterContent与XuanshuwangChapterContentDTO之间互相转换
 * @author：杨攀
 * @date：2020年7月24日 下午2:21:22
 */
@Component
public class XuanshuwangChapterContentConverter {

    public ChapterContentDTO toChapterContentDTO(XuanshuwangChapterContent content) {

        if (content == null) {

            return null;
        }
        ChapterContentDTO contentDTO = new ChapterContentDTO();
        contentDTO.setContent(content.content());
        return contentDTO;
    }
}