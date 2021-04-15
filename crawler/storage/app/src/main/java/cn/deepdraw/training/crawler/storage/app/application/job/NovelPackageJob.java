package cn.deepdraw.training.crawler.storage.app.application.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.deepdraw.training.framework.quartz.job.JobBase;

/**
 * 小说打包应用服务
 * @author huangjiancheng
 * 2020-06-12
 */
public class NovelPackageJob extends JobBase {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("packaging...");
	}
}