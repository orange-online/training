package cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain;

import java.util.List;

/**
 * @Description
 * @Author zhangzhucong
 * @Date 2020/6/6
 **/
public interface BiqugeNovelRepository {

    List<BiqugeNovel> findByKeyword(String keyword);

	BiqugeNovel findNovel(String url);
}