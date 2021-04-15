package cn.deepdraw.training.framework.utils.response;

import cn.deepdraw.training.framework.exception.Message;

/**
 * 响应工具类
 * @author huangjiancheng
 * 2018-08-07
 */
public final class ResponseUtils {

	public static Response success() {

		return new Response();
	}
	
	public static Response success(Object body) {

		return new Response(body);
	}

	public static Response fail(String code) {

		return new Response(code);
	}

	public static Response fail(String code, String message) {

		return new Response(code, message);
	}

	public static Response fail(Message message) {

		return new Response(message.getCode(), message.getMessage());
	}
}