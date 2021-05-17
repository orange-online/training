package cn.deepdraw.training.novel.channel.app.domain.core;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Channel Service
 * @author huangjiancheng
 * @Date 2021-04-30
 */
@Component
public class ChannelService {
	
	@Autowired
	private ChannelRepository channelRepo;

	public Channel create(Channel channel) {
		
		Validate.isTrue(checkCode(channel.code(), null), "code_already_exists");
		return channelRepo.create(channel);
	}
	
	private boolean checkCode(String code, Long channelId) {
		
		Channel findByCode = channelRepo.findByCode(code);
		return findByCode == null || findByCode.entityId().equals(channelId);
	}
	
	public Channel update(Long channelId, Channel channel) {
		
		Channel persis = channelRepo.findByEntityId(channelId);
		Validate.notNull(persis, "channel_id_donot_exist");
		Validate.isTrue(checkCode(channel.code(), persis.entityId()), "code_already_exists");
		return channelRepo.update(persis.update(channel));
	}
	
	public Channel updateAvailable(Long channelId, boolean available) {
		
		Channel persis = channelRepo.findByEntityId(channelId);
		Validate.notNull(persis, "channel_id_donot_exist");
		return channelRepo.update(persis.updateAvailable(available));
	}
}