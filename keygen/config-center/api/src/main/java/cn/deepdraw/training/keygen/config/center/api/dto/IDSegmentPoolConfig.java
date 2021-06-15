package cn.deepdraw.training.keygen.config.center.api.dto;

import java.io.Serializable;

/**
 * IDSegmentPool Config
 * @author huangjiancheng
 * @Date 2021-06-03
 */
public class IDSegmentPoolConfig implements Serializable {
	
	private static final long serialVersionUID = 20210603L;
	
	private int capacity;

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}