package cn.deepdraw.training.framework.web.advice;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.deepdraw.training.framework.exception.WebAppException;
import cn.deepdraw.training.framework.exception.WebAppRuntimeException;
import cn.deepdraw.training.framework.exception.constants.CommonExceptionMessageConstants;
import cn.deepdraw.training.framework.utils.response.Response;
import cn.deepdraw.training.framework.utils.response.ResponseUtils;

/**
 * Controller异常统一处理
 * @author huangjiancheng
 * 2018-08-07
 */
@RestControllerAdvice
public class ControllerExceptionAdvice {

	private static Logger logger = LoggerFactory.getLogger(ControllerExceptionAdvice.class);

	/**
	 * 400 - Bad Request
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

		logger.error("bad request", e);
		return ResponseUtils.fail(CommonExceptionMessageConstants.BAD_REQUEST);
	}

	/**
	 * 405 - Method Not Allowed
	 */
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {

		logger.error("method not allowed", e);
		return ResponseUtils.fail(CommonExceptionMessageConstants.METHOD_NOT_ALLOWED);
	}

	/**
	 * 500 - Internal Server Error
	 */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public Response handleException(Exception e) {

		logger.error("internal error", e);
		if (instanceofInternalException(e)) {

			return ResponseUtils.fail(getResponseCode(e), e.getMessage());
		} else if (StringUtils.isNotBlank(e.getMessage())) {

			return ResponseUtils.fail(e.getMessage(), e.getMessage());
		} else {

			return ResponseUtils.fail(CommonExceptionMessageConstants.INTERNAL_SERVER_ERROR);
		}
	}

	private boolean instanceofInternalException(Exception e) {

		return instanceofWebAppException(e) || instanceofWebAppRuntimeException(e);
	}

	private String getResponseCode(Exception e) {

		return instanceofWebAppException(e) ? ((WebAppException) e).getCode() : ((WebAppRuntimeException) e).getCode();
	}

	private boolean instanceofWebAppException(Exception e) {

		return e instanceof WebAppException;
	}

	private boolean instanceofWebAppRuntimeException(Exception e) {

		return e instanceof WebAppRuntimeException;
	}
}