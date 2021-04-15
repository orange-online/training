package cn.deepdraw.training.framework.cache.sdk.caffeine.core;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.github.benmanes.caffeine.cache.stats.ConcurrentStatsCounter;

import cn.deepdraw.training.framework.cache.sdk.caffeine.CaffeineCache;
import cn.deepdraw.training.framework.cache.sdk.caffeine.setting.CaffeineCacheSetting;

/**
 * Caffeine Cacher
 * @author huangjiancheng
 * @Date 2020-08-25
 * @param <K>
 * @param <V>
 */
public class CaffeineCacher<K, V> implements CaffeineCache<K, V> {

	private Cache<K, V> cache;

	private CaffeineCacher() {}

	private CaffeineCacher(CaffeineCacheSetting<K, V> setting) {

		Caffeine<Object, Object> builder = Caffeine.newBuilder();
		prepareRequiredConfigs(builder, setting);
		prepareOptionalConfigs(builder, setting);
		prepareCacheInstance(builder, setting);
	}

	private void prepareRequiredConfigs(Caffeine<Object, Object> builder, CaffeineCacheSetting<K, V> setting) {

		builder.initialCapacity(setting.initialCapacity()).maximumSize(setting.maximumSize());
		if (setting.expireAfterAccess() >= 0) {

			builder.expireAfterAccess(setting.expireAfterAccess(), TimeUnit.SECONDS);
		}
		if (setting.expireAfterWrite() >= 0) {

			builder.expireAfterWrite(setting.expireAfterWrite(), TimeUnit.SECONDS);
		}
	}

	private void prepareOptionalConfigs(Caffeine<Object, Object> builder, CaffeineCacheSetting<K, V> setting) {

		if (setting.loader() != null && setting.refreshAfterWrite() > 0) {

			builder.refreshAfterWrite(setting.refreshAfterWrite(), TimeUnit.NANOSECONDS);
		}
		if (setting.recordStats()) {

			builder.recordStats(() -> new ConcurrentStatsCounter());
		}
		if (setting.weakKeys()) {

			builder.weakKeys();
		}
		if (setting.weakValues()) {

			builder.weakValues();
		}
	}

	private void prepareCacheInstance(Caffeine<Object, Object> builder, CaffeineCacheSetting<K, V> setting) {

		cache = setting.loader() == null ? builder.build() : builder.build(setting.loader());
	}

	protected static <K, V> CaffeineCacher<K, V> of(CaffeineCacheSetting<K, V> setting) {

		return new CaffeineCacher<K, V>(setting);
	}

	@Override
	public V get(K key) {

		return cache.getIfPresent(key);
	}

	@Override
	public V get(K key, Function<? super K, ? extends V> mappingFunction) {

		return cache.get(key, mappingFunction);
	}

	@Override
	public Map<K, V> getAllPresent(Iterable<? extends K> keys) {

		return cache.getAllPresent(keys);
	}

	@Override
	public void put(K key, V value) {

		cache.put(key, value);
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> map) {

		cache.putAll(map);
	}

	@Override
	public void invalidate(Object key) {

		cache.invalidate(key);
	}

	@Override
	public void invalidateAll(Iterable<? extends K> keys) {

		cache.invalidateAll(keys);
	}

	@Override
	public void invalidateAll() {

		cache.invalidateAll();
	}

	@Override
	public long size() {

		return cache.estimatedSize();
	}

	@Override
	public CacheStats stats() {

		return cache.stats();
	}

	@Override
	public ConcurrentMap<K, V> asMap() {

		return cache.asMap();
	}

	@Override
	public void cleanUp() {

		cache.cleanUp();
	}
}