package cn.deepdraw.training.crawler.storage.app.interfaces.motan;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.storage.api.dto.FileItem;
import cn.deepdraw.training.crawler.storage.api.dto.Resource;
import cn.deepdraw.training.crawler.storage.app.application.ResourceStorageAppService;
import cn.deepdraw.training.crawler.storage.app.domain.core.File;
import cn.deepdraw.training.crawler.storage.app.infrastructure.env.AppEnvironment;

/**
 * ResourceStorageApiMotanService Test
 * @author huangjiancheng
 * 2020-07-17
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourceStorageApiMotanServiceTest {

	@InjectMocks
	private ResourceStorageApiMotanService apiMotanService;

	@Mock
	private AppEnvironment appEnv;

	@Mock
	private ResourceStorageAppService appService;

	@Test
	public void should_return_the_specified_resource_when_path_retrieved_exists() {

		String path  = "path", url = "url_prefix";
		Mockito.when(appEnv.url()).thenReturn(url);

		Resource resource = apiMotanService.retrieve(path);

		Assert.assertSame(url, resource.getPrefix());
		Assert.assertSame(path, resource.getPath());
		Mockito.verify(appEnv).url();
	}

	@Test
	public void should_return_the_resource_when_store_a_new_legal_file() throws IOException {

		String url = "url_prefix";
		FileItem item = FileItem.of("name", null, "data".getBytes());
		File fileMocked = Mockito.mock(File.class);
		Mockito.when(appEnv.url()).thenReturn(url);
		Mockito.when(appService.store(Mockito.any())).thenReturn(fileMocked);
		Mockito.when(fileMocked.fullPath()).thenReturn("full_path");

		Resource resource = apiMotanService.store(item);

		Assert.assertSame(url, resource.getPrefix());
		Assert.assertSame(fileMocked.fullPath(), resource.getPath());

		Mockito.verify(appEnv).url();
		Mockito.verify(appService).store(Mockito.any());
		
	}
}