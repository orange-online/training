package cn.deepdraw.training.crawler.novel.crawler.channel.app.domain.core;

import java.util.List;

import cn.deepdraw.training.crawler.novel.crawler.channel.app.domain.shared.IdEntityRepository;

/**
 * 小说仓储
 * @author huangjiancheng
 * 2020-06-19
 */
public interface ChannelRepository extends IdEntityRepository<Channel> {

	List<Channel> findAll();
	
	Channel findByCode(String code);

	List<Channel> findByAvailable(boolean available);
}