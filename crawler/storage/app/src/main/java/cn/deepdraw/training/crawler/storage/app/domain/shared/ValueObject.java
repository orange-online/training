package cn.deepdraw.training.crawler.storage.app.domain.shared;

import java.io.Serializable;

/**
 * @Description 值对象，以值来判等，而非id
 * @author xujunfeng
 * @date: 2019年9月19日
 */
public interface ValueObject<T> extends Serializable {

	boolean equalTo(T object);
}