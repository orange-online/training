package cn.deepdraw.training.storage.api;

import java.io.IOException;
import java.util.List;

import cn.deepdraw.training.storage.api.dto.FileItem;
import cn.deepdraw.training.storage.api.dto.Resource;
import cn.deepdraw.training.storage.api.dto.ResourceData;

/**
 * 存储接口
 * @author huangjiancheng
 * 2020-07-14
 */
public interface ResourceStorageApi {

	Resource store(FileItem item) throws IOException;

	Resource retrieve(String path);

	List<ResourceData> download(List<String> paths);
}