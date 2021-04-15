package cn.deepdraw.training.framework.quartz.config;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * 将QuartzJob交由Spring去托管
 * @author huangjiancheng
 * 2020-05-25
 */
public class QuartzJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

	private transient AutowireCapableBeanFactory beanFactory;

	@Override
	public void setApplicationContext(final ApplicationContext context) {

		beanFactory = context.getAutowireCapableBeanFactory();
	}

	/**
	 * 将job实例交给spring ioc托管 * 我们在job实例实现类内可以直接使用spring注入的调用被spring ioc管理的实例
	 */
	@Override
	protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {

		final Object job = super.createJobInstance(bundle);
		beanFactory.autowireBean(job); // 将job实例交付给spring ioc
		return job;
	}
}