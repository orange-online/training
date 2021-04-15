package cn.deepdraw.training.framework.rocketmq.core.consumer;

import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import cn.deepdraw.training.framework.rocketmq.core.setting.RocketmqSetting;

/**
 * Rocketmq Consumer Setting
 * @author huangjiancheng
 * 2020-07-13
 */
public interface RocketmqConsumerSetting extends RocketmqSetting {

	public static final long INTERVAL_TIMESTAMP = 1000 * 60 * 30; // 默认间隔半个小时

	public static final int CONSUME_MESSAGE_BATCH_MAX_SIZE = 1; // 最大批量消费消息数

	public static final int CONSUME_TIMEOUT = 3; // 消费超时时长3分钟

	public static final int CONSUME_THREAD_MIN = 20; // 最小消费线程数

	public static final int CONSUME_THREAD_MAX = 20; // 最大消费线程数

	String getTopic();

	String getExpression();

	MessageListener getMessageListener(); // 消息监听器

	/**
	 * 消息模式：广播、集群（默认集群模式）
	 * @return
	 */
	default MessageModel getMessageModel() {

		return MessageModel.CLUSTERING;
	}

	/**
	 * 从哪里开始消费（默认从队尾开始消费）
	 * @return
	 */
	default ConsumeFromWhere getConsumeFromWhere() {

		return ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;
	}

	/**
	 * 从指定时间点（戳）开始消费（默认半个小时以前）
	 * @return
	 */
	default long getConsumeTimestamp() {

		return System.currentTimeMillis() - INTERVAL_TIMESTAMP;
	}

	/**
	 * 最大消息批量消费数（默认为1，即：不走批量）
	 * @return
	 */
	default int getConsumeMessageBatchMaxSize() {

		return CONSUME_MESSAGE_BATCH_MAX_SIZE;
	}

	/**
	 * 消费超时时长（分钟）
	 * @return
	 */
	default int getConsumeTimeout() {

		return CONSUME_TIMEOUT;
	}

	/**
	 * 并发消费最小线程数（默认20）
	 * @return
	 */
	default int getConsumeThreadMin() {

		return CONSUME_THREAD_MIN;
	}

	/**
	 * 并发消费最大线程数（默认20）
	 * @return
	 */
	default int getConsumeThreadMax() {

		return CONSUME_THREAD_MAX;
	}
}