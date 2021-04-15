package cn.deepdraw.training.framework.orm.mysql.spring.datasource;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 持久化配置
 * TODO 该配置作为一个临时方案，以解决Bean配置无法注入spring.jpa.hibernate.*的问题
 * @author huangjiancheng
 * @date Jul 12, 2018
 */
@Configuration
@ConditionalOnBean(DatasourceConfig.class)
@AutoConfigureAfter({ DatasourceConfig.class })
@ConditionalOnResource(resources = "classpath:/spring-persistence.xml")
@ImportResource("classpath:/spring-persistence.xml")
public class PersistenceXmlConfig {
}