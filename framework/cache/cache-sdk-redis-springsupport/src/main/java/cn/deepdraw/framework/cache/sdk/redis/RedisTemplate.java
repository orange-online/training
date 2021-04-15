package cn.deepdraw.framework.cache.sdk.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;

/**
 * Redis Template
 * @author huangjiancheng
 * @Date 2020-11-16
 * @param <K>
 * @param <V>
 */
public class RedisTemplate<K, V> extends org.springframework.data.redis.core.RedisTemplate<K, V> {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final ThreadLocal<Integer> dbIndex = new ThreadLocal<Integer>() {
		
		@Override
		protected Integer initialValue() {
			
			return 0;
		}
	};

	@Override
	protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
		
		try {
			
			Integer index = dbIndex.get();
			if (index != null) { // 如果设置了dbIndex
				
				if (connection instanceof JedisConnection) {
					
					if (((JedisConnection) connection).getNativeConnection().getDB().intValue() != index.intValue()) {
						
						connection.select(index);
					}
				} else {
					
					connection.select(index);
				}
			} else {
				
				connection.select(0);
			}
		}catch (Exception e) {
			
			logger.error("pre-process redis connection failed, exception message is " + e.getMessage(), e);
		} finally {
			
			dbIndex.remove();
		}
		return super.preProcessConnection(connection, existingConnection);
	}
	
	public static void setDBIndex(int dbIndex) {
		
		RedisTemplate.dbIndex.set(dbIndex);
	}
}