package cn.deepdraw.training.framework.orm.mysql.spring.auditor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import cn.deepdraw.training.framework.orm.mysql.spring.datasource.DatasourceConfig;

/**
 * AuditorAware Configuration
 * @author huangjiancheng
 * 2018-08-16
 */
@Configuration
@ConditionalOnBean(DatasourceConfig.class)
public class AuditorAwareConfig {

	@Bean("defaultAAAware")
	public AuditorAware<Long> userIdAuditorAware() {

		return new UserIdAuditorAware();
	}
}