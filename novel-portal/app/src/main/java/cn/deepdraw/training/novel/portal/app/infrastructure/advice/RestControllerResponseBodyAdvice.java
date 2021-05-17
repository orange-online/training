package cn.deepdraw.training.novel.portal.app.infrastructure.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import cn.deepdraw.training.framework.utils.response.Response;
import cn.deepdraw.training.framework.utils.response.ResponseUtils;

/**
 * RestController ResponseBodyAdvice
 * @author huangjiancheng
 * 2020-06-19
 */
@RestControllerAdvice
public class RestControllerResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
									Class<? extends HttpMessageConverter<?>> selectedConverterType,
									ServerHttpRequest request, ServerHttpResponse response) {

		return body instanceof Response ? body : ResponseUtils.success(body);
	}
}