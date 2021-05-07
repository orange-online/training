package cn.deepdraw.training.crawler.novel.crawler.channel.app.interfaces.core;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.crawler.novel.crawler.channel.api.dto.ChannelDTO;
import cn.deepdraw.training.crawler.novel.crawler.channel.app.domain.core.Channel;
import cn.deepdraw.training.framework.api.conv.IdLongEntityConv;

/**
 * Channel Conv
 * @author huangjiancheng
 * @Date 2021-04-30
 */
@Component
public class ChannelConv extends IdLongEntityConv<Channel, ChannelDTO> {

	@Override
	protected ChannelDTO doing(Channel channel) {

		ChannelDTO dto = new ChannelDTO();
		dto.setName(channel.name());
		dto.setCode(channel.code());
		dto.setLink(channel.link());
		dto.setTimeout(channel.timeout());
		dto.setAvailable(channel.available());
		return dto;
	}
}