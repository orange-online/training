package cn.deepdraw.training.crawler.novel.crawler.gateway.app.infrastructure.motan;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;

import cn.deepdraw.training.framework.motan.spring.config.MotanConfig;

/**
 * motan server配置
 * @author huangjiancheng
 * @date Jul 16, 2018
 */
@Configuration
@AutoConfigureAfter({ MotanConfig.class })
public class NovelCrawlerApiMotanClientConfig {

	@Bean(name = "xuanshuwangCrawlerBaseReferer")
	@ConfigurationProperties(prefix = "motan.client-crawler-xuanshuwang")
	public BasicRefererConfigBean xuanshuwangCrawlerBaseRefererConfig() {

		return new BasicRefererConfigBean();
	}

	@Bean(name = "liudatxtCrawlerBaseReferer")
	@ConfigurationProperties(prefix = "motan.client-crawler-liudatxt")
	public BasicRefererConfigBean liudatxtCrawlerBaseRefererConfig() {

		return new BasicRefererConfigBean();
	}

	@Bean(name = "biqugeCrawlerBaseReferer")
	@ConfigurationProperties(prefix = "motan.client-crawler-biquge")
	public BasicRefererConfigBean biqugeCrawlerBaseRefererConfig() {

		return new BasicRefererConfigBean();
	}
}