package cn.deepdraw.training.novel.crawler.xuanshuwang.app.domain;

import java.util.List;

/**
 * @author：杨攀
 * @date：2020年7月22日 下午2:42:22
 */
public interface XuanshuwangRepository {

    List<XuanshuwangNovel> findByKeywords(String keywords);

    List<XuanshuwangChapter> findChapters(String url);

    XuanshuwangChapterContent findChapterContent(String chapterUrl);

	XuanshuwangNovel findNovel(String url);
}