package cn.deepdraw.training.framework.web.auth.sso;

import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.deepdraw.training.framework.utils.HttpUtils;
import cn.deepdraw.training.framework.utils.HttpUtils.Reply;
import cn.deepdraw.training.framework.utils.JsonUtils;
import cn.deepdraw.training.framework.utils.collection.MultiValueHashMap;
import cn.deepdraw.training.framework.utils.collection.MultiValueMap;
import cn.deepdraw.training.framework.utils.session.Session;
import cn.deepdraw.training.framework.web.auth.RegisterResponseApi;

/**
 * Session注册机
 * @author huangjiancheng
 * 2018-12-07
 */
@Component
public class SessionRegister implements RegisterResponseApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public Session registry(String url, String token) {

		if (StringUtils.isBlank(url) || StringUtils.isBlank(token)) {

			return null;
		}
		MultiValueMap<String, String> parameters = new MultiValueHashMap<>();
		parameters.add("access_token", token);
		Reply reply = HttpUtils.get(url, parameters);
		logger.info("SSO auth reply is " + JsonUtils.toJson(reply));
		if (Status.OK != reply.getStatus()) {

			return null;
		}
		return getSession(reply.getContent());
	}

	private Session getSession(String response) {

		if (!successful(response)) {

			return null;
		}
		return new Session(fieldValue(response, "user_id"));
	}
}