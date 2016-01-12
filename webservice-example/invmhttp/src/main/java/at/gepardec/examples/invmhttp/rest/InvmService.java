package at.gepardec.examples.invmhttp.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.URLConnectionClientExecutor;

@Path("/invm")
@RequestScoped
public class InvmService {
	@GET
	@Produces({ "application/json" })
	public String ServerName() {
		ClientExecutor executor = new URLConnectionClientExecutor();
		ClientRequest request = new ClientRequest("invmhttp://something/invmhttp/rest/name", executor);

		request.accept("application/json");
		ClientResponse<String> response;
		try {
			response = request.get(String.class);
		} catch (Exception e) {
			return "Result wasn't string!";
		}
		if (response.getStatus() != 200) {
			return "Request didn't worked!!!";
		}
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new ByteArrayInputStream(((String) response.getEntity()).getBytes())));

		String result = "";
		try {
			String output;
			while ((output = br.readLine()) != null) {
				result = result + output;
			}
		} catch (IOException e) {
			return "Faild to read result!" + result;
		}
		return "INVM call: " + result;
	}
}