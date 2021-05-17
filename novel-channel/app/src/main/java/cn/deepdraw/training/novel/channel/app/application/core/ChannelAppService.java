package cn.deepdraw.training.novel.channel.app.application.core;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.novel.channel.api.dto.ChannelRequest;
import cn.deepdraw.training.novel.channel.app.domain.core.Channel;

/**
 * Channel AppService
 * @author huangjiancheng
 * @Date 2021-04-30
 */
public interface ChannelAppService {

	Channel create(ChannelRequest request) throws WebAppRuntimeException;
	
	Channel update(Long channelId, ChannelRequest request) throws WebAppRuntimeException;
	
	Channel updateAvailable(Long channelId, boolean available) throws WebAppRuntimeException;
}