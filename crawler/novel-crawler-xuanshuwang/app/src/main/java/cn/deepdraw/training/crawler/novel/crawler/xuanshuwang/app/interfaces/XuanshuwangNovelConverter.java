package cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.interfaces;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangConstants;
import cn.deepdraw.training.crawler.novel.crawler.xuanshuwang.app.domain.XuanshuwangNovel;

/**
 * 职责: novel和novel对象之间互相转化
 * @author：杨攀
 * @date：2020年6月22日 下午9:04:44
 */
@Component
public class XuanshuwangNovelConverter {

    public List<Novel> toNovels(List<XuanshuwangNovel> novels) {

        return CollectionUtils.isEmpty(novels) ? Collections.emptyList()
                                                : novels.stream().map(novel -> toNovel(novel)).filter(dto -> dto != null).collect(Collectors.toList());
    }

    public Novel toNovel(XuanshuwangNovel novel) {

        if (novel == null) {

            return null;
        }
        
        Novel dto = new Novel();
        dto.setName(novel.name());
        dto.setAuthor(novel.author());
        dto.setSite(XuanshuwangConstants.SITE);
        dto.setLink(novel.link());
        return dto;
    }
}