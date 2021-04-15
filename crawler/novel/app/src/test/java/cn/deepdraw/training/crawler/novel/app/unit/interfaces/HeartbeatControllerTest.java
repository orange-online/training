package cn.deepdraw.training.crawler.novel.app.unit.interfaces;

import org.junit.Assert;
import org.junit.Test;

import cn.deepdraw.training.crawler.novel.app.interfaces.heartbeat.rest.HeartbeatController;
import cn.deepdraw.training.framework.utils.response.Response;

/**
 * HeartbeatController Test
 * @author huangjiancheng
 * 2020-06-07
 */
public class HeartbeatControllerTest {

	@Test
	public void should_be_success_when_heartbeat_interface_called() {

		Assert.assertEquals(Response.OK, new HeartbeatController().heartbeat().getCode());
	}
}