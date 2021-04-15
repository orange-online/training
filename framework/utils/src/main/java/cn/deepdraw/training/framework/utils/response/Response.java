package cn.deepdraw.training.framework.utils.response;

import java.io.Serializable;

/**
 * 请求响应封装
 * @author huangjiancheng
 * @date Mar 29, 2018
 */
public class Response implements Serializable {

	private static final long serialVersionUID = Long.MAX_VALUE;

	public static final String OK = "200";

	public static final String BAD_REQUEST = "400";
	public static final String ACCESS_FORBIDDEN = "403";
	public static final String REQUEST_NOT_FOUND = "404";
	public static final String METHOD_NOT_ALLOWED = "405";
	public static final String INTERNAL_ERROR = "500";
	public static final String BAD_GATEWAY = "502";

	private String code;

	private Object body;

	private String message;

	public Response() {
		
		this.code = OK;
	}

	public Response(Object data) {

		this.code = OK;
		this.body = data;
	}

	public Response(String code) {

		this.code = code;
	}

	public Response(String code, String message) {

		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean successful() {

		return OK.equals(this.code);
	}
}