package cn.deepdraw.training.framework.motan.spring.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;

/**
 * motan client配置
 * @author huangjiancheng
 * @date Jul 16, 2018
 */
@Configuration
@AutoConfigureAfter({ MotanConfig.class })
public class MotanClientConfig {

	@Bean(name = "baseReferer")
	@ConfigurationProperties(prefix = "motan.client")
    public BasicRefererConfigBean baseRefererConfig() {

        return new BasicRefererConfigBean();
    }
}