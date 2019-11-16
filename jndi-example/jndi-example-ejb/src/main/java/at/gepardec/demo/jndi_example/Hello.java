package at.gepardec.demo.jndi_example;

import javax.ejb.Remote;

@Remote
public interface Hello {

	public String hello();
}
