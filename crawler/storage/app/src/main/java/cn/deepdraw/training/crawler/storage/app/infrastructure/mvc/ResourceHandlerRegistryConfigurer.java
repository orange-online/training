package cn.deepdraw.training.crawler.storage.app.infrastructure.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ResourceHandlerRegistry Configurer
 * @author huangjiancheng
 * 2020-07-16
 */
@Configuration
public class ResourceHandlerRegistryConfigurer implements WebMvcConfigurer {

	@Value("${app.dir}")
	private String dir;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/files/**").addResourceLocations("file:" + dir + "/");
	}
}