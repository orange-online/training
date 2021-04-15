package cn.deepdraw.training.framework.exception.constants;

import cn.deepdraw.training.framework.exception.Message;

public enum CommonExceptionMessageConstants implements Message {

	BAD_REQUEST("BAD_REQUEST", "无效的请求，请核对请求参数是否合法。"),

	METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "方法不被允许，请核对请求目标地址是否合法。"),

	INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "内部服务错误，请联系管理员。"),
	;

	private String code;

	private String message;

	CommonExceptionMessageConstants(String code, String message) {

		this.code = code;
		this.message = message;
	}

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}