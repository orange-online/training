package cn.deepdraw.training.framework.web.auth.sso;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.framework.utils.session.Session;
import cn.deepdraw.training.framework.utils.session.SessionContext;
import cn.deepdraw.training.framework.web.auth.AuthExceptionMessageConstants;

/**
 * 单点登陆认证接口服务
 * @author huangjiancheng
 * 2018-12-07
 */
@Component
public class SSOAuthApiService implements SSOAuthApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${sso.auth.url:}")
	private String url;
	
	@Autowired
	private SessionRegister register;

	@Override
	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String accessToken = getAccessToken(request);
		logger.info("servlet path: " + request.getServletPath() + ", access_token: " + accessToken);
		if (StringUtils.isBlank(accessToken)) { // access_token cannot be blank, even if there is no need to initiate the sso authentication. by huangjiancheng 2020-09-07

			throw new WebAppRuntimeException(AuthExceptionMessageConstants.ACCESS_TOKEN_ILLEGAL);
		}
		if (StringUtils.isBlank(url)) { // if `authUrl` is blank, it seems that there is no need to initiate the sso authentication. by huangjiancheng 2020-09-07
			
			return;
		}
		Session session = register.registry(url, accessToken);
		if (session == null) {

			SessionContext.getInstance().remove();
			throw new WebAppRuntimeException(AuthExceptionMessageConstants.ACCESS_TOKEN_AUTH_FAILED);
		}
		SessionContext.getInstance().set(session);
	}
}