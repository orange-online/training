package cn.deepdraw.training.novel.crawler.biquge.app.interfaces;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.novel.crawler.api.dto.Novel;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeConstants;
import cn.deepdraw.training.novel.crawler.biquge.app.domain.BiqugeNovel;

/**
 * @Description BiqugeNovelConverter
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
@Component
public class BiqugeNovelConverter {

    public Novel toNovel(BiqugeNovel biqugeNovel) {

        if (biqugeNovel == null) {

            return null;
        }
        Novel dto = new Novel();
        dto.setName(biqugeNovel.getName());
        dto.setAuthor(biqugeNovel.getAuthor());
        dto.setSite(BiqugeConstants.SITE);
        dto.setLink(biqugeNovel.getLink());
        return dto;
    }

    public List<Novel> toNovels(List<BiqugeNovel> biqugeNovels) {

        return biqugeNovels.stream().filter(Objects::nonNull).map(this::toNovel).collect(Collectors.toList());
    }
}