package at.gepardec.demo.jndi_example;

import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


@Model
public class LookupService {
	Logger log = Logger.getLogger(LookupService.class.getName());

	private String jndiName = "";
	private String  value = "";

	
	public void call() throws NamingException{
		value = call(jndiName);
	}
	

	public String getJndiName() {
		return jndiName;
	}

	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	public String call(String jndi) throws NamingException {
		log.info("jndiName: >" + jndi + "<");
		Context ctx = new InitialContext();
		Hello svc = (Hello)ctx.lookup(jndi);
		return svc.hello();
	}
	
}
