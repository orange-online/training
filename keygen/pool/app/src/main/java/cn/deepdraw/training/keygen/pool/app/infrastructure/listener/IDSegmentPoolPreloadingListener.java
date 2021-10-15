package cn.deepdraw.training.keygen.pool.app.infrastructure.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.keygen.pool.app.domain.core.IDSegmentPool;
import cn.deepdraw.training.keygen.pool.app.domain.core.IDSegmentRepository;

/**
 * 号段池预加载监听器
 * @author huangjiancheng
 * @Date 2021-10-15
 */
@Order(2)
@Component
public class IDSegmentPoolPreloadingListener implements ApplicationListener<ContextRefreshedEvent> {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IDSegmentRepository segmentRepo;
	
	@Autowired
	private IDSegmentPool segmentPool;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		segmentPool.addAll(segmentRepo.findByConsumed(false));
		logger.info("ID segment pool has been initialized successful.");
	}
}