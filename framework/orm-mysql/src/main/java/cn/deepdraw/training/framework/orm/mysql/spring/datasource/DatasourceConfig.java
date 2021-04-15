package cn.deepdraw.training.framework.orm.mysql.spring.datasource;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

/**
 * 数据源配置
 * 
 * @author huangjiancheng
 * @date Jul 10, 2018
 */
@Configuration
// @ConditionalOnProperty(prefix = "datasource", name = "active", matchIfMissing = false) // 项目更名为orm-mysql后，只要引入了该组件，那么必须提供相应的配置，可选的意义已经不大了，故删除掉 by 黄建成
public class DatasourceConfig {

	@Bean
	@Primary
	@Qualifier(value = "datasource")
	@ConfigurationProperties(prefix = "datasource.tomcat")
	public DataSource datasource() {

		return new DataSource();
	}

	@Bean
	@Qualifier(value = "datasourceProxy")
	public TransactionAwareDataSourceProxy dataSourceProxy(@Qualifier("datasource") DataSource datasource) {

		return new TransactionAwareDataSourceProxy(datasource);
	}
}