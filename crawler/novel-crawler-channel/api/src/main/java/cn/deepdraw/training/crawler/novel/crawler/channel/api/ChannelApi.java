package cn.deepdraw.training.crawler.novel.crawler.channel.api;

import java.util.List;

import cn.deepdraw.training.crawler.novel.crawler.channel.api.dto.ChannelDTO;
import cn.deepdraw.training.crawler.novel.crawler.channel.api.dto.ChannelRequest;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;

/**
 * Channel Api
 * @author huangjiancheng
 * @Date 2021-04-29
 */
public interface ChannelApi {

	List<ChannelDTO> findAll();
	
	List<ChannelDTO> findAvailable();
	
	ChannelDTO findByChannelId(Long channelId);
	
	ChannelDTO findByChannelCode(String channelCode);
	
	ChannelDTO create(ChannelRequest request) throws WebAppRuntimeException;
	
	ChannelDTO update(Long channelId, ChannelRequest request) throws WebAppRuntimeException;
	
	ChannelDTO updateAvailable(Long channelId, boolean available) throws WebAppRuntimeException;
}