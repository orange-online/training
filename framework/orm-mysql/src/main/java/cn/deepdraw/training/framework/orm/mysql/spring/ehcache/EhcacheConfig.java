package cn.deepdraw.training.framework.orm.mysql.spring.ehcache;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import cn.deepdraw.training.framework.orm.mysql.spring.datasource.DatasourceConfig;

/**
 * Ehcache Config
 * @author huangjiancheng
 * 2018-09-03
 */
@Configuration
@ConditionalOnBean(DatasourceConfig.class)
@EnableCaching
public class EhcacheConfig {

}