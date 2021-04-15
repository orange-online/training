package cn.deepdraw.training.framework.quartz.job;

import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 不支持并发执行的抽象Job基类
 * @author huangjiancheng
 * 2020-05-25
 */
@DisallowConcurrentExecution
public abstract class JobBase extends QuartzJobBean {

	protected Logger logger = LoggerFactory.getLogger(getClass());
}