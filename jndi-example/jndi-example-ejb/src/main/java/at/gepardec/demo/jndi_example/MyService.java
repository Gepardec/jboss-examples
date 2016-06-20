package at.gepardec.demo.jndi_example;

import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.annotation.Resources;

@Stateless
@Resources( {
    @Resource(name="java:/MyEJB1", lookup="java:app/jndi-example-ejb/MyService!at.gepardec.demo.jndi_example.Hello"),
    @Resource(name="java:jboss/MyEJB2", lookup="java:app/jndi-example-ejb/MyService!at.gepardec.demo.jndi_example.Hello"),
    @Resource(name="java:jboss/exported/MyEJB3", lookup="java:app/jndi-example-ejb/MyService!at.gepardec.demo.jndi_example.Hello")
})
public class MyService implements Hello{

	@Override
	public String hello() {
		return"Success!";
	}
}
