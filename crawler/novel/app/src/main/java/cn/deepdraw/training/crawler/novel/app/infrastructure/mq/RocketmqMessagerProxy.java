package cn.deepdraw.training.crawler.novel.app.infrastructure.mq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.framework.rocketmq.core.producer.RocketmqMessager;
import cn.deepdraw.training.framework.rocketmq.core.producer.RocketmqProducerSetting;

/**
 * RocketmqMessager Proxy
 * @author huangjiancheng
 * @Date 2020-11-27
 */
@Component
public class RocketmqMessagerProxy {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public boolean send(RocketmqProducerSetting setting, Object messageBody) {
		
		try {
			
			SendResult result = RocketmqMessager.send(setting, messageBody);
			logger.info("message body is: " + messageBody.toString() + ", result is " + result);
			return SendStatus.SEND_OK == result.getSendStatus();
		} catch (MQClientException e) {
			
			logger.info("send crawling event failed. message body is: " + messageBody.toString() + ", exception message is: " + e.getMessage(), e);
			throw new WebAppRuntimeException("crawling_event_message_sent_failed", e);
		}
	}
}