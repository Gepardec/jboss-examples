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

import at.objectbay.tests.ws.soap.Person;

@WebServlet(urlPatterns = "/ejbws")
public class PerfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ServiceBean svc;

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

		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Wait</title></head><body>");
		out.println("Start<br/>");
		out.println(new Date().toString() + "<br/>");

		out.println("Rufe <a href=\"ejbws?time=" + time + "\">getPerson</a> "
				+ time + "ms<br/>");
		Person p = svc.getPerson();

		out.println("End<br/>");
		out.println("</body>");
		out.println("</html>");
	}

}
