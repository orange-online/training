package cn.deepdraw.training.crawler.novel.portal.app.interfaces.storage.rest;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.deepdraw.training.crawler.novel.portal.app.interfaces.storage.motan.ResourceStorageApiProxy;
import cn.deepdraw.training.crawler.storage.api.dto.FileItem;
import cn.deepdraw.training.crawler.storage.api.dto.Resource;

/**
 * Novel Controller
 * @author huangjiancheng
 * 2020-06-19
 */
@RestController
@RequestMapping("/resources")
public class ResourceStorageController {

	@Autowired
	private ResourceStorageApiProxy storageApi;

	@PostMapping
	public Resource store(@RequestParam String name, @RequestParam String path) throws IOException {

		return storageApi.store(FileItem.of(name, path, "小说内容！！".getBytes()));
	}

	@GetMapping
	public Resource retrieve(@RequestParam String path) {

		return storageApi.retrieve(path);
	}
}