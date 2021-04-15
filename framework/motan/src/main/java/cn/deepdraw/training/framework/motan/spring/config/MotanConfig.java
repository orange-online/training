package cn.deepdraw.training.framework.motan.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;

/**
 * motan通用配置
 * @author huangjiancheng
 * @date Jul 16, 2018
 */
@Configuration
public class MotanConfig {

	/**
	 * AnnotationBean的配置需要设置为静态方法
	 * 参考链接: https://blog.csdn.net/u011686226/article/details/53841227
	 * @return
	 */
	@Bean(name = "annotation")
	@ConfigurationProperties(prefix = "motan.annotation")
	public static AnnotationBean motanAnnotation() {

		return new AnnotationBean();
	}

	@Bean(name = "protocol")
	@ConfigurationProperties(prefix = "motan.protocol")
	public ProtocolConfigBean protocolConfigBean() {

		return new ProtocolConfigBean();
	}

	@Bean(name = "registry")
	@ConfigurationProperties(prefix = "motan.registry")
	public RegistryConfigBean zkRegistry() {

		return new RegistryConfigBean();
	}
}