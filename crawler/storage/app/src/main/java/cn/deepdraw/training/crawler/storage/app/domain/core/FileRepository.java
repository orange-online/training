package cn.deepdraw.training.crawler.storage.app.domain.core;

import java.io.IOException;

/**
 * 文件仓储接口
 * @author huangjiancheng
 * 2020-07-16
 */
public interface FileRepository {

	File store(File file) throws IOException;
	
	byte[] download(String path);
}