package cn.deepdraw.training.novel.channel.app.interfaces.core.dubbo;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.novel.channel.api.ChannelApi;
import cn.deepdraw.training.novel.channel.api.dto.ChannelDTO;
import cn.deepdraw.training.novel.channel.api.dto.ChannelRequest;
import cn.deepdraw.training.novel.channel.app.application.core.ChannelAppService;
import cn.deepdraw.training.novel.channel.app.domain.core.ChannelRepository;
import cn.deepdraw.training.novel.channel.app.interfaces.core.ChannelConv;

/**
 * ChannelApi DubboService
 * @author huangjiancheng
 * @Date 2021-04-30
 */
@DubboService
public class ChannelApiDubboService implements ChannelApi {
	
	@Autowired
	private ChannelConv channelConv;
	
	@Autowired
	private ChannelRepository channelRepo;
	
	@Autowired
	private ChannelAppService channelAppService;

	@Override
	public List<ChannelDTO> findAll() {

		return channelConv.done(channelRepo.findAll());
	}

	@Override
	public List<ChannelDTO> findAvailable() {

		return channelConv.done(channelRepo.findByAvailable(true));
	}

	@Override
	public ChannelDTO findByChannelId(Long channelId) {

		return channelConv.done(channelRepo.findByEntityId(channelId));
	}

	@Override
	public ChannelDTO findByChannelCode(String channelCode) {

		return channelConv.done(channelRepo.findByCode(channelCode));
	}

	@Override
	public ChannelDTO create(ChannelRequest request) throws WebAppRuntimeException {

		return channelConv.done(channelAppService.create(request));
	}

	@Override
	public ChannelDTO update(Long channelId, ChannelRequest request) throws WebAppRuntimeException {

		return channelConv.done(channelAppService.update(channelId, request));
	}

	@Override
	public ChannelDTO updateAvailable(Long channelId, boolean available) throws WebAppRuntimeException {

		return channelConv.done(channelAppService.updateAvailable(channelId, available));
	}
}