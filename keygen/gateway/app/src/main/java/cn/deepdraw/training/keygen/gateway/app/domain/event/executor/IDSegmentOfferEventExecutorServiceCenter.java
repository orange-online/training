package cn.deepdraw.training.keygen.gateway.app.domain.event.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cn.deepdraw.training.framework.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * IDSegmentOfferEventExecutor Container
 * @author huangjiancheng
 * @Date 2021-07-09
 */
@Slf4j
public class IDSegmentOfferEventExecutorServiceCenter {
	
	private volatile IDSegmentOfferEventExecutorService executor;
	
	private volatile List<IDSegmentOfferEventExecutorService> executorGarbages;
	
	private IDSegmentOfferEventExecutorServiceCenter() {
		
		this.executorGarbages = new ArrayList<>();
	}
	
	private static class IDSegmentOfferEventExecutorServiceCenterHolder {
		
		public static final IDSegmentOfferEventExecutorServiceCenter INSTANCE = new IDSegmentOfferEventExecutorServiceCenter();
	}
	
	private void shutdownGarbagesIfNecessary() {
		
		executorGarbages.forEach(garbage -> {
			
			garbage.shutdown();
			
			try {
				
				while (!garbage.awaitTermination(NumberUtils.INTEGER_ONE, TimeUnit.SECONDS));
			} catch (InterruptedException e) {
				
				log.error("[{}(), {}()] execution fails, exception message: {}, caused by: {}", "IDSegmentOfferEventExecutorContainer::shutdownGarbagesIfNecessary",
							"ExecutorService::awaitTermination", NumberUtils.INTEGER_ONE, e.getMessage(), JsonUtils.toJson(e.getCause()));
			}
			log.info("[{}()] executor({}) had been shutdown.", "IDSegmentOfferEventExecutorContainer::shutdownGarbagesIfNecessary", garbage.toString());
		});
	}
	
	private IDSegmentOfferEventExecutorService reuseGarbageIfPresent(int capacity) {
		
		return this.executorGarbages.parallelStream().filter(garbage -> Objects.equals(garbage.getCapacity(), capacity) && !garbage.isShutdown()).findFirst().orElse(null);
	}
	
	private IDSegmentOfferEventExecutorService resetExecutor(IDSegmentOfferEventExecutorService executor) {
		
		this.executorGarbages.removeIf(garbage -> Objects.equals(garbage.getCapacity(), executor.getCapacity()));
		if (!Objects.isNull(this.executor)) {
			
			if (CollectionUtils.isNotEmpty(this.executorGarbages)) { // TODO there can only be one garbage
				
				shutdownGarbagesIfNecessary();
			}
			this.executorGarbages.add(this.executor);
		}
		this.executor = executor;
		return this.executor;
	}
	
	public IDSegmentOfferEventExecutorService getExecutor(int capacity) {
		
		if (!Objects.isNull(this.executor) && Objects.equals(capacity, this.executor.getCapacity())) {
			
			return this.executor;
		} else {
			
			IDSegmentOfferEventExecutorService executorGarbage = reuseGarbageIfPresent(capacity);
			return resetExecutor(Objects.isNull(executorGarbage) ? IDSegmentOfferEventExecutorService.of(capacity) : executorGarbage);
		}
	}
	
	public static IDSegmentOfferEventExecutorServiceCenter getInstance() {
		
		return IDSegmentOfferEventExecutorServiceCenterHolder.INSTANCE;
	}
}