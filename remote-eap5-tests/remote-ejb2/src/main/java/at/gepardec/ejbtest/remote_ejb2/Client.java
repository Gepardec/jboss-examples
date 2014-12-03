package at.gepardec.ejbtest.remote_ejb2;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import at.gepardec.jboss5.ejb_invoker.JBoss5Context;

public class Client {

	public static void main(String[] args) {

		try {
			call1();

			call2();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void call1() throws RemoteException {
		JBoss5Context ctx = new JBoss5Context("jnp://127.0.0.1:1399", "admin",
				"admin");
		HelloObject hello = ctx.lookupEjb2("ejb/Hello", HelloObject.class,
				HelloHome.class);
		System.out.println("Service returns: " + hello.sayHello());
	}

	private static void call2() throws NamingException, RemoteException,
			CreateException {
		Properties p = new Properties();

		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.jnp.interfaces.NamingContextFactory");
		p.put(Context.PROVIDER_URL, "jnp://127.0.0.1:1399");
		p.put(Context.SECURITY_PRINCIPAL, "admin");
		p.put(Context.SECURITY_CREDENTIALS, "admin");
		p.put("jboss.naming.client.ejb.context", true);
		p.setProperty("java.naming.factory.url.pkgs",
				"org.jboss.naming:org.jnp.interfaces");

		InitialContext ctx = new InitialContext(p);

		// Lookup the bean using it's deployment id
		Object obj = ctx.lookup("ejb/Hello");

		// Be good and use RMI remote object narrowing
		// as required by the EJB specification.
		HelloHome ejbHome = (HelloHome) PortableRemoteObject.narrow(obj,
				HelloHome.class);

		// Use the HelloHome to create a HelloObject
		HelloObject ejbObject = ejbHome.create();

		// The part we've all been wainting for...
		String message = ejbObject.sayHello();

		// A drum roll please.
		System.out.println(message);
	}

}
