package cn.deepdraw.training.framework.web.auth;

import com.fasterxml.jackson.databind.JsonNode;

import cn.deepdraw.training.framework.utils.JsonUtils;
import cn.deepdraw.training.framework.utils.response.Response;

/**
 * 认证响应API（解析认证中心的响应数据）
 * @author huangjiancheng
 * 2018-12-08
 */
public interface RegisterResponseApi {

	default JsonNode responseNode(String response) {

		return JsonUtils.parse(response, JsonNode.class);
	}

	default boolean successful(String response) {

		return Response.OK.equals(responseNode(response).path("code").asText());
	}

	default String fieldValue(String response, String fieldName) {

		return successful(response) ? responseNode(response).path("body").path(fieldName).asText(null) : null;
	}
}