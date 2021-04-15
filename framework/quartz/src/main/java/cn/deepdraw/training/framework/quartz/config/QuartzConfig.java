package cn.deepdraw.training.framework.quartz.config;

import java.io.IOException;
import java.util.Properties;

import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Quartz Config
 * 
 * @author huangjiancheng 2020-05-25
 */
@Configuration
@EnableScheduling
@ConditionalOnResource(resources = "classpath:/quartz.properties")
public class QuartzConfig {

	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext) {

		QuartzJobFactory jobFactory = new QuartzJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory, Trigger... triggers) throws IOException {

		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setQuartzProperties(quartzProperties());
		factory.setJobFactory(jobFactory);
		factory.setTriggers(triggers);
		return factory;
	}

	@Bean
	public Properties quartzProperties() throws IOException {

		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
		propertiesFactoryBean.afterPropertiesSet();
		return propertiesFactoryBean.getObject();
	}
}