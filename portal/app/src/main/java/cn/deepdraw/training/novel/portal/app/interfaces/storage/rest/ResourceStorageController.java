package cn.deepdraw.training.novel.portal.app.interfaces.storage.rest;

import java.io.IOException;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.deepdraw.training.storage.api.ResourceStorageApi;
import cn.deepdraw.training.storage.api.dto.FileItem;
import cn.deepdraw.training.storage.api.dto.Resource;

/**
 * Novel Controller
 * @author huangjiancheng
 * 2020-06-19
 */
@RestController
@RequestMapping("/resources")
public class ResourceStorageController {

	@DubboReference
	private ResourceStorageApi storageApi;

	@PostMapping
	public Resource store(@RequestParam String name, @RequestParam String path) throws IOException {

		return storageApi.store(FileItem.of(name, path, "小说内容！！".getBytes()));
	}

	@GetMapping
	public Resource retrieve(@RequestParam String path) {

		return storageApi.retrieve(path);
	}
}