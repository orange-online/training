package cn.deepdraw.training.crawler.novel.app.infrastructure.motan;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weibo.api.motan.config.springsupport.BasicServiceConfigBean;

import cn.deepdraw.training.framework.motan.spring.config.MotanConfig;

/**
 * motan server配置
 * @author huangjiancheng
 * @date Jul 16, 2018
 */
@Configuration
@AutoConfigureAfter({ MotanConfig.class })
public class NovelCrawlingEventMotanServerConfig {

	@Bean(name = "novelCrawlingEventBaseService")
	@ConfigurationProperties(prefix = "motan.server-novel-crawling-event")
	public BasicServiceConfigBean novelCrawlingEventBaseServiceConfig() {

		return new BasicServiceConfigBean();
	}
}