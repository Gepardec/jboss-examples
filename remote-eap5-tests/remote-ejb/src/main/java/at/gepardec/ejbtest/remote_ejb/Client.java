package at.gepardec.ejbtest.remote_ejb;

import at.gepardec.jboss5.ejb_invoker.JBoss5Context;

public class Client {

	public static void main(String[] args) {

			JBoss5Context  ctx = new JBoss5Context("jnp://127.0.0.1:1399", "admin", "admin");
			StringConverter converter = ctx.lookup(
					"Converter/remote-at.gepardec.ejbtest.remote_ejb.StringConverter", StringConverter.class);
			System.out.println("Service returns: " + converter.toUpperCase("Hello"));

	}

}
