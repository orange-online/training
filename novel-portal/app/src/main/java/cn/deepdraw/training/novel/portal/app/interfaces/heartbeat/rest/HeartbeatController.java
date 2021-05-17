package cn.deepdraw.training.novel.portal.app.interfaces.heartbeat.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 心跳接口
 * @author huangjiancheng
 * 2020-06-06
 */
@RestController
@RequestMapping("/heartbeat")
public class HeartbeatController {

	@GetMapping
	public void heartbeat() {}
}