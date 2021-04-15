package cn.deepdraw.training.framework.dubbo.filter;

import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;

import cn.deepdraw.training.framework.utils.session.Session;
import cn.deepdraw.training.framework.utils.session.SessionContext;

/**
 * Dubbo Session Filter
 * @author huangjiancheng
 * 2018-10-09
 */
public class SessionFilter implements Filter {

	private static final String SESSION = "session";

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

		SessionContext instance = SessionContext.getInstance();
		Object sessionAttachment = invocation.getObjectAttachment(SESSION);
		if (sessionAttachment != null) { // server端

			instance.set((Session) sessionAttachment);
		} else if (instance.get() != null) { // client端

			invocation.setObjectAttachment(SESSION, instance.get());
		}
		return invoker.invoke(invocation);
	}
}