package cn.deepdraw.training.crawler.novel.app.infrastructure.mq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.rocketmq.core.producer.RocketmqProducerSetting;
import cn.deepdraw.training.framework.rocketmq.core.setting.DefaultRocketmqSetting;

/**
 * @author xjn
 * @description 小说章节爬取事件消息生产方配置
 * @date 2020/11/19
 */
@Component
public class NovelChapterRocketmqProducerSetting extends DefaultRocketmqSetting implements RocketmqProducerSetting {

	private static final long serialVersionUID = 20201126L;

	@Value("${rocketmq.producer.novel-chapter-crawling-event-message.namesrvAddr}")
	private String addr;

	@Value("${rocketmq.producer.novel-chapter-crawling-event-message.group}")
	private String group;

	@Value("${rocketmq.producer.novel-chapter-crawling-event-message.topic}")
	private String topic;

	@Value("${rocketmq.producer.novel-chapter-crawling-event-message.tag}")
	private String tag;

	@Override
	public String getGroup() {

		return group;
	}

	@Override
	public String getNamesrvAddr() {

		return addr;
	}

	@Override
	public String getTopic() {

		return topic;
	}

	@Override
	public String getTag() {

		return tag;
	}
}
