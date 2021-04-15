package cn.deepdraw.training.framework.web.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.framework.utils.JsonUtils;
import cn.deepdraw.training.framework.utils.response.ResponseUtils;
import cn.deepdraw.training.framework.web.auth.AuthExceptionMessageConstants;

public class ServletHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

	/**
	 * 只对业务接口做拦截，而业务接口前缀统一为/v1/...
	 */
	public static final String PATH_PATTERN = "/v*/**";
//
//	@Autowired
//	private SSOAuthApi authApi;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		if (!"OPTIONS".equals(request.getMethod())) { // 对OPTIONS预检请求直接放行，其他请求统一认证 by 黄建成 2019-09-09

			try {

//				ssoAuthApi.authenticate(request, response);
			} catch (WebAppRuntimeException exception) {

				return catchIfNeccessary(exception, response);
			}
		}
		return true;
	}

	private boolean catchIfNeccessary(WebAppRuntimeException exception, HttpServletResponse response) throws IOException {

		if (loginFailed(exception)) {

			response.setStatus(HttpStatus.SC_UNAUTHORIZED);
			response.getWriter().write(JsonUtils.toJson(ResponseUtils.fail("-1", "Please login first.")));
			return false;
		} else {

			throw exception;
		}
	}

	private boolean loginFailed(WebAppRuntimeException exception) {

		return AuthExceptionMessageConstants.ACCESS_TOKEN_AUTH_FAILED.getCode().equals(exception.getCode());
	}
}