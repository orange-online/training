package cn.deepdraw.training.framework.rocketmq.core.setting;

import java.io.Serializable;

/**
 * Producer Setting
 * @author huangjiancheng
 * 2020-07-11
 */
public class DefaultRocketmqSetting implements RocketmqSetting, Serializable {

	private static final long serialVersionUID = 20200711L;

	private String group;

	private String namesrvAddr;

	protected DefaultRocketmqSetting() {}

	protected DefaultRocketmqSetting(String group, String namesrvAddr) {

		this.setGroup(group);
		this.setNamesrvAddr(namesrvAddr);
	}

	public static DefaultRocketmqSetting of(String group, String namesrvAddr) {

		return new DefaultRocketmqSetting(group, namesrvAddr);
	}

	@Override
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public String getNamesrvAddr() {
		return namesrvAddr;
	}

	public void setNamesrvAddr(String namesrvAddr) {
		this.namesrvAddr = namesrvAddr;
	}
}