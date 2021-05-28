package cn.deepdraw.training.keygen.api;

import java.util.List;

/**
 * ID Generator Api
 * @author huangjiancheng
 * @Date 2021-05-17
 */
public interface IdGenApi {

	Long genId();
	
	List<Long> genIds(long size);
}