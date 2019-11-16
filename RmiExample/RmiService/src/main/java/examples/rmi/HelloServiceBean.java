package examples.rmi;

import javax.ejb.Stateless;

@Stateless
public class HelloServiceBean implements HelloService {

	public String sayHello(String greeter) {
		return "Hello " + greeter;
	}
}
