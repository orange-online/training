package cn.deepdraw.training.crawler.novel.app.domain.shared;

import java.io.Serializable;

/**
 * 值对象接口
 * @author huangjiancheng
 * 2020-06-09
 */
public interface ValueObject<T> extends Serializable {

	boolean equalTo(T object);
}