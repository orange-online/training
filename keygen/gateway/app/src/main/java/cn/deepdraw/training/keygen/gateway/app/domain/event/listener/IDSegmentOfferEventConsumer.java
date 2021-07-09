package cn.deepdraw.training.keygen.gateway.app.domain.event.listener;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.keygen.gateway.app.domain.core.IDSegment;
import cn.deepdraw.training.keygen.gateway.app.domain.core.IDSegmentContainer;
import cn.deepdraw.training.keygen.gateway.app.domain.event.IDSegmentOfferEvent;
import cn.deepdraw.training.keygen.gateway.app.domain.event.executor.IDSegmentOfferEventExecutorServiceCenter;
import cn.deepdraw.training.keygen.pool.api.IDSegmentPoolApi;
import cn.deepdraw.training.keygen.pool.api.dto.IDSegmentDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * IDSegmentOfferEvent Consumer
 * 
 * @author huangjiancheng
 * @Date 2021-07-01
 */
@Slf4j
@Order(0)
@Component
public class IDSegmentOfferEventConsumer implements IDSegmentOfferEventListener {
	
	private static final IDSegmentOfferEventExecutorServiceCenter EXECUTOR_CENTER = IDSegmentOfferEventExecutorServiceCenter.getInstance();

	@DubboReference
	private IDSegmentPoolApi poolApi;

	public void consume(IDSegmentOfferEvent event) {

		log.info("method {} param: event = {}", "IDSegmentOfferEventConsumer::consume", event.toString());
		CompletableFuture.supplyAsync(() -> {

			IDSegmentDTO idSegment = poolApi.getIDSegment();
			if (!Objects.isNull(idSegment)) {

				IDSegmentContainer.getInstance().segmentPool().offer(IDSegment.of(idSegment.getStartInclusive(), idSegment.getEndExclusive()));
			}
			return !Objects.isNull(idSegment);
		}, getExecutorService(event.capacity())).whenComplete((result, e) -> { // whenComplete第一个参数是结果，第二个参数是异常，他可以感知异常，无法返回默认数据
			
			if (!Objects.isNull(result)) {
				
				log.info("method {} param: event = {}, result: {}", "IDSegmentOfferEventConsumer::consume", event.toString(), result.toString());
			}
		}).exceptionally(e -> { // exceptionally只有一个参数是异常类型，他可以感知异常，同时返回默认数据10
			
			log.error("method {} param: event = {}, method {} execution fails, exception message: {}", "IDSegmentOfferEventConsumer::consume", event.toString(), "Future::get", e.getMessage());
			return false;
		});
//		.handle((result, e) -> { // handler既可以感知异常，也可以返回默认数据，是whenComplete和exceptionally的结合
//			
//			if (result != null) {
//				
//				return result;
//			}
//			if (e != null) {
//				
//				return 0;
//			}
//			return false;
//		});
		
		// future implement
//		Future<Boolean> future = getExecutor(event.capacity()).submit(new Callable<Boolean>() {
//
//			@Override
//			public Boolean call() throws Exception {
//
//				IDSegmentDTO idSegment = poolApi.getIDSegment();
//				if (!Objects.isNull(idSegment)) {
//
//					segmentContainer.segmentPool()
//							.offer(IDSegment.of(idSegment.getStartInclusive(), idSegment.getEndExclusive()));
//				}
//				return !Objects.isNull(idSegment);
//			}
//		});
//		try {
//
//			log.info("method {} param: event = {}, result: {}", "IDSegmentOfferEventConsumer::consume", event.toString(), future.get().toString());
//		} catch (InterruptedException | ExecutionException e) {
//
//			log.error("method {} param: event = {}, method {} execution fails and exception message is: {}", "IDSegmentOfferEventConsumer::consume", event.toString(), "Future::get", e.getMessage());
//		}
	}

	private ExecutorService getExecutorService(int capacity) {
		
		return EXECUTOR_CENTER.getExecutor(capacity).getService();
	}

	@Override
	public void listen(IDSegmentOfferEvent event) {
		
		consume(event);;
	}
}