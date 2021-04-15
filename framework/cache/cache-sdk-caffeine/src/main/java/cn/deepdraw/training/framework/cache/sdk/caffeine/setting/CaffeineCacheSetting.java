package cn.deepdraw.training.framework.cache.sdk.caffeine.setting;

import com.github.benmanes.caffeine.cache.CacheLoader;

import cn.deepdraw.training.framework.cache.sdk.setting.CacheSetting;

/**
 * Caffeine Cache Setting
 * @author huangjiancheng
 * @Date 2020-08-25
 * @param <K>
 * @param <V>
 */
public interface CaffeineCacheSetting<K, V> extends CacheSetting {

	long EXPIRE_AFTER_ACCESS = -1;

	long REFRESH_AFTER_WRITE = 0;

	boolean RECORD_STATS = false;

	boolean WEAK_KEYS = false;

	boolean WEAK_VALUES = false;

	int initialCapacity();

	long maximumSize();

	/**
	 * this config's unit is seconds. It owns higher priority than expireAfterAccess.
	 * this config will not be enable if give it a negative number.
	 * @return
	 */
	long expireAfterWrite();

	/**
	 * this config's unit is seconds.
	 * this config will not be enable if give it a negative number.
	 * @return
	 */
	default long expireAfterAccess() {

		return EXPIRE_AFTER_ACCESS;
	}

	/**
	 * this config require a not-null loader.
	 * @return
	 */
	default long refreshAfterWrite() {

		return REFRESH_AFTER_WRITE;
	}

	default boolean recordStats() {

		return RECORD_STATS;
	}

	default boolean weakKeys() {

		return WEAK_KEYS;
	}

	default boolean weakValues() {

		return WEAK_VALUES;
	}

	default CacheLoader<K, V> loader() {

		return null;
	}
}