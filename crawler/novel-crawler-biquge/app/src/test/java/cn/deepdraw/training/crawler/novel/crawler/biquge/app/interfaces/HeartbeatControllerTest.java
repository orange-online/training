package cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces;

import cn.deepdraw.training.framework.utils.response.Response;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author xjn
 * @description HearbeatControllerTest
 * @date 2020/11/20
 */
@RunWith(MockitoJUnitRunner.class)
public class HeartbeatControllerTest {

	@Test
	public void heartbeat_happyPath() {

		Assert.assertEquals(Response.OK, new HeartbeatController().heartbeat().getCode());
	}
}
