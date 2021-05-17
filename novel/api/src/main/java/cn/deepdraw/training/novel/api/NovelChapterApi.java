package cn.deepdraw.training.novel.api;

import java.util.List;

import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.novel.api.dto.LinkAddress;
import cn.deepdraw.training.novel.api.dto.NovelChapterDTO;
import cn.deepdraw.training.novel.api.dto.NovelChapterPageRequest;

/**
 * 小说章节Api
 * @author huangjiancheng
 * 2020-07-22
 */
public interface NovelChapterApi {

	NovelChapterDTO findByChapterId(Long chapterId);

	List<NovelChapterDTO> findByNovelId(Long novelId, String site, Long version);

	PageDTO<NovelChapterDTO> findByPage(NovelChapterPageRequest chapterReq, PageRequest pageReq);

	NovelChapterDTO create(Long novelId, String name, LinkAddress address, Integer index) throws WebAppRuntimeException;

	NovelChapterDTO updateAddressPath(Long chapterId, String path) throws WebAppRuntimeException;
}