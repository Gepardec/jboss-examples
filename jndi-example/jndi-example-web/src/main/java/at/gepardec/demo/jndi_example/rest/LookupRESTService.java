package at.gepardec.demo.jndi_example.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import at.gepardec.demo.jndi_example.LookupService;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members table.
 */
@Path("/lookup")
@RequestScoped
public class LookupRESTService {
   @Inject
   private LookupService svc;


   @GET
   @Path("/{jndi}")
   @Produces("text/xml")
   public String lookup(@PathParam("jndi") String jndi) throws NamingException {
      return svc.call(jndi);
   }
}
