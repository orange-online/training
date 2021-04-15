package cn.deepdraw.training.framework.rocketmq.core.consumer;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.UtilAll;

import cn.deepdraw.training.framework.rocketmq.core.LifeCycle;

/**
 * Rocketmq Consumer
 * @author huangjiancheng
 * 2020-07-11
 */
public class RocketmqConsumer implements LifeCycle {

	private RocketmqConsumerSetting setting;

	private DefaultMQPushConsumer consumer;

	private RocketmqConsumer() {}

	private RocketmqConsumer(RocketmqConsumerSetting setting) throws MQClientException {

		this.setting = setting;
		this.consumer = getRocketmqDefaultConsumerInstance(setting);
		this.start(); // 实例化完成之后直接启动
	}

	private DefaultMQPushConsumer getRocketmqDefaultConsumerInstance(RocketmqConsumerSetting setting) throws MQClientException {

		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(setting.getGroup());
		consumer.setNamesrvAddr(setting.getNamesrvAddr());
		consumer.subscribe(setting.getTopic(), setting.getExpression());
		return doRegisterMessageListener(consumer, setting);
	}

	private DefaultMQPushConsumer doRegisterMessageListener(DefaultMQPushConsumer consumer, RocketmqConsumerSetting setting) {

		consumer.setMessageModel(setting.getMessageModel());
		consumer.setConsumeMessageBatchMaxSize(setting.getConsumeMessageBatchMaxSize());
		consumer.setConsumeFromWhere(setting.getConsumeFromWhere());
		consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(setting.getConsumeTimestamp())); // 该属性需要与ConsumeFromWhere.CONSUME_FROM_TIMESTAMP配套使用，可以不设置，默认半个小时以前；表示从指定时间开始消费
		consumer.setConsumeTimeout(setting.getConsumeTimeout()); // 消费超时时长（分钟），因为在有序消费的情况下，在消费消息的过程中会阻塞队列
		if (setting.getMessageListener() instanceof MessageListenerOrderly) {

			consumer.registerMessageListener((MessageListenerOrderly) setting.getMessageListener());
		} else {

			consumer.setConsumeThreadMin(setting.getConsumeThreadMin());
			consumer.setConsumeThreadMax(setting.getConsumeThreadMax());
			consumer.registerMessageListener((MessageListenerConcurrently) setting.getMessageListener());
		}
		return consumer;
	}

	public RocketmqConsumerSetting getSetting() {

		return setting;
	}

	public DefaultMQPushConsumer getConsumer() {

		return consumer;
	}

	@Override
	public void start() throws MQClientException {

		this.consumer.start();
	}

	@Override
	public void shutdown() {

		RocketmqConsumersSingletonFactory.remove(this.setting);
		this.consumer.shutdown();
	}

	/**
	 * 获取RocketmqConsumer唯一实例（若setting对应的Consumer实例没有装配过，则先完成装配，然后再返回；否则直接返回匹配的Consumer实例）
	 * @param setting Consumer配置
	 * @return
	 * @throws MQClientException 
	 */
	public static RocketmqConsumer getInstance(RocketmqConsumerSetting setting) throws MQClientException {

		return RocketmqConsumersSingletonFactory.getInstance(setting);
	}

	public static void shutdownAll() {

		RocketmqConsumersSingletonFactory.getInstances().forEach(instance -> instance.shutdown());
	}

	/**
	 * RocketmqConsumer(组) 单例工厂
	 * @author huangjiancheng
	 * 2020-07-11
	 */
	private static class RocketmqConsumersSingletonFactory {

		private static final String INSTANCE_KEY_SEPARATOR = "@";

		private static Map<String, RocketmqConsumer> INSTANCES = new ConcurrentHashMap<>();

		public static String instanceKey(RocketmqConsumerSetting setting) {

			return setting.getNamesrvAddr() + INSTANCE_KEY_SEPARATOR
					+ setting.getGroup() + INSTANCE_KEY_SEPARATOR
					+ setting.getTopic() + INSTANCE_KEY_SEPARATOR
					+ StringUtils.defaultString(setting.getExpression()); // 消息消费者须要按照最小的粒度（监控队列主题，且支持标签过滤）来进行分类缓存，以支持消费者精细化定制
		}

		public static RocketmqConsumer getInstance(RocketmqConsumerSetting setting) throws MQClientException {

			String instanceKey = instanceKey(setting);
			if (!INSTANCES.containsKey(instanceKey)) {

				INSTANCES.putIfAbsent(instanceKey, new RocketmqConsumer(setting));
			}
			return INSTANCES.get(instanceKey);
		}

		public static RocketmqConsumer remove(RocketmqConsumerSetting setting) {

			return INSTANCES.remove(instanceKey(setting));
		}

		public static Collection<RocketmqConsumer> getInstances() {

			return INSTANCES.values();
		}
	}
}