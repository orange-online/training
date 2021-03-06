package cn.deepdraw.training.novel.crawler.gateway.app.infrastructure.dubbo.proxy.crawler;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.novel.channel.api.ChannelApi;

/**
 * ChannelApi Proxy
 * @author huangjiancheng
 * @Date 2021-05-06
 */
@Component
public class ChannelApiProxy {

	@DubboReference
	private ChannelApi channelApi;
}