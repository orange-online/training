package cn.deepdraw.training.framework.utils.session;

import java.io.Serializable;
import java.util.UUID;

/**
 * 会话：存储当前用户相关信息
 * @author huangjiancheng 2018-10-10
 */
public class Session implements Serializable {

	private static final long serialVersionUID = Long.MAX_VALUE;

	private String traceId;

	private Long userId;

	public Session() {

		this.traceId = UUID.randomUUID().toString().replace("-", "");
	}

	public Session(Long userId) {

		this();
		this.userId = userId;
	}

	public static Session of(Long userId) {

		return new Session(userId);
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}