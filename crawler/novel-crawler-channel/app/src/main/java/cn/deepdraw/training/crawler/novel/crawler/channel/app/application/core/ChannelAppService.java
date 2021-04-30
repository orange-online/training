package cn.deepdraw.training.crawler.novel.crawler.channel.app.application.core;

import cn.deepdraw.training.crawler.novel.crawler.channel.api.dto.ChannelRequest;
import cn.deepdraw.training.crawler.novel.crawler.channel.app.domain.core.Channel;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

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