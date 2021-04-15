package cn.deepdraw.training.framework.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.type.TypeFactory;


/**
 * json工具类
 * 
 * @author milaneuo
 */
public final class JsonUtils {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	private static final TypeReference<HashMap<String, Object>> HASH_MAP_TYPE = new TypeReference<HashMap<String, Object>>() {};

	private static final TypeReference<ArrayList<Object>> LIST_TYPE = new TypeReference<ArrayList<Object>>() {};

	private static final ObjectMapper mapper = new ObjectMapper();

	static {
		// 允许单引号做为字符串定界符
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		// 允许fieldName不含任何引号
		mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// 忽略未知(如新加)的field
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 忽略null,以减少序列化结果大小,优化传输速度
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
	}

	public static <T> T parse(String json, Class<T> clazz) {
	
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {

			logger.error("Cannot parse json: " + json + ", clazz is " + clazz, e);
			return null;
		}
	}

	public static <T> T parse(String json, TypeReference<T> type) {

		try {

			return mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true).readValue(json, type);
		} catch (IOException e) {

			logger.error("Cannot parse json: " + json + ", type is " + type, e);
			return null;
		}
	}

	public static Map<String, Object> parse(String json) {

		try {
			return mapper.readValue(json, HASH_MAP_TYPE);
		} catch (IOException e) {

			logger.error("Cannot parse json: " + json, e);
			return new HashMap<String, Object>(0);
		}
	}

	public static JsonNode read(String json) {

		try {
			return mapper.readTree(json);
		} catch (IOException e) {

			logger.error("Cannot parse json: " + json, e);
			return NullNode.instance;
		}
	}

	public static List<Object> parseObjects(String json) {

		try {
			return mapper.readValue(json, LIST_TYPE);
		} catch (IOException e) {

			logger.error("Cannot parse json: " + json, e);
			return new ArrayList<Object>(0);
		}
	}

	public static <E> List<E> parseObjects(String json, Class<E> elementClass) {

		try {

			return mapper.readValue(json, TypeFactory.defaultInstance().constructCollectionType(List.class, elementClass));
		} catch (IOException e) {

			logger.error("Cannot parse json: " + json, e);
			return new ArrayList<E>(0);
		}
	}

	public static <V> Map<String, V> parseMap(String json, Class<V> valueClass) {

		return parseMap(json, String.class, valueClass);
	}

	public static <K, V> Map<K, V> parseMap(String json, Class<K> keyClass, Class<V> valueClass) {

		try {

			return mapper.readValue(json, TypeFactory.defaultInstance().constructMapType(HashMap.class, keyClass, valueClass));
		} catch (IOException e) {

			logger.error("Cannot parse json: " + json, e);
			return new HashMap<K, V>(0);
		}
	}

	public static String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error("Cannot format json: " + object, e);
			return "{}";
		}
	}

	public static JsonNode toJsonNode(Object object) {
		
		return mapper.convertValue(object, JsonNode.class);
	}
	
	private JsonUtils() {}
}