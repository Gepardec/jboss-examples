package at.gepardec.jboss5.ejb_invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JBoss5InvocationHandler implements InvocationHandler {

	private Object bean;

	public JBoss5InvocationHandler(Object bean) {
		this.bean = bean;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		ClassLoader ctxCl = JBoss5Context.setContextClassloaderToLocal();
		try {
			return bean.getClass().getMethod(method.getName(), method.getParameterTypes()).invoke(bean, args);
		} finally{
			JBoss5Context.resetContextClassloader(ctxCl);	
		}
	}

}
