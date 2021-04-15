package cn.deepdraw.training.framework.rocketmq.core.consumer;

import org.apache.rocketmq.client.consumer.listener.MessageListener;

import cn.deepdraw.training.framework.rocketmq.core.setting.DefaultRocketmqSetting;

/**
 * Default RocketmqConsumerSetting
 * @author huangjiancheng
 * 2020-07-13
 */
public class DefaultRocketmqConsumerSetting extends DefaultRocketmqSetting implements RocketmqConsumerSetting {

	private static final long serialVersionUID = 20200713L;

	private String topic;

	private String expression;

	private MessageListener messageListener;

	private DefaultRocketmqConsumerSetting() {}

	private DefaultRocketmqConsumerSetting(String group, String namesrvAddr) {

		super(group, namesrvAddr);
	}

	public static DefaultRocketmqConsumerSetting of(String group, String namesrvAddr) {

		return new DefaultRocketmqConsumerSetting(group, namesrvAddr);
	}

	@Override
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	@Override
	public MessageListener getMessageListener() {
		return messageListener;
	}

	public void setMessageListener(MessageListener messageListener) {
		this.messageListener = messageListener;
	}

	public DefaultRocketmqConsumerSetting subscribe(String topic, String expression) {

		this.setTopic(topic);
		this.setExpression(expression);
		return this;
	}

	public DefaultRocketmqConsumerSetting registerMessageListener(MessageListener messageListener) {

		this.setMessageListener(messageListener);
		return this;
	}
}