package cn.deepdraw.training.novel.channel.app.application.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.novel.channel.api.dto.ChannelRequest;
import cn.deepdraw.training.novel.channel.app.application.core.ChannelAppService;
import cn.deepdraw.training.novel.channel.app.application.core.ChannelRequestConv;
import cn.deepdraw.training.novel.channel.app.domain.core.Channel;
import cn.deepdraw.training.novel.channel.app.domain.core.ChannelService;

/**
 * ChannelAppService Impl
 * @author huangjiancheng
 * @Date 2021-04-30
 */
@Service
@Transactional
public class ChannelAppServiceImpl implements ChannelAppService {
	
	@Autowired
	private ChannelRequestConv reqConv;
	
	@Autowired
	private ChannelService channelService;

	@Override
	public Channel create(ChannelRequest request) throws WebAppRuntimeException {

		return channelService.create(reqConv.done(request));
	}

	@Override
	public Channel update(Long channelId, ChannelRequest request) throws WebAppRuntimeException {

		return channelService.update(channelId, reqConv.done(request));
	}

	@Override
	public Channel updateAvailable(Long channelId, boolean available) throws WebAppRuntimeException {

		return channelService.updateAvailable(channelId, available);
	}
}