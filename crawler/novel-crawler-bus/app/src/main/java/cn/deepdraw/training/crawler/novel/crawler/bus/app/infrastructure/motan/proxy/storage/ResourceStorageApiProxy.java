package cn.deepdraw.training.crawler.novel.crawler.bus.app.infrastructure.motan.proxy.storage;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.weibo.api.motan.config.springsupport.annotation.MotanReferer;

import cn.deepdraw.training.crawler.storage.api.ResourceStorageApi;
import cn.deepdraw.training.crawler.storage.api.dto.FileItem;
import cn.deepdraw.training.crawler.storage.api.dto.Resource;
import cn.deepdraw.training.crawler.storage.api.dto.ResourceData;

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
	public Resource store(FileItem item) throws IOException {

		return storageApi.store(item);
	}

	@Override
	public Resource retrieve(String path) {

		return storageApi.retrieve(path);
	}

	@Override
	public List<ResourceData> download(List<String> paths) {

		return storageApi.download(paths);
	}
}