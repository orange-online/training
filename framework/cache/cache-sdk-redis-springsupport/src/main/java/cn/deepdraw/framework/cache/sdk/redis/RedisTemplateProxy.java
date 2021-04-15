package cn.deepdraw.framework.cache.sdk.redis;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * RedisTemplate Proxy
 * @author huangjiancheng
 * @Date 2020-11-16
 */
@Component
public class RedisTemplateProxy {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RedisTemplate<String, Object> template;
	
//	public void setTemplate(RedisTemplate<String, Object> template) {
//		
//		this.template = template;
//	}
	
	/*--------------------------------------------------------------Common----------------------------------------------------------------------------*/

	/**
	 * 指定缓存失效时间
	 * @param key  键
	 * @param time 时间(秒)
	 * @return
	 */
	public boolean expire(String key, long time) {
		
		try {
			
			if (time > 0) {
				
				template.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			
			logger.error("Redis[expire] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 根据key 获取过期时间
	 * @param key 键 不能为null
	 * @return 时间(秒) 返回0代表为永久有效
	 */
	public long getExpire(String key) {
		
		return template.getExpire(key, TimeUnit.SECONDS);
	}

	/**
	 * 判断key是否存在
	 * @param key 键
	 * @return true 存在 false不存在
	 */
	public boolean hasKey(String key) {
		
		try {
			
			return template.hasKey(key);
		} catch (Exception e) {

			logger.error("Redis[hasKey] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 删除缓存
	 * @param keys 可以传一个值 或多个
	 */
	public void del(String... keys) {
		
		if (keys != null && keys.length > 0) {
			
			if (keys.length == 1) {
				
				template.delete(keys[0]);
			} else {
				
				template.delete(Arrays.asList(keys));
			}
		}
	}
	
	/*--------------------------------------------------------------String----------------------------------------------------------------------------*/

	/**
	 * 普通缓存获取
	 * @param key 键
	 * @return 值
	 */
	public Object get(String key) {
//	public Object get(String key, int dbIndex) {
		
//		RedisTemplate.setDBIndex(dbIndex);
		return key == null ? null : template.opsForValue().get(key);
	}

	/**
	 * 普通缓存获取
	 * @param keys 键集
	 * @return 值集
	 */
	public List<Object> multiGet(Collection<String> keys) {

		return keys == null ? Collections.emptyList() : template.opsForValue().multiGet(keys);
	}

	/**
	 * 普通缓存放入
	 * @param key   键
	 * @param value 值
	 * @return true成功 false失败
	 */
	public boolean set(String key, Object value) {
//	public boolean set(String key, Object value, int dbIndex) {
		
		try {
			
//			RedisTemplate.setDBIndex(dbIndex);
			template.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {

			logger.error("Redis[set] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 普通缓存放入
	 * @param key   键
	 * @param value 值
	 * @return true成功 false失败
	 */
	public boolean multiSet(Map<? extends String, ? extends Object> map) {
//	public boolean set(String key, Object value, int dbIndex) {
		
		try {
			
//			RedisTemplate.setDBIndex(dbIndex);
			template.opsForValue().multiSet(map);
			return true;
		} catch (Exception e) {

			logger.error("Redis[multiSet] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 普通缓存放入并设置时间
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @return true成功 false 失败
	 */
	public boolean set(String key, Object value, long time) {
		
		try {
			
			if (time > 0) {
				
				template.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else {
				
				template.opsForValue().set(key, value);
			}
			return true;
		} catch (Exception e) {

			logger.error("Redis[set] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 递增
	 * @param key 键
	 * @param by  要增加几(大于0)
	 * @return
	 */
	public long incr(String key, long delta) {
		
		if (delta < 0) {

			throw new IllegalArgumentException("delta_should_greater_than_zero");
		}
		return template.opsForValue().increment(key, delta);
	}

	/**
	 * 递减
	 * @param key 键
	 * @param by  要减少几(小于0)
	 * @return
	 */
	public long decr(String key, long delta) {
		
		if (delta < 0) {

			throw new IllegalArgumentException("delta_should_greater_than_zero");
		}
		return template.opsForValue().increment(key, -delta);
	}

	/*--------------------------------------------------------------Map----------------------------------------------------------------------------*/
	
	/**
	 * HashGet
	 * @param key  键 不能为null
	 * @param hashKey 项 不能为null
	 * @return 值
	 */
	public Object hashGet(String key, String hashKey) {
		
		return template.opsForHash().get(key, hashKey);
	}

	/**
	 * 获取hashKey对应的所有键值
	 * @param key 键
	 * @return 对应的多个键值
	 */
	public Map<Object, Object> hashGetAll(String key) {
		
		return template.opsForHash().entries(key);
	}

	/**
	 * HashSet
	 * @param key 键
	 * @param map 对应多个键值
	 * @return true 成功 false 失败
	 */
	public boolean hashPutAll(String key, Map<String, Object> map) {
		
		try {
			
			template.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			
			logger.error("Redis[hashPutAll] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * HashSet 并设置时间
	 * @param key  键
	 * @param map  对应多个键值
	 * @param time 时间(秒)
	 * @return true成功 false失败
	 */
	public boolean hashPutAll(String key, Map<String, Object> map, long time) {
		
		try {
			
			template.opsForHash().putAll(key, map);
			if (time > 0) {
				
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			
			logger.error("Redis[hashPutAll] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 向一张hash表中放入数据,如果不存在将创建
	 * @param key   键
	 * @param hashKey  项
	 * @param value 值
	 * @return true 成功 false失败
	 */
	public boolean hashSet(String key, String hashKey, Object value) {
		
		try {
			
			template.opsForHash().put(key, hashKey, value);
			return true;
		} catch (Exception e) {
			
			logger.error("Redis[hashSet] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 向一张hash表中放入数据,如果不存在将创建
	 * @param key   键
	 * @param hashKey  项
	 * @param value 值
	 * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
	 * @return true 成功 false失败
	 */
	public boolean hashSet(String key, String hashKey, Object value, long time) {
		
		try {
			
			template.opsForHash().put(key, hashKey, value);
			if (time > 0) {
				
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			
			logger.error("Redis[hashSet] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 删除hash表中的值
	 * @param key  键 不能为null
	 * @param hashKeys 项 可以使多个 不能为null
	 */
	public void hashDel(String key, Object... hashKeys) {
		
		template.opsForHash().delete(key, hashKeys);
	}

	/**
	 * 判断hash表中是否有该项的值
	 * @param key  键 不能为null
	 * @param hashKey 项 不能为null
	 * @return true 存在 false不存在
	 */
	public boolean hashHasKey(String key, String hashKey) {
		
		return template.opsForHash().hasKey(key, hashKey);
	}

	/**
	 * hash递增 如果不存在,就会创建一个 并把新增后的值返回
	 * @param key  键
	 * @param hashKey 项
	 * @param delta   要增加几(大于0)
	 * @return
	 */
	public double hashIncr(String key, String hashKey, double delta) {
		
		return template.opsForHash().increment(key, hashKey, delta);
	}

	/**
	 * hash递减
	 * @param key  键
	 * @param hashKey  项
	 * @param delta  要减少记(小于0)
	 * @return
	 */
	public double hashDecr(String key, String hashKey, double delta) {
		
		return template.opsForHash().increment(key, hashKey, -delta);
	}

	/*--------------------------------------------------------------Set----------------------------------------------------------------------------*/
	
	/**
	 * 根据key获取Set中的所有值
	 * 
	 * @param key 键
	 * @return
	 */
	public Set<Object> setGet(String key) {
		
		try {
			
			return template.opsForSet().members(key);
		} catch (Exception e) {
			
			logger.error("Redis[setGet] failed, exception message is " + e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 根据value从一个set中查询,是否存在
	 * @param key   键
	 * @param value 值
	 * @return true 存在 false不存在
	 */
	public boolean setContains(String key, Object value) {
		
		try {
			
			return template.opsForSet().isMember(key, value);
		} catch (Exception e) {
			
			logger.error("Redis[setContains] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 将数据放入set缓存
	 * @param key    键
	 * @param values 值 可以是多个
	 * @return 成功个数
	 */
	public long setAdd(String key, Object... values) {
		
		try {
			
			return template.opsForSet().add(key, values);
		} catch (Exception e) {
			
			logger.error("Redis[setAdd] failed, exception message is " + e.getMessage(), e);
			return 0;
		}
	}

	/**
	 * 将set数据放入缓存
	 * @param key    键
	 * @param time   时间(秒)
	 * @param values 值 可以是多个
	 * @return 成功个数
	 */
	public long setAdd(String key, long time, Object... values) {
		
		try {
			
			Long count = template.opsForSet().add(key, values);
			if (time > 0) {
				
				expire(key, time);
			}
			return count;
		} catch (Exception e) {
			
			logger.error("Redis[setAdd] failed, exception message is " + e.getMessage(), e);
			return 0;
		}
	}

	/**
	 * 获取set缓存的长度
	 * @param key 键
	 * @return
	 */
	public long setSize(String key) {
		
		try {
			
			return template.opsForSet().size(key);
		} catch (Exception e) {
			
			logger.error("Redis[setSize] failed, exception message is " + e.getMessage(), e);
			return 0;
		}
	}

	/**
	 * 移除值为value的
	 * @param key    键
	 * @param values 值 可以是多个
	 * @return 移除的个数
	 */
	public long setRemove(String key, Object... values) {
		
		try {
			
			return template.opsForSet().remove(key, values);
		} catch (Exception e) {
			
			logger.error("Redis[setRemove] failed, exception message is " + e.getMessage(), e);
			return 0;
		}
	}
	
	/*--------------------------------------------------------------List----------------------------------------------------------------------------*/

	/**
	 * 获取list缓存的内容
	 * @param key   键
	 * @param start 开始
	 * @param end   结束 0 到 -1代表所有值
	 * @return
	 */
	public List<Object> listRange(String key, long start, long end) {
		
		try {
			
			return template.opsForList().range(key, start, end);
		} catch (Exception e) {
			
			logger.error("Redis[listRange] failed, exception message is " + e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 获取list缓存的长度
	 * @param key 键
	 * @return
	 */
	public long listSize(String key) {
		
		try {
			
			return template.opsForList().size(key);
		} catch (Exception e) {
			
			logger.error("Redis[listSize] failed, exception message is " + e.getMessage(), e);
			return 0;
		}
	}

	/**
	 * 通过索引 获取list中的值
	 * @param key   键
	 * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
	 * @return
	 */
	public Object listGet(String key, long index) {
		
		try {
			
			return template.opsForList().index(key, index);
		} catch (Exception e) {
			
			logger.error("Redis[listGet] failed, exception message is " + e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 将value追加list缓存
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒)
	 * @return
	 */
	public boolean listPush(String key, Object value) {
		
		try {
			
			template.opsForList().rightPush(key, value);
			return true;
		} catch (Exception e) {
			
			logger.error("Redis[listPush] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 将value追加list缓存
	 * @param key   键
	 * @param value 值
	 * @param time  时间(秒)
	 * @return
	 */
	public boolean listPush(String key, Object value, long time) {
		
		try {
			
			template.opsForList().rightPush(key, value);
			if (time > 0) {
				
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			
			logger.error("Redis[listPush] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 将values追加list缓存
	 * @param key   键
	 * @param values 值
	 * @param time  时间(秒)
	 * @return
	 */
	public boolean listPushAll(String key, List<Object> values) {
		
		try {
			
			template.opsForList().rightPushAll(key, values);
			return true;
		} catch (Exception e) {
			
			logger.error("Redis[listPushAll] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 将values追加list缓存
	 * @param key   键
	 * @param values 值
	 * @param time  时间(秒)
	 * @return
	 */
	public boolean listPushAll(String key, List<Object> values, long time) {
		
		try {
			
			template.opsForList().rightPushAll(key, values);
			if (time > 0) {
				
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			
			logger.error("Redis[listPushAll] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 根据索引修改list中的某条数据
	 * @param key   键
	 * @param index 索引
	 * @param value 值
	 * @return
	 */
	public boolean listReplace(String key, long index, Object value) {
		
		try {
			
			template.opsForList().set(key, index, value);
			return true;
		} catch (Exception e) {
			
			logger.error("Redis[listReplace] failed, exception message is " + e.getMessage(), e);
			return false;
		}
	}

	/**
	 * 移除N个值为value
	 * 
	 * @param key   键
	 * @param count 移除多少个
	 * @param value 值
	 * @return 移除的个数
	 */
	public long listRemove(String key, long count, Object value) {
		
		try {
			
			return template.opsForList().remove(key, count, value);
		} catch (Exception e) {
			
			logger.error("Redis[listRemove] failed, exception message is " + e.getMessage(), e);
			return 0;
		}
	}
}
