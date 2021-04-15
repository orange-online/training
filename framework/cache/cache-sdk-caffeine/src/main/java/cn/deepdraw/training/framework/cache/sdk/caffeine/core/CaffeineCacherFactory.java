package cn.deepdraw.training.framework.cache.sdk.caffeine.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.deepdraw.training.framework.cache.sdk.caffeine.setting.CaffeineCacheSetting;

/**
 * CaffeineCacher Factory
 * @author huangjiancheng
 * @Date 2020-08-25
 */
public class CaffeineCacherFactory {

	private static Map<String, CaffeineCacher<?, ?>> INSTANCES = new ConcurrentHashMap<>();

	@SuppressWarnings("unchecked")
	public static <K, V> CaffeineCacher<K, V> getInstance(CaffeineCacheSetting<K, V> setting) {

		String instanceKey = setting.policy();
		if (!INSTANCES.containsKey(instanceKey)) {

			INSTANCES.putIfAbsent(instanceKey, CaffeineCacher.of(setting));
		}
		return (CaffeineCacher<K, V>) INSTANCES.get(instanceKey);
	}

	private CaffeineCacherFactory() {}
}