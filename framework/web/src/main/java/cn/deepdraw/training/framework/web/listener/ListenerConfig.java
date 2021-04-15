package cn.deepdraw.training.framework.web.listener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 应用监听器配置
 * 
 * @author huangjiancheng 2018-12-08
 */
@Configuration
public class ListenerConfig {

	@Bean
	public ServletListenerRegistrationBean<ServletContextListener> serssionListenerBean() {

		return new ServletListenerRegistrationBean<ServletContextListener>(servletContextListener());
	}

	@Bean
	public ServletContextListener servletContextListener() {

		return new ServletContextListener();
	}
}