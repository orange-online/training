package cn.deepdraw.training.framework.web.auth.sso;

import javax.servlet.http.HttpServletRequest;

import cn.deepdraw.training.framework.web.auth.AuthApi;

/**
 * 单点登陆认证API
 * @author huangjiancheng
 * 2018-12-07
 */
public interface SSOAuthApi extends AuthApi {

	default String getAccessToken(HttpServletRequest request) {

		return request.getHeader("access_token");
	}

	default String getRefreshToken(HttpServletRequest request) {

		return request.getHeader("refresh_token");
	}
}