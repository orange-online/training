package cn.deepdraw.training.novel.api;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.novel.api.dto.NovelPackagingEventDTO;

/**
 * @author xujianing
 * @date 2020/12/7
 */
public interface NovelPackagingEventApi {

	NovelPackagingEventDTO create(Long novelId, String site, Long version) throws WebAppRuntimeException;

	NovelPackagingEventDTO publish(Long eventId) throws WebAppRuntimeException;

	NovelPackagingEventDTO complete(Long eventId, String path) throws WebAppRuntimeException;
}
