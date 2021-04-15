package cn.deepdraw.training.framework.utils.session;

/**
 * Session上下文
 * @author huangjiancheng
 * 2018-10-10
 */
public final class SessionContext {

	private static ThreadLocal<Session> context = new ThreadLocal<>();

	private static SessionContext instance = null;

	private static Object lock = new Object();

	private SessionContext() {}

	public static SessionContext getInstance() {

		synchronized (lock) {

			if (instance == null) {

				synchronized (lock) {

					if (instance == null) {

						instance = new SessionContext();
					}
				}
			}
		}
		return instance;
	}

	public void set(Session session) {

		context.set(session);
	}

	public Session get() {

		return context.get();
	}

	public void remove() {

		context.remove();
	}
}