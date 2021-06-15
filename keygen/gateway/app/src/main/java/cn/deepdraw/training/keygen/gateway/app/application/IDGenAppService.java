package cn.deepdraw.training.keygen.gateway.app.application;

import java.util.List;

/**
 * IdGen AppService
 * @author huangjiancheng
 * @Date 2021-05-28
 */
public interface IDGenAppService {

	Long genId();

	List<Long> genIds(int size);
}