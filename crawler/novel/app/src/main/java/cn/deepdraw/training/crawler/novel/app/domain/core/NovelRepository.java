package cn.deepdraw.training.crawler.novel.app.domain.core;

import cn.deepdraw.training.crawler.novel.app.domain.shared.IdEntityRepository;

/**
 * 小说仓储
 * @author huangjiancheng
 * 2020-06-19
 */
public interface NovelRepository extends IdEntityRepository<Novel> {

	Novel findByNovelId(String novelId);

	Novel findByUnique(String name, String author);
}