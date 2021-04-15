package cn.deepdraw.training.framework.orm.mysql.spring.auditor;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.AuditorAware;

import cn.deepdraw.training.framework.utils.session.Session;
import cn.deepdraw.training.framework.utils.session.SessionContext;

/**
 * 帐号审计人
 * @author huangjiancheng
 * @date Jul 25, 2018
 */
public class UserIdAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		Session session = SessionContext.getInstance().get();
		return Optional.of(session != null && StringUtils.isNotBlank(session.getUserId()) ? session.getUserId() : "");
	}
}