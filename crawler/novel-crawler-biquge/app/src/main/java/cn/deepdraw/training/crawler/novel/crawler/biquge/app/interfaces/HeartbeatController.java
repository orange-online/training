package cn.deepdraw.training.crawler.novel.crawler.biquge.app.interfaces;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.deepdraw.training.framework.utils.response.Response;
import cn.deepdraw.training.framework.utils.response.ResponseUtils;

/**
 * 心跳接口
 * @author huangjiancheng
 * 2020-06-06
 */
@RestController
@RequestMapping("/heartbeat")
public class HeartbeatController {

	@GetMapping
	public Response heartbeat() {

		return ResponseUtils.success();
	}
}