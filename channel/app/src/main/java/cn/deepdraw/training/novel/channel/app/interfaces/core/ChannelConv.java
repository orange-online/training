package cn.deepdraw.training.novel.channel.app.interfaces.core;

import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.api.conv.IdLongEntityConv;
import cn.deepdraw.training.novel.channel.api.dto.ChannelDTO;
import cn.deepdraw.training.novel.channel.app.domain.core.Channel;

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