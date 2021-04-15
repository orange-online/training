package cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces.converter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;
import cn.deepdraw.training.crawler.novel.crawler.biquge.app.domain.BiqugeNovel;

/**
 * @Description BiqugeNovelConverter
 * @Author zhangzhucong
 * @Date 2020/6/9
 **/
@Component
public class BiqugeNovelConverter {

    public NovelDTO toNovelDTO(BiqugeNovel biqugeNovel) {

        if (biqugeNovel == null) {

            return null;
        }
        NovelDTO dto = new NovelDTO();
        dto.setAuthor(biqugeNovel.getAuthor());
        dto.setName(biqugeNovel.getName());
        dto.setUrl(biqugeNovel.getUrl());
        return dto;
    }

    public List<NovelDTO> toNovelDTOs(List<BiqugeNovel> biqugeNovels) {

        return biqugeNovels.stream().filter(Objects::nonNull).map(this::toNovelDTO).collect(Collectors.toList());
    }
}