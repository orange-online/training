package cn.deepdraw.training.framework.motan.spring.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weibo.api.motan.config.springsupport.BasicServiceConfigBean;

/**
 * motan server配置
 * @author huangjiancheng
 * @date Jul 16, 2018
 */
@Configuration
@AutoConfigureAfter({ MotanConfig.class })
public class MotanServerConfig {

	@Bean(name = "baseService")
	@ConfigurationProperties(prefix = "motan.server")
	public BasicServiceConfigBean baseServiceConfig() {

		return new BasicServiceConfigBean();
	}
}