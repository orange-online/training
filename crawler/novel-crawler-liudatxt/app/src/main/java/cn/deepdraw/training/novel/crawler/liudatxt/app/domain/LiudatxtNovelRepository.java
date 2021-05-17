package cn.deepdraw.training.novel.crawler.liudatxt.app.domain;

import java.util.List;

/**
 * LiudatxtNovel Repository
 * @author huangjiancheng
 * 2020-06-07
 */
public interface LiudatxtNovelRepository {

	List<LiudatxtNovel> find(String keywords);

	LiudatxtNovel findNovel(String url);
}