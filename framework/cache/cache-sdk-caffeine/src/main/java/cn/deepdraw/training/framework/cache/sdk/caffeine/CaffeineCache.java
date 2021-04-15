package cn.deepdraw.training.framework.cache.sdk.caffeine;

import com.github.benmanes.caffeine.cache.stats.CacheStats;

import cn.deepdraw.training.framework.cache.sdk.Cache;

/**
 * Caffeine Cache API
 * @author huangjiancheng
 * @Date 2020-08-25
 * @param <K>
 * @param <V>
 */
public interface CaffeineCache<K, V> extends Cache<K, V> {

	CacheStats stats();
}