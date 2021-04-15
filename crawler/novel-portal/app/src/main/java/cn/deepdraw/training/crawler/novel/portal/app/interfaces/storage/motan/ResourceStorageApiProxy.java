package cn.deepdraw.training.crawler.novel.portal.app.interfaces.storage.motan;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;

import cn.deepdraw.training.crawler.storage.api.ResourceStorageApi;
import cn.deepdraw.training.crawler.storage.api.dto.FileItem;
import cn.deepdraw.training.crawler.storage.api.dto.Resource;

/**
 * ResourceStorageApi Proxy
 * @author huangjiancheng
 * 2020-07-16
 */
@Component
public class ResourceStorageApiProxy implements ResourceStorageApi {

	@MotanReferer(basicReferer = "baseReferer")
	private ResourceStorageApi storageApi;

	@Override
	public Resource retrieve(String path) {

		return storageApi.retrieve(path);
	}

	@Override
	public Resource store(FileItem item) throws IOException {

		return storageApi.store(item);
	}
}