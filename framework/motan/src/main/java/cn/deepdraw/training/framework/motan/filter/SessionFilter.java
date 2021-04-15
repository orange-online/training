package cn.deepdraw.training.framework.motan.filter;

import org.apache.commons.lang3.StringUtils;

import com.weibo.api.motan.core.extension.Scope;
import com.weibo.api.motan.core.extension.Spi;
import com.weibo.api.motan.core.extension.SpiMeta;
import com.weibo.api.motan.filter.Filter;
import com.weibo.api.motan.rpc.Caller;
import com.weibo.api.motan.rpc.Request;
import com.weibo.api.motan.rpc.Response;

import cn.deepdraw.training.framework.utils.JsonUtils;
import cn.deepdraw.training.framework.utils.session.Session;
import cn.deepdraw.training.framework.utils.session.SessionContext;

/**
 * Motan权限拦截器
 * @author huangjiancheng
 * 2018-10-09
 */
@SpiMeta(name = "session")
@Spi(scope = Scope.SINGLETON)
public class SessionFilter implements Filter {

	private static final String SESSION = "session";

	@Override
	public Response filter(Caller<?> caller, Request request) {

		SessionContext instance = SessionContext.getInstance();
		String sessionValue = request.getAttachments().get(SESSION);
		if (StringUtils.isNotBlank(sessionValue)) { // server端

			instance.set(JsonUtils.parse(sessionValue, Session.class));
		} else if (instance.get() != null) { // client端

			request.setAttachment(SESSION, JsonUtils.toJson(instance.get()));
		}
		return caller.call(request);
	}
}