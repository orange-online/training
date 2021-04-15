package cn.deepdraw.training.framework.web.listener;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ServletContext Listener
 * @author huangjiancheng
 * 2018-12-08
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(ServletContextListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {

		logger.info("context destoryed.");
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {

		logger.info("context initialized.");
	}
}