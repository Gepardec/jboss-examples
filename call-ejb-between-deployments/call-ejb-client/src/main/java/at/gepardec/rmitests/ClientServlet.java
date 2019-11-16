package at.gepardec.rmitests;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import at.gepardec.ejbtest.remote_ejb.StringConverter;
@WebServlet("/ClientServlet")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = -4257270508512265869L;

	@EJB(mappedName="java:/services/StringConverter")
	StringConverter svc1;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String jndiName = request.getParameter("jndi");
		if ( null == jndiName ){
			jndiName = "java:/services/StringConverter";
		}
		out.println("Session ID: " + request.getSession().getId());

		String msg = "Session ID: " + request.getSession().getId() + " Value:"
				+ (Integer) request.getSession().getAttribute("test");
		System.out.println("Store: " + msg);

		out.println("Service1 returned with lookup:" + call(msg, jndiName));
		out.println("Service1 returned with inject:" + call1(msg));
	}

	private String call1(String msg) {
		return svc1.toUpperCase(msg);
	}

	private String call(String msg, String jndiName) {

		try {
			Context ctx = new InitialContext();
			StringConverter svc = (StringConverter)ctx.lookup(jndiName);
			return svc.toUpperCase(msg);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

}
