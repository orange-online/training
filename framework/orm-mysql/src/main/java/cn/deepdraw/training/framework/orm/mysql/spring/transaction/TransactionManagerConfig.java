package cn.deepdraw.training.framework.orm.mysql.spring.transaction;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cn.deepdraw.training.framework.orm.mysql.spring.datasource.PersistenceXmlConfig;

/**
 * 数据源配置（暂不启用）
 * 问题：使用该方式注入时，spring.jpa.hibernate.*属性注入失败。
 * 		该问题导致表/列名生成策略失效，从而带来一系列问题。
 * @author huangjiancheng
 * @date Jul 10, 2018
 */
@Configuration
@ConditionalOnBean(PersistenceXmlConfig.class)
@AutoConfigureAfter({ PersistenceXmlConfig.class })
@EntityScan("cn.deepdraw.training.**.domain")
@EnableJpaAuditing(auditorAwareRef = "defaultAAAware")
@EnableJpaRepositories(value = { "cn.deepdraw.training.**.repository", "cn.deepdraw.training.**.repo" })
//@EnableJpaRepositories(value = "cn.deepdraw.repository",
//						entityManagerFactoryRef = "entityManagerFactory",
//						transactionManagerRef = "transactionManager")
// 等同于<tx:annotation-driven />
@EnableTransactionManagement
public class TransactionManagerConfig {

//	@Autowired
	private JpaProperties jpaProperties;

//	@Autowired
//	@Qualifier(value = DataSourceConfiguration.DYNAMIC_ROUTING_DATA_SOURCE)
	private DataSource dataSource;

//	@Bean(name = "entityManagerFactoryBean")
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean(EntityManagerFactoryBuilder builder) {

		return builder.dataSource(dataSource)
						.properties(jpaProperties.getProperties())
						.packages("cn.deepdraw.**.entity")
						.build();
	}

//	@Primary
//	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory getEntityManagerFactory(EntityManagerFactoryBuilder builder) {

		return getEntityManagerFactoryBean(builder).getObject();
	}

//	@Primary
//	@Bean(name = "transactionManager")
	public PlatformTransactionManager getTransactionManager(EntityManagerFactoryBuilder builder) {

		return new JpaTransactionManager(getEntityManagerFactory(builder));
	}
}