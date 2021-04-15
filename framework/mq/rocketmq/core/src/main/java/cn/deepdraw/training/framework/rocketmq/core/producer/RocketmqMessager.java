package cn.deepdraw.training.framework.rocketmq.core.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;

import cn.deepdraw.training.framework.rocketmq.core.RocketmqMessage;

/**
 * Rocketmq 消息发送工具
 * @author huangjiancheng
 * 2020-07-23
 */
public class RocketmqMessager {

	/**
	 * 发送消息
	 * @param setting 消息队列（生产者）配置
	 * @param messageBody 消息内容（需要重写toString()方法）
	 * @return
	 * @throws MQClientException
	 */
	public static SendResult send(RocketmqProducerSetting setting, Object messageBody) throws MQClientException {

		return RocketmqProducer.getInstance(setting).send(RocketmqMessage.of(setting.getTopic(), messageBody.toString(), setting.getTag(), setting.getKeys()));
	}
}