package cn.deepdraw.training.novel.crawler.xuanshuwang.app.interfaces;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.deepdraw.training.framework.utils.response.Response;
import cn.deepdraw.training.framework.utils.response.ResponseUtils;

/**
 * @author：杨攀
 * @date：2020年7月22日 上午11:36:13
 */
@RestController
@RequestMapping("/heartbeat")
public class HeartbeatController {

    @GetMapping
    public Response heartbeat() {

        return ResponseUtils.success();
    }
}