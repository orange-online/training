package cn.deepdraw.training.crawler.storage.app.application.impl;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import cn.deepdraw.training.crawler.storage.app.domain.core.File;
import cn.deepdraw.training.crawler.storage.app.domain.core.FileRepository;

/**
 * ResourceStorageAppServiceImpl Test
 * @author huangjiancheng
 * 2020-07-17
 */
@RunWith(MockitoJUnitRunner.class)
public class ResourceStorageAppServiceImplTest {

	@InjectMocks
	private ResourceStorageAppServiceImpl appServiceImpl;

	@Mock
	private FileRepository fileRepo;

	@Test
	public void should_return_the_file_stored_when_a_new_file_is_stored() throws IOException {

		File fileMocked = Mockito.mock(File.class);
		Mockito.when(fileRepo.store(fileMocked)).thenReturn(fileMocked);

		File file = appServiceImpl.store(fileMocked);

		Assert.assertSame(fileMocked, file);
		Mockito.verify(fileRepo).store(fileMocked);
	}
}