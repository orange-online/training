package cn.deepdraw.training.crawler.novel.portal.app.interfaces.novel.motan;

import org.springframework.stereotype.Component;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;

import cn.deepdraw.training.crawler.novel.api.NovelApi;
import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.api.dto.NovelQueryDTO;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * NovelApi Proxy
 * @author huangjiancheng
 * 2020-06-19
 */
@Component
public class NovelApiProxy implements NovelApi {

	@MotanReferer(basicReferer = "baseReferer")
	private NovelApi novelApi;

	@Override
	public NovelDTO create(String name, String author, LinkAddress address) throws WebAppRuntimeException {

		return novelApi.create(name, author, address);
	}

	@Override
	public NovelDTO findByNovelId(String novelId) {

		return novelApi.findByNovelId(novelId);
	}

	@Override
	public PageDTO<NovelDTO> findByPage(NovelQueryDTO query, PageRequest request) {

		return novelApi.findByPage(query, request);
	}

	@Override
	public NovelDTO findByNameAndAuthor(String name, String author) {

		return novelApi.findByNameAndAuthor(name, author);
	}

	@Override
	public NovelDTO updateAddress(String novelId, LinkAddress address) throws WebAppRuntimeException {

		return novelApi.updateAddress(novelId, address);
	}

	@Override
	public NovelDTO updatePath(String novelId, String site, String path) throws WebAppRuntimeException {

		return novelApi.updatePath(novelId, site, path);
	}

	@Override
	public NovelDTO crawl(String site, String url) {

		return novelApi.crawl(site, url);
	}
}