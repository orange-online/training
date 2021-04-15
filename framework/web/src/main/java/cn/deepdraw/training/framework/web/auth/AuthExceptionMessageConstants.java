package cn.deepdraw.training.framework.web.auth;

import cn.deepdraw.training.framework.exception.Message;

/**
 * 认证异常
 * @author huangjiancheng
 * 2018-09-21
 */
public enum AuthExceptionMessageConstants implements Message {

	ACCESS_TOKEN_ILLEGAL("access_token_illegal", "access_token illegal"),

	ACCESS_TOKEN_AUTH_FAILED("sso_auth_failed", "sso_auth failed");
	
	private String code;

	private String message;
	
	AuthExceptionMessageConstants(String code, String message) {

		setCode(code);
		setMessage(message);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}