package cn.deepdraw.training.framework.web.filter;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * Filter Config
 * 
 * @author huangjiancheng 2018-12-10
 */
@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<XssFilter> xssFilterRegistration() {

		FilterRegistrationBean<XssFilter> registration = new FilterRegistrationBean<XssFilter>(new XssFilter());
		registration.setDispatcherTypes(DispatcherType.REQUEST);
		registration.addUrlPatterns("/*");
		registration.setName("xssFilter");
		registration.setOrder(Ordered.LOWEST_PRECEDENCE);
		return registration;
	}
}