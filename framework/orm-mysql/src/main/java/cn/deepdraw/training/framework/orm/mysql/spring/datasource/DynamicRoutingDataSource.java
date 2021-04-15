package cn.deepdraw.training.framework.orm.mysql.spring.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态路由数据源
 * @author huangjiancheng
 * @date Jul 11, 2018
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {

		return "default";
	}
}