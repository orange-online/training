package cn.deepdraw.training.framework.motan.spring.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Motan YAML文件初始化器
 * @author huangjiancheng
 * @date Jul 11, 2018
 */
public class MotanConfigContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static final String SPRING_MOTAN_LOCATION = "classpath:/motan/motan-spring.yml";

	private static final String SPRING_MOTAN_ROOT_NAME = "motan";

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {

		YamlPropertySourceInitializer.initialize(applicationContext, SPRING_MOTAN_LOCATION, SPRING_MOTAN_ROOT_NAME);
	}
}