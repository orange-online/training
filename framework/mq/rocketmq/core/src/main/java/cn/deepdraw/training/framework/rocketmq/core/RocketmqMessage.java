package cn.deepdraw.training.framework.rocketmq.core;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageConst;

/**
 * Rocketmq Message
 * @author huangjiancheng
 * 2020-07-13
 */
public class RocketmqMessage implements Serializable {

	private static final long serialVersionUID = 20200713L;

	private String topic;

	private String tag;

	private List<String> keys;

	private String body;

	private RocketmqMessage() {}

	private RocketmqMessage(String topic, String tag, List<String> keys, String body) {

		this.setTopic(topic);
		this.setTag(tag);
		this.setKeys(keys);
		this.setBody(body);
	}

	/**
	 * 无tag、无key消息构造方法
	 * @param topic
	 * @param body
	 * @return
	 */
	public static RocketmqMessage of(String topic, String body) {

		return new RocketmqMessage(topic, StringUtils.EMPTY, Collections.emptyList(), body);
	}

	/**
	 * 有tag、无key消息构造方法
	 * @param topic
	 * @param body
	 * @param tag
	 * @return
	 */
	public static RocketmqMessage of(String topic, String body, String tag) {

		return new RocketmqMessage(topic, tag, Collections.emptyList(), body);
	}

	/**
	 * 有tag、多key消息构造方法
	 * @param topic
	 * @param body
	 * @param tags
	 * @param keys
	 * @return
	 */
	public static RocketmqMessage of(String topic, String body, String tag, List<String> keys) {

		return new RocketmqMessage(topic, tag, keys, body);
	}

	/**
	 * 有tag、单key消息构造方法
	 * @param topic
	 * @param body
	 * @param tag
	 * @param key
	 * @return
	 */
	public static RocketmqMessage of(String topic, String body, String tag, String key) {

		return new RocketmqMessage(topic, tag, Collections.singletonList(key), body);
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Message message() {

		return new Message(this.topic, this.tag, String.join(MessageConst.KEY_SEPARATOR, this.keys), this.body.getBytes());
	}
}