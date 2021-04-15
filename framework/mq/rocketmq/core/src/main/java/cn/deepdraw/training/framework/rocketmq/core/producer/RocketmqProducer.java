package cn.deepdraw.training.framework.rocketmq.core.producer;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.framework.rocketmq.core.LifeCycle;
import cn.deepdraw.training.framework.rocketmq.core.RocketmqMessage;

/**
 * Rocketmq Producer
 * @author huangjiancheng
 * 2020-07-11
 */
public class RocketmqProducer implements LifeCycle {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private RocketmqProducerSetting setting;

	private DefaultMQProducer producer;

	private RocketmqProducer() {}

	private RocketmqProducer(RocketmqProducerSetting setting) throws MQClientException {

		this.setting = setting;
		this.producer = getRocketmqDefaultProducerInstance(setting);
		this.start(); // 实例化完成之后直接启动
	}

	private DefaultMQProducer getRocketmqDefaultProducerInstance(RocketmqProducerSetting setting) {

		DefaultMQProducer producer = new DefaultMQProducer(setting.getGroup());
		producer.setNamesrvAddr(setting.getNamesrvAddr());
		producer.setSendMsgTimeout(setting.getSendMsgTimeout());
		return producer;
	}

	public RocketmqProducerSetting getSetting() {

		return setting;
	}

	public DefaultMQProducer getProducer() {

		return producer;
	}

	public SendResult send(RocketmqMessage message) {

		try {

			return this.producer.send(message.message());
		} catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {

			String exceptionMsg = "message sent failed. exception message: " + e.getMessage();
			logger.error(exceptionMsg, e);
			throw new WebAppRuntimeException(exceptionMsg, e);
		}
	}

	@Override
	public void start() throws MQClientException {

		this.producer.start();
	}

	@Override
	public void shutdown() {

		RocketmqProducersSingletonFactory.remove(this.setting);
		this.producer.shutdown();
	}

	/**
	 * 获取RocketmqProducer唯一实例（若setting对应的Producer实例没有装配过，则先完成装配，然后再返回；否则直接返回匹配的Producer实例）
	 * @param setting Producer配置
	 * @return
	 * @throws MQClientException 
	 */
	public static RocketmqProducer getInstance(RocketmqProducerSetting setting) throws MQClientException {

		return RocketmqProducersSingletonFactory.getInstance(setting);
	}

	public static void shutdownAll() {

		RocketmqProducersSingletonFactory.getInstances().forEach(instance -> instance.shutdown());
	}

	/**
	 * RocketmqProducer(组) 单例工厂
	 * @author huangjiancheng
	 * 2020-07-11
	 */
	private static class RocketmqProducersSingletonFactory {

		private static final String INSTANCE_KEY_SEPARATOR = "@";

		private static Map<String, RocketmqProducer> INSTANCES = new ConcurrentHashMap<>();

		public static String instanceKey(RocketmqProducerSetting setting) {

			return setting.getNamesrvAddr() + INSTANCE_KEY_SEPARATOR + setting.getGroup();
		}

		public static RocketmqProducer getInstance(RocketmqProducerSetting setting) throws MQClientException {

			String instanceKey = instanceKey(setting);
			if (!INSTANCES.containsKey(instanceKey)) {

				INSTANCES.putIfAbsent(instanceKey, new RocketmqProducer(setting));
			}
			return INSTANCES.get(instanceKey);
		}

		public static RocketmqProducer remove(RocketmqProducerSetting setting) {

			return INSTANCES.remove(instanceKey(setting));
		}

		public static Collection<RocketmqProducer> getInstances() {

			return INSTANCES.values();
		}
	}
}