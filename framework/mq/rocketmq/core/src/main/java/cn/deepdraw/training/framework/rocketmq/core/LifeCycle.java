package cn.deepdraw.training.framework.rocketmq.core;

import org.apache.rocketmq.client.exception.MQClientException;

/**
 * Rocketmq 生命周期
 * @author huangjiancheng
 * 2020-07-13
 */
public interface LifeCycle {

	void start() throws MQClientException;

	void shutdown();
}