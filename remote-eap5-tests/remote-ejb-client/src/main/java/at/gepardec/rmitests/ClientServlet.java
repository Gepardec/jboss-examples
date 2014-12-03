package at.gepardec.rmitests;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.gepardec.ejbtest.remote_ejb.StringConverter;
import at.gepardec.ejbtest.remote_ejb2.HelloHome;
import at.gepardec.ejbtest.remote_ejb2.HelloObject;
import at.gepardec.jboss5.ejb_invoker.JBoss5Context;

@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = -4257270508512265869L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("Session ID: " + request.getSession().getId());

		String msg = "Session ID: " + request.getSession().getId() + " Value:"
				+ (Integer) request.getSession().getAttribute("test");
		System.out.println("Store: " + msg);

		out.println("Service1 returned " + call(msg));
		out.println("Service2 returned " + call2(msg));
	}

	private String call(String msg) {

		JBoss5Context ctx = new JBoss5Context("jnp://127.0.0.1:1399", "admin",
				"admin");
		StringConverter converter = ctx
				.lookup("Converter/remote-at.gepardec.ejbtest.remote_ejb.StringConverter",
						StringConverter.class);
		return converter.toUpperCase(msg);
	}

	private String call2(String msg) {

		JBoss5Context ctx = new JBoss5Context("jnp://127.0.0.1:1399", "admin",
				"admin");
		HelloObject hello = ctx.lookupEjb2("ejb/Hello", HelloObject.class,
				HelloHome.class);
		try {
			return hello.sayHello();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}
}
