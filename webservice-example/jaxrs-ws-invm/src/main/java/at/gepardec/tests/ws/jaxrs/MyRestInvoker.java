package at.gepardec.tests.ws.jaxrs;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.jboss.resteasy.client.core.executors.URLConnectionClientExecutor;

import at.gepardec.tests.protocols.Protocols;

public class MyRestInvoker {
	private static final Logger log = Logger.getLogger(MyRestInvoker.class);

	/**
	 * Standard JAX-RS
	 */
	public Book getSingleBookInVM1() {
		Client client = ClientBuilder.newClient();

		return client
				.target("http://localhost:8080/jaxrs-ws-1.0.0-SNAPSHOT/rest/book/002")
				.request(MediaType.APPLICATION_JSON).get(Book.class);
	}

	public Book getSingleBookInVM() {
		Protocols.register();

		ClientExecutor executor = new URLConnectionClientExecutor();
		ClientRequest request = new ClientRequest(
				"invmhttp://someproxyname/books/rest/book/002",
				executor);

		try {
			return request.accept(MediaType.APPLICATION_JSON).get(Book.class)
					.getEntity();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
