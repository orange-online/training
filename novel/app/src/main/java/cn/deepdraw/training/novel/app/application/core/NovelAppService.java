package cn.deepdraw.training.novel.app.application.core;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.novel.app.domain.core.LinkAddr;
import cn.deepdraw.training.novel.app.domain.core.Novel;

/**
 * Novel AppService
 * @author huangjiancheng
 * 2020-06-19
 */
public interface NovelAppService {

	Novel create(String name, String author, LinkAddr link) throws WebAppRuntimeException;

	Novel updateLink(Long novelId, LinkAddr link) throws WebAppRuntimeException;

	Novel updatePath(Long novelId, String site, Long version, String path) throws WebAppRuntimeException;

	Novel crawl(String site, String url);

	Novel packaging(Long novelId, String site, Long version);
}