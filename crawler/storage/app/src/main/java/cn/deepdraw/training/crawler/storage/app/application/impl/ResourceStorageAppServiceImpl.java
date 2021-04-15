package cn.deepdraw.training.crawler.storage.app.application.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.deepdraw.training.crawler.storage.app.application.ResourceStorageAppService;
import cn.deepdraw.training.crawler.storage.app.domain.core.File;
import cn.deepdraw.training.crawler.storage.app.domain.core.FileRepository;

/**
 * 存储应用服务接口实现
 * @author huangjiancheng
 * 2020-07-16
 */
@Service
public class ResourceStorageAppServiceImpl implements ResourceStorageAppService {

	@Autowired
	private FileRepository fileRepo;

	@Override
	public File store(File file) throws IOException {

		return fileRepo.store(file);
	}
}