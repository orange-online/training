package cn.deepdraw.training.crawler.storage.app.interfaces.motan;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.weibo.api.motan.config.springsupport.annotation.MotanService;

import cn.deepdraw.training.crawler.storage.api.ResourceStorageApi;
import cn.deepdraw.training.crawler.storage.api.dto.FileItem;
import cn.deepdraw.training.crawler.storage.api.dto.Resource;
import cn.deepdraw.training.crawler.storage.api.dto.ResourceData;
import cn.deepdraw.training.crawler.storage.app.application.ResourceStorageAppService;
import cn.deepdraw.training.crawler.storage.app.domain.core.File;
import cn.deepdraw.training.crawler.storage.app.domain.core.FileRepository;
import cn.deepdraw.training.crawler.storage.app.infrastructure.env.AppEnvironment;

/**
 * StorageApi MotanService
 * @author huangjiancheng
 * 2020-07-14
 */
@MotanService(basicService = "baseService")
public class ResourceStorageApiMotanService implements ResourceStorageApi {

	@Autowired
	private AppEnvironment appEnv;

	@Autowired
	private ResourceStorageAppService appService;
	
	@Autowired
	private FileRepository fileRepo;

	@Override
	public Resource retrieve(String path) {

		return Resource.of(appEnv.url(), path);
	}

	@Override
	public Resource store(FileItem item) throws IOException {

		return Resource.of(appEnv.url(), appService.store(File.of(item.getName(), item.getPath(), item.getData())).fullPath());
	}

	@Override
	public List<ResourceData> download(List<String> paths) {

		if (CollectionUtils.isEmpty(paths)) {
			
			return Collections.emptyList();
		}
		return paths.stream().map(path -> ResourceData.of(appEnv.url(), path, fileRepo.download(path))).collect(Collectors.toList());
	}
}