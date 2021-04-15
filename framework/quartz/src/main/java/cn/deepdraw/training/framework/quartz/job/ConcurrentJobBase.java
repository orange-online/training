package cn.deepdraw.training.framework.quartz.job;

import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 基础且支持并发的抽象Job基类
 * @author huangjiancheng
 * 2020-05-25
 */
public abstract class ConcurrentJobBase extends QuartzJobBean {
}