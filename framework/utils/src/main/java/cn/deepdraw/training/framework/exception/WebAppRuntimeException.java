package cn.deepdraw.training.framework.exception;

/**
 * 自定义unchecked异常（为配合事物管理，集成自RuntimeException）
 * @author HuangJianCheng
 * 2018-01-13 16:29
 */
public class WebAppRuntimeException extends RuntimeException {

	private static final long serialVersionUID = Long.MAX_VALUE;

	protected String code;

	public WebAppRuntimeException() {

		super();
	}

	public WebAppRuntimeException(String message) {

		super(message);
	}

	public WebAppRuntimeException(Throwable cause) {

		super(cause);
	}

	public WebAppRuntimeException(String message, Throwable cause) {

		super(message, cause);
	}

	public WebAppRuntimeException(String code, String message) {

		super(message);
		this.code = code;
	}

	public WebAppRuntimeException(String code, String message, Throwable cause) {

		super(message, cause);
		this.code = code;
	}

	public WebAppRuntimeException(Message message) {

		super(message.getMessage());
		this.code = message.getCode();
	}

	public WebAppRuntimeException(Message message, Throwable cause) {

		super(message.getMessage(), cause);
		this.code = message.getCode();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}