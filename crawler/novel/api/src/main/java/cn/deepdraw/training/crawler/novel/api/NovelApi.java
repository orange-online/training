package cn.deepdraw.training.crawler.novel.api;

import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.api.dto.NovelQueryDTO;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * 小说接口
 * @author huangjiancheng
 * 2020-06-09
 */
public interface NovelApi {

	NovelDTO create(String name, String author, LinkAddress address) throws WebAppRuntimeException;

	NovelDTO updateAddress(Long novelId, LinkAddress address) throws WebAppRuntimeException;

	NovelDTO updatePath(Long novelId, String site, String path) throws WebAppRuntimeException;

	NovelDTO findByNovelId(Long novelId);

	NovelDTO findByNameAndAuthor(String name, String author);

	PageDTO<NovelDTO> findByPage(NovelQueryDTO query, PageRequest request);

	NovelDTO crawl(String site, String url);

	NovelDTO packaging(Long novelId, String site);
}