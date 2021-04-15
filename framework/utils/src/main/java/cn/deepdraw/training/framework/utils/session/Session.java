package cn.deepdraw.training.framework.utils.session;

import java.io.Serializable;

/**
 * 会话：存储当前用户相关信息
 * @author huangjiancheng
 * 2018-10-10
 */
public class Session implements Serializable {

	private static final long serialVersionUID = Long.MAX_VALUE;

	private String userId;

	public Session() {}

	public Session(String userId) {

		this.setUserId(userId);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}