package at.objectbay.tests.ws.jaxrs;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/ejbws")
public class PerfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ServiceBean svc;

    static private int zaehler;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		int time = 0;
		try {
			time = Integer.parseInt(request.getParameter("time"));
		} catch (NumberFormatException e) {
			// I don't care
		}
		int count = 1;
		try {
			count = Integer.parseInt(request.getParameter("count"));
		} catch (NumberFormatException e) {
			// I don't care
		}

		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Wait</title></head><body>");
		out.println("Start<br/>");
		out.println(new Date().toString() + "<br/>");

		out.println("Rufe <a href=\"ejbws?time=" + time + "&count="+count+"\">getPerson</a> "
				+ time + "ms<br/>");
		// Person p = svc.getPerson();
		String p = "";		
		String name = "user" + zaehler++;
		if ( zaehler > 99 ) zaehler = 1;
		while (count-- > 0) {
			p = svc.getCustomer(name);
			if (name.equals(p)) {
				p = "OK " + name;
			} else {
				throw new IllegalStateException("Falscher Returnwert: " + p
						+ " statt " + name);
			}
		}
		out.println("Got: " + p);

		out.println("End<br/>");
		out.println("</body>");
		out.println("</html>");
	}

}
