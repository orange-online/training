package cn.deepdraw.framework.cache.sdk.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis Config
 * @author huangjiancheng
 * @Date 2020-11-16
 */
@Configuration
public class RedisConfig {
	
	@Value("${redis.hostName}")
	private String hostName;

	@Value("${redis.port}")
	private int port;

	@Value("${redis.password}")
	private String password;

	@Value("${redis.minIdle}")
	private int minIdle;

	@Value("${redis.maxIdle}")
	private int maxIdle;

	@Value("${redis.maxTotal}")
	private int maxTotal;

	@Value("${redis.maxWaitMillis}")
	private int maxWaitMillis;

	@Value("${redis.blockWhenExhausted}")
	private boolean blockWhenExhausted;

	@Value("${redis.testOnBorrow}")
	private boolean testOnBorrow;

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(hostName, port);
		config.setPassword(password);
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
		poolConfig.setBlockWhenExhausted(blockWhenExhausted);
		poolConfig.setTestOnBorrow(testOnBorrow);
		
		return new JedisConnectionFactory(config, JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig).build());
	}

	@Bean
	public RedisTemplate<String, Object> customizedRedisTemplate(RedisConnectionFactory connectionFactory) {
		
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new JdkSerializationRedisSerializer());
		template.setHashValueSerializer(new JdkSerializationRedisSerializer());
		template.setEnableTransactionSupport(true); // 开启事务
		template.setConnectionFactory(connectionFactory);
		template.afterPropertiesSet();
		return template;
	}

//	public static void main(String[] args) throws InterruptedException {
//		
//		RedisConfig config = new RedisConfig();
//		RedisTemplate<String,Object> template = config.customizedRedisTemplate(config.jedisConnectionFactory());
//		RedisTemplateProxy templateProxy = new RedisTemplateProxy();
//		templateProxy.setTemplate(template);
//		
//		Pet pet = new Pet();
//		pet.setName("cat");
//		pet.setGender("male");
//		System.out.println(templateProxy.set("cache_sdk_redis_version", pet));
//
//		List<Object> values = templateProxy.multiGet(Arrays.asList("hha", "cache_sdk_redis_version"));
//		System.out.println(values);
//		System.out.println(((Pet) values.get(1)).getName());
//		
////		System.out.println(templateProxy.hashGet("cache_sdk_redis_version", "cat"));
//		
//		templateProxy.expire("cache_sdk_redis_version", 1);
//		
//		Thread.sleep(2000L);
//
//		System.out.println(templateProxy.multiGet(Arrays.asList("hha", "cache_sdk_redis_version")));
//	}
//	
//	public static class Pet implements Serializable {
//		
//		private static final long serialVersionUID = 1L;
//
//		private String name;
//		
//		private String gender;
//
//		public String getName() {
//			return name;
//		}
//
//		public void setName(String name) {
//			this.name = name;
//		}
//
//		public String getGender() {
//			return gender;
//		}
//
//		public void setGender(String gender) {
//			this.gender = gender;
//		}
//	}
}