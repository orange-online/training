package cn.deepdraw.training.keygen.config.center.app.domain.core;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.keygen.config.center.app.domain.shared.ValueObject;

/**
 * IDSegmentPoolConfig
 * @author huangjiancheng
 * @Date 2021-10-11
 */
@Component
public class IDSegmentPoolConfig implements ValueObject<IDSegmentPoolConfig> {

	private static final long serialVersionUID = Long.MAX_VALUE;

	@Value("${id_segment_pool.capacity}")
	private int capacity;
	
	private IDSegmentPoolConfig() {}
	
	public int capacity() {
		
		return capacity;
	}

	@Override
	public boolean equalTo(IDSegmentPoolConfig config) {
		
		return config != null && (this == config || (ObjectUtils.equals(this.capacity(), config.capacity())));
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
			
			return false;
		} else if (this == obj) {
			
			return true;
		} else if (obj instanceof IDSegmentPoolConfig) {
			
			return equals((IDSegmentPoolConfig) obj);
		} else {
			
			return false;
		}
	}

	@Override
	public int hashCode() {
		
		return this.toString().hashCode();
	}

	@Override
	public String toString() {
		
		return "{\"capacity\":" + this.capacity() + "}";
	}
	
	
}
