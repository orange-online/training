package cn.deepdraw.training.storage.app.infrastructure.quartz;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import cn.deepdraw.training.storage.app.application.job.NovelPackageJob;

/**
 * 小说打包调度配置
 * @author huangjiancheng
 * 2020-06-12
 */
@Configuration
public class NovelPackageQuartzConfig {

	@Bean
	public CronTriggerFactoryBean userMessageSyncCronTriggerFactoryBean(@Qualifier("novelPackageJobDetailFactoryBean") JobDetail jobDetail,
																			@Value("${quartz.job.trigger.novel-package.group}") String group,
																			@Value("${quartz.job.trigger.novel-package.name}") String name,
																			@Value("${quartz.job.trigger.novel-package.start-delay}") Long startDelay,
																			@Value("${quartz.job.trigger.novel-package.cron-expression}") String cronExpression) {

		CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
		factoryBean.setJobDetail(jobDetail);
		factoryBean.setGroup(group);
		factoryBean.setName(name);
		factoryBean.setStartDelay(startDelay);
		factoryBean.setCronExpression(cronExpression);
		return factoryBean;
	}

	@Bean
	public JobDetailFactoryBean novelPackageJobDetailFactoryBean(@Value("${quartz.job.detail.novel-package.group}") String group, @Value("${quartz.job.detail.novel-package.name}") String name) {

		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(NovelPackageJob.class);
		factoryBean.setGroup(group);
		factoryBean.setName(name);
		return factoryBean;
	}
}