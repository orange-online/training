package cn.deepdraw.training.framework.web.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证API
 * @author huangjiancheng
 * 2018-12-07
 */
public interface AuthApi {

	public void authenticate(HttpServletRequest request, HttpServletResponse response) throws Exception;
}