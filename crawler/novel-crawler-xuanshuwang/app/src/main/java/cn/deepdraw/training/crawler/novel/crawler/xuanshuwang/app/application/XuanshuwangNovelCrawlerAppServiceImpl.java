package cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.application;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapter;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangChapterContent;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangNovel;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangRepository;

/**
 * @author：杨攀
 * @date：2020年7月21日 下午3:34:12
 */
@Component
public class XuanshuwangNovelCrawlerAppServiceImpl implements XuanshuwangNovelCrawlerAppService {

    @Autowired
    private XuanshuwangRepository repository;

    @Override
    public List<XuanshuwangNovel> findByKeywords(String keywords) {

        if (StringUtils.isBlank(keywords)) {

            return Collections.emptyList();
        }
        return repository.findByKeywords(keywords);
    }

    @Override
    public List<XuanshuwangChapter> findChapters(String url) {

        if (StringUtils.isBlank(url)) {

            return Collections.emptyList();
        }
        return repository.findChapters(url);
    }

    @Override
    public XuanshuwangChapterContent findChapterContent(String chapterUrl) {

        if (StringUtils.isBlank(chapterUrl)) {

            return null;
        }
        return repository.findChapterContent(chapterUrl);
    }

    @Override
    public XuanshuwangNovel findNovel(String url) {

        return StringUtils.isNotBlank(url) ? repository.findNovel(url) : null;
    }
}