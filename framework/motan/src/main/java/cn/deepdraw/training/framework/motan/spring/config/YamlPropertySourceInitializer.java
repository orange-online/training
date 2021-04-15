package cn.deepdraw.training.framework.motan.spring.config;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

/**
 * Yaml属性源初始化器
 * @author huangjiancheng
 * @date Jul 18, 2018
 */
public class YamlPropertySourceInitializer {

	private static Logger logger = LoggerFactory.getLogger(YamlPropertySourceInitializer.class);

	public static void initialize(ConfigurableApplicationContext applicationContext, String location, String root) {

		try {

			logger.info("initializing yaml file: " + location);
			Resource resource = applicationContext.getResource(location);
			YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader();
			List<PropertySource<?>> yamlTestProperties = sourceLoader.load(root, resource);
			yamlTestProperties.forEach(propertySource -> applicationContext.getEnvironment().getPropertySources().addLast(propertySource));
		} catch (IOException e) {

			logger.error(location + " not found, please check.", e);
			throw new RuntimeException(e);
		}
	}
}