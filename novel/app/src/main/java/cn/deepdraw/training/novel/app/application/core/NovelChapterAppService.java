package cn.deepdraw.training.novel.app.application.core;

import cn.deepdraw.training.novel.app.domain.core.NovelChapter;

/**
 * NovelChapter AppService
 * @author huangjiancheng
 * 2020-07-22
 */
public interface NovelChapterAppService {

	NovelChapter create(NovelChapter chapter);

	NovelChapter updatePath(NovelChapter chapter, String path);

}