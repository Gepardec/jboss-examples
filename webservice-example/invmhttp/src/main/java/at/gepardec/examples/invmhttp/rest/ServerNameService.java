package at.gepardec.examples.invmhttp.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/name")
@RequestScoped
public class ServerNameService {
	@GET
	@Produces({ "application/json" })
	public String ServerName() {
		return "Server " + System.getProperty("server.name") + " ";
	}
}
