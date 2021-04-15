package cn.deepdraw.training.framework.orm.mysql.spring.datasource;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 数据源YAML文件初始化器
 * @author huangjiancheng
 * @date Jul 11, 2018
 */
public class DatasourceConfigContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static final String SPRING_TOMCAT_LOCATION = "classpath:/datasources/datasource-tomcat.yml";

	private static final String SPRING_TOMCAT_ROOT_NAME = "datasource";

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {

		YamlPropertySourceInitializer.initialize(applicationContext, SPRING_TOMCAT_LOCATION, SPRING_TOMCAT_ROOT_NAME);
	}
}