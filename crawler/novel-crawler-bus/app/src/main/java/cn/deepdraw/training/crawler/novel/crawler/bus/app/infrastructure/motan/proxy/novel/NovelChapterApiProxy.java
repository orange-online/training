package cn.deepdraw.training.crawler.novel.crawler.bus.app.infrastructure.motan.proxy.novel;

import java.util.List;

import org.springframework.stereotype.Component;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;

import cn.deepdraw.training.crawler.novel.api.NovelChapterApi;
import cn.deepdraw.training.crawler.novel.api.dto.LinkAddress;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterDTO;
import cn.deepdraw.training.crawler.novel.api.dto.NovelChapterQueryDTO;
import cn.deepdraw.training.framework.api.dto.page.PageDTO;
import cn.deepdraw.training.framework.api.dto.page.PageRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * 小说章节Api代理
 * @author huangjiancheng
 * 2020-07-27
 */
@Component
public class NovelChapterApiProxy implements NovelChapterApi {

	@MotanReferer(basicReferer = "baseReferer")
	private NovelChapterApi chapterApi;

	@Override
	public NovelChapterDTO create(String novelId, String name, LinkAddress address) throws WebAppRuntimeException {

		return chapterApi.create(novelId, name, address);
	}

	@Override
	public NovelChapterDTO findByChapterId(String novelId, String chapterId) {

		return chapterApi.findByChapterId(novelId, chapterId);
	}

	@Override
	public List<NovelChapterDTO> findByNovelId(String novelId, String site) {

		return chapterApi.findByNovelId(novelId, site);
	}

	@Override
	public PageDTO<NovelChapterDTO> findByPage(NovelChapterQueryDTO query, PageRequest request) {

		return chapterApi.findByPage(query, request);
	}

	@Override
	public NovelChapterDTO updatePath(String novelId, String chapterId, String path) throws WebAppRuntimeException {

		return chapterApi.updatePath(novelId, chapterId, path);
	}
}