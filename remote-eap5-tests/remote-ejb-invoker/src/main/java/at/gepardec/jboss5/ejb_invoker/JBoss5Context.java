package at.gepardec.jboss5.ejb_invoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

public class JBoss5Context {

	private String url;
	private String user;
	private String password;

	public JBoss5Context(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public <T> T lookup(String beanJNDI, Class<T> interfaceClass) {

		Properties jndiProperties = getProperties();

		Object bean;

		ClassLoader ctxCl = setContextClassloaderToLocal();
		try {
			final Context context = new InitialContext(jndiProperties);
			bean = context.lookup(beanJNDI);
			context.close();
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} finally {
			resetContextClassloader(ctxCl);
		}

		InvocationHandler handler = new JBoss5InvocationHandler(bean);
		return interfaceClass.cast(Proxy.newProxyInstance(ctxCl,
				new Class[] { interfaceClass }, handler));

	}

	public <T,H> T lookupEjb2(String beanJNDI, Class<T> interfaceClass, Class<H> homeInterface) {

		Properties jndiProperties = getProperties();

		Object bean;

		ClassLoader ctxCl = setContextClassloaderToLocal();
		try {
			final Context context = new InitialContext(jndiProperties);
			Object obj = context.lookup(beanJNDI);
		    Object home =
			        PortableRemoteObject.narrow(obj,homeInterface);

			   bean =   home.getClass().getMethod("create", (Class[]) null).invoke(home, (Object[]) null);

			context.close();
		} catch (NamingException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} finally {
			resetContextClassloader(ctxCl);
		}

		InvocationHandler handler = new JBoss5InvocationHandler(bean);
		return interfaceClass.cast(Proxy.newProxyInstance(ctxCl,
				new Class[] { interfaceClass }, handler));

	}

	static void resetContextClassloader(ClassLoader ctxCl) {
		Thread.currentThread().setContextClassLoader(ctxCl);
	}

	static ClassLoader setContextClassloaderToLocal() {
		ClassLoader ctxCl = Thread.currentThread().getContextClassLoader();
		Thread.currentThread().setContextClassLoader(
				JBoss5Context.class.getClassLoader());
		return ctxCl;
	}

	private Properties getProperties() {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, url);
		jndiProperties.put(Context.SECURITY_PRINCIPAL, user);
		jndiProperties.put(Context.SECURITY_CREDENTIALS, password);
		jndiProperties.put("jboss.naming.client.ejb.context", true);
		jndiProperties.setProperty("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");
		return jndiProperties;
	}

}
