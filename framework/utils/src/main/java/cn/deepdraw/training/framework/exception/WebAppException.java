package cn.deepdraw.training.framework.exception;

/**
 * 自定义checked异常
 * @author HuangJianCheng
 * 2018-01-13 16:29
 */
public class WebAppException extends Exception {

	private static final long serialVersionUID = Long.MAX_VALUE;

	protected String code;

	public WebAppException() {

		super();
	}

	public WebAppException(String message) {

		super(message);
	}

	public WebAppException(Throwable cause) {

		super(cause);
	}

	public WebAppException(String message, Throwable cause) {

		super(message, cause);
	}

	public WebAppException(String code, String message) {

		super(message);
		this.code = code;
	}

	public WebAppException(String code, String message, Throwable cause) {

		super(message, cause);
		this.code = code;
	}

	public WebAppException(Message message) {

		super(message.getMessage());
		this.code = message.getCode();
	}

	public WebAppException(Message message, Throwable cause) {

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