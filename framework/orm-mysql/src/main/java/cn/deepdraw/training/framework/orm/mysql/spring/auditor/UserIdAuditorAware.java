package cn.deepdraw.training.framework.orm.mysql.spring.auditor;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import cn.deepdraw.training.framework.utils.session.Session;
import cn.deepdraw.training.framework.utils.session.SessionContext;

/**
 * 帐号审计人
 * @author huangjiancheng
 * @date Jul 25, 2018
 */
public class UserIdAuditorAware implements AuditorAware<Long> {

	@Override
	public Optional<Long> getCurrentAuditor() {

		Session session = SessionContext.getInstance().get();
		return Optional.ofNullable(session != null && session.getUserId() != null ? session.getUserId() : null);
	}
}