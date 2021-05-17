package cn.deepdraw.training.novel.app.domain.core;

import cn.deepdraw.training.novel.app.domain.shared.IdEntityRepository;

/**
 * 小说仓储
 * @author huangjiancheng
 * 2020-06-19
 */
public interface NovelRepository extends IdEntityRepository<Novel> {

	Novel findByUnique(String name, String author);
}