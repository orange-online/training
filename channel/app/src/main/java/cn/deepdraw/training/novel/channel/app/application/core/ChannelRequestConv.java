package cn.deepdraw.training.novel.channel.app.application.core;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.novel.channel.api.dto.ChannelRequest;
import cn.deepdraw.training.novel.channel.app.domain.core.Channel;

/**
 * ChannelRequest Conv
 * @author huangjiancheng
 * @Date 2021-04-30
 */
@Component
public class ChannelRequestConv {

	public Channel done(ChannelRequest req) {
		
		return Channel.of(req.getName(), req.getCode(), req.getLink(), req.getTimeout());
	}
}