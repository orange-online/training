package cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.interfaces;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.domain.XuanshuwangChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.ChapterContent;

/**
 * 职责: XuanshuwangChapterContent与XuanshuwangChapterContent之间互相转换
 * @author：杨攀
 * @date：2020年7月24日 下午2:21:22
 */
@Component
public class XuanshuwangChapterContentConverter {

    public ChapterContent toChapterContent(XuanshuwangChapterContent content) {

        if (content == null) {

            return null;
        }
        ChapterContent dto = new ChapterContent();
        dto.setContent(content.content());
        return dto;
    }
}