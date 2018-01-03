package at.gepardec.tests.ws.jaxrs;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.core.executors.ApacheHttpClient4Executor;
import org.jboss.resteasy.client.core.executors.URLConnectionClientExecutor;

import at.gepardec.tests.protocols.Protocols;

public class RestInvokerTest {

	@Test
	public void testJaxRsApi() {
		Client client = ClientBuilder.newClient();

		Book res = client
				.target("http://localhost:8080/jaxrs-ws-1.0.0-SNAPSHOT/rest/book/002")
				.request(MediaType.APPLICATION_JSON).get(Book.class);
		assertEquals("Book recieved", "002", res.getIsbn());
	}

	@Test
	public void testRestEasyApi() {
		Book res;
		
		ClientExecutor executor = new URLConnectionClientExecutor();
		ClientRequest request = new ClientRequest(
				"http://localhost:8080/jaxrs-ws-1.0.0-SNAPSHOT/rest/book/002",
				executor);

		try {
			res = request.accept(MediaType.APPLICATION_JSON).get(Book.class)
					.getEntity();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		assertEquals("Book recieved", "002", res.getIsbn());
	}
	
	@Test //@Ignore
	public void testInvmProtocol() {

		Protocols.register();
		
		Book res;		
		ClientExecutor executor = new URLConnectionClientExecutor();
		ClientRequest request = new ClientRequest(
				"invmhttp://someproxyname/jaxrs-ws-1.0.0-SNAPSHOT/rest/book/002",
				executor);

		try {
			res = request.accept(MediaType.APPLICATION_JSON).get(Book.class)
					.getEntity();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		assertEquals("Book recieved", "002", res.getIsbn());
	}

}
