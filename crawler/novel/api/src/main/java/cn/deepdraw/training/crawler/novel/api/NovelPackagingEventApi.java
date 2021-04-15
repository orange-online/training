package cn.deepdraw.training.crawler.novel.api;

import cn.deepdraw.training.crawler.novel.api.dto.NovelPackagingEventDTO;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * @author xujianing
 * @date 2020/12/7
 */
public interface NovelPackagingEventApi {

	NovelPackagingEventDTO create(String novelId, String site) throws WebAppRuntimeException;

	NovelPackagingEventDTO publish(String eventId) throws WebAppRuntimeException;

	NovelPackagingEventDTO complete(String eventId, String path) throws WebAppRuntimeException;
}
