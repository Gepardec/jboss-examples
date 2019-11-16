package examples.rmi;
import javax.ejb.Remote;

@Remote
public interface HelloService {

	public String sayHello(String greeter);
}
