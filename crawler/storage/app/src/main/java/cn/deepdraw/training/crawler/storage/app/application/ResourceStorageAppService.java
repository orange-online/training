package cn.deepdraw.training.crawler.storage.app.application;

import java.io.IOException;

import cn.deepdraw.training.crawler.storage.app.domain.core.File;

/**
 * 存储应用服务接口
 * @author huangjiancheng
 * 2020-07-16
 */
public interface ResourceStorageAppService {

	File store(File file) throws IOException;
}