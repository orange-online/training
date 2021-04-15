package cn.deepdraw.training.framework.cache.sdk;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * Cache
 * @author huangjiancheng
 * @Date 2020-08-25
 */
public interface Cache<K, V> {

	V get(K key);

	V get(K key, Function<? super K, ? extends V> mappingFunction);

	Map<K, V> getAllPresent(Iterable<? extends K> keys);

	void put(K key, V value);

	void putAll(Map<? extends K, ? extends V> m);

	void invalidate(Object key);

	void invalidateAll(Iterable<? extends K> keys);

	void invalidateAll();

	long size();

	ConcurrentMap<K, V> asMap();

	void cleanUp();
}