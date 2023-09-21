package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

@WebServlet(urlPatterns = "/log")
public class LoggerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Inject
    CalcService service;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		out.println("<html><head><title>LoggingExample</title></head><body>");
		out.println("Start<br/>");
		out.println(new Date().toString() + "<br/>");

        out.println("Schreibe LogObject<br/>");
        service.calc(new CalcRequest(10, 5, Operation.SUM));


		out.println("End<br/>");
		out.println("</body>");
		out.println("</html>");
	}
}
