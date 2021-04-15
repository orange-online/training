package cn.deepdraw.training.framework.web.config;

import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.deepdraw.training.framework.web.converter.String2DateConverter;
import cn.deepdraw.training.framework.web.interceptor.ServletHandlerInterceptorAdapter;

@Configuration
public class WebMvcConfigurerAdapter implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {

		registry.addConverter(string2DateConverter());
		WebMvcConfigurer.super.addFormatters(registry);
	}

	@Bean
	public Converter<String, Date> string2DateConverter() {

		return new String2DateConverter();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(servletHandlerInterceptorAdapter()).addPathPatterns(ServletHandlerInterceptorAdapter.PATH_PATTERN);
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Bean
	public ServletHandlerInterceptorAdapter servletHandlerInterceptorAdapter() {

		return new ServletHandlerInterceptorAdapter();
	}
}