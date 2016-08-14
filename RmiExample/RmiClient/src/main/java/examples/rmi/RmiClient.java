package examples.rmi;
import javax.naming.InitialContext;


public class RmiClient {

	public static void main(String[] args) throws Exception {

		String jndiName = "";
		
		if ( args.length == 0 || args[0].equals("-h") ){
			printHelp();
			System.exit(0);
		}
		if ( args.length > 0 ){
			jndiName = args[0];
		}
		
		
		InitialContext ctx = new InitialContext();
		
		System.out.println("using jndiName: "+jndiName);
		HelloService service = 
			(HelloService) ctx.lookup(jndiName);
		
		System.out.println("Service returns: " + service.sayHello("Client"));
	}
	
	private static void printHelp() {
		System.out.println("Usage: RmiClient jndiName");
	}

}
