package cn.deepdraw.training.crawler.novel.api;

import java.util.List;

import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterDTO;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterQueryDTO;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * 小说章节Api
 * @author huangjiancheng
 * 2020-07-22
 */
public interface NovelChapterApi {

	NovelChapterDTO create(String novelId, String name, LinkAddress address) throws WebAppRuntimeException;

	NovelChapterDTO updatePath(String novelId, String chapterId, String path) throws WebAppRuntimeException;

	NovelChapterDTO findByChapterId(String novelId, String chapterId);

	List<NovelChapterDTO> findByNovelId(String novelId, String site);

	PageDTO<NovelChapterDTO> findByPage(NovelChapterQueryDTO query, PageRequest request);
}