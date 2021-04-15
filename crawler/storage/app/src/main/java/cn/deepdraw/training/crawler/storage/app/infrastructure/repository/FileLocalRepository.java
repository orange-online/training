package cn.deepdraw.training.crawler.storage.app.infrastructure.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.storage.app.domain.core.File;
import cn.deepdraw.training.crawler.storage.app.domain.core.FileRepository;
import cn.deepdraw.training.crawler.storage.app.infrastructure.env.AppEnvironment;

/**
 * 本地文件仓储实现
 *
 * @author huangjiancheng
 * 2020-07-16
 */
@Component
public class FileLocalRepository implements FileRepository {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AppEnvironment appEnv;

	@Override
	public File store(File file) throws IOException {

		createFileIfNecessary(file);
		Files.write(Paths.get(appEnv.dir(), file.path(), file.name()), file.data());
		return file;
	}

	private void createFileIfNecessary(File file) throws IOException {

		Path path = Paths.get(appEnv.dir(), file.path(), file.name());
		if (!Files.exists(path)) {

			Files.createDirectories(Paths.get(appEnv.dir(), file.path()));
			Files.createFile(path);
		}
	}

	@Override
	public byte[] download(String path) {

		Path pathObject = Paths.get(appEnv.dir(), path);
		if (!Files.exists(pathObject)) {

			return null;
		}
		try {

			return Files.readAllBytes(pathObject);
		} catch (IOException e) {

			logger.error("path: " + path + " read failed. exception message: " + e.getMessage(), e);
			return null;
		}
	}
}