package cn.deepdraw.training.crawler.storage.app.interfaces.rest;

import org.junit.Assert;
import org.junit.Test;

/**
 * HeartbeatController Test
 * @author huangjiancheng
 * 2020-07-17
 */
public class HeartbeatControllerTest {

	@Test
	public void should_return_success() {

		Assert.assertTrue(new HeartbeatController().heartbeat().successful());
	}
}