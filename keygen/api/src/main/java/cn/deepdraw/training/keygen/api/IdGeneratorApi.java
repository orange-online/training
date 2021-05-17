package cn.deepdraw.training.keygen.api;

import java.util.List;

/**
 * ID Generator Api
 * @author huangjiancheng
 * @Date 2021-05-17
 */
public interface IdGeneratorApi {

	Long generateId();
	
	List<Long> generateIds(long size);
}