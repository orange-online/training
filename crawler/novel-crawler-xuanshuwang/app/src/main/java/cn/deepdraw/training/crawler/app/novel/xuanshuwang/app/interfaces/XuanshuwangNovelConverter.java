package cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.interfaces;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.app.novel.xuanshuwang.app.domain.XuanshuwangNovel;
import cn.deepdraw.training.crawler.novel.crawler.api.dto.NovelDTO;

/**
 * 职责: novel和novelDTO对象之间互相转化
 * @author：杨攀
 * @date：2020年6月22日 下午9:04:44
 */
@Component
public class XuanshuwangNovelConverter {

    public List<NovelDTO> toNovelDTOs(List<XuanshuwangNovel> novels) {

        return CollectionUtils.isEmpty(novels) ? Collections.emptyList()
                                                : novels.stream().map(novel -> toNovelDTO(novel)).filter(dto -> dto != null).collect(Collectors.toList());
    }

    public NovelDTO toNovelDTO(XuanshuwangNovel novel) {

        if (novel == null) {

            return null;
        }
        
        NovelDTO novelDTO = new NovelDTO();
        novelDTO.setName(novel.name());
        novelDTO.setAuthor(novel.author());
        novelDTO.setUrl(novel.url());
        return novelDTO;
    }
}