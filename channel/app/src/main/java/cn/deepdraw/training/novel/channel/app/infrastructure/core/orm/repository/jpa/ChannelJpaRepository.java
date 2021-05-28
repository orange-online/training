package cn.deepdraw.training.novel.channel.app.infrastructure.core.orm.repository.jpa;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.deepdraw.training.novel.channel.app.domain.core.Channel;
import cn.deepdraw.training.novel.channel.app.domain.core.ChannelRepository;
import cn.deepdraw.training.novel.channel.app.infrastructure.shared.orm.repository.jpa.IdEntityJpaRepository;

/**
 * Channel Jpa Repository
 * @author huangjiancheng
 * 2020-06-19
 */
@Repository
public interface ChannelJpaRepository extends ChannelRepository, IdEntityJpaRepository<Channel> {
	
	@Override
	default List<Channel> findAll() {

		return this.findAll(Sort.by(Arrays.asList(Order.asc("createdDate"))));
	}

	@Override
	default List<Channel> findByAvailable(boolean available) {

		return findByAvailable(available, false);
	}

	@Query("select channel from Channel channel where channel.available = :available and channel.removed = :removed")
	List<Channel> findByAvailable(@Param("available") boolean available, @Param("removed") boolean removed);
	
	@Override
	default Channel findByCode(String code) {

		return findByCode(code, false);
	}

	@Query("select channel from Channel channel where channel.code = :code and channel.removed = :removed")
	Channel findByCode(@Param("code") String code, @Param("removed") boolean removed);
}