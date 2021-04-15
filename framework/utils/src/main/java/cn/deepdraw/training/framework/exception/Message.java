package cn.deepdraw.training.framework.exception;

import java.io.Serializable;

/**
 * 消息接口
 * @author huangjiancheng
 * 2018-08-07
 */
public interface Message extends Serializable {

	public String getCode();

	public String getMessage();
}