package cn.deepdraw.training.keygen.gateway.api;

import java.util.List;

/**
 * ID Generator Api
 * @author huangjiancheng
 * @Date 2021-05-17
 */
public interface IDGenApi {

	Long genId();
	
	List<Long> genIds(int size);
}