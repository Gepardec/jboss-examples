package at.gepardec.tests.ws.jaxrs;

import static at.gepardec.tests.ws.jaxrs.PerfServlet.getNumberParameter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

@WebServlet(urlPatterns = "/trouble")
public class TroubleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(TroubleServlet.class);

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int count = (short) getNumberParameter(request, "count", 0);

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Trouble</title></head><body>");
		out.println("Start<br/>");
		out.println(new Date().toString() + "<br/>");

		Compute compute = new Compute(10);
		compute.compute(count);

		out.println("Result: " + compute.result() + "<br/>");
		out.println("End<br/>");
		out.println("</body>");
		out.println("</html>");
	}

}
